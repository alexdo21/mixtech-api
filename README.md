# MixTech
 
A web application to help DJs and Musicians mix and find similar songs by interfacing directly with Spotify’s metadata on audio features.
 
[Click here to watch a demo of MixTech.](https://www.youtube.com/watch?v=SjTmeYfRfbo)
 
The tech stack of MixTech is:
- React front end using Redux, and other third party components most notably react-idle-timer, react-responsive-modal and react-input-range.
- Java Spring Boot back end using a [Spotify API wrapper](https://github.com/spotify-web-api-java/spotify-web-api-java).
- MySQL database.
 
Relevant repositories:
- [MixTech API](https://github.com/alexdo21/mixtech-api)
- [MixTech UI](https://github.com/alexdo21/mixtech-ui)
 
# Running Locally
 
1. Clone both repositories.
2. Setup a database server in MySQL and import the schema-mysql.sql file found in src/main/resources/ to create the mixtech database.
3. Open MixTech API in IntelliJ and use the MixTech Local run configuration to start the application.
4. In MixTech UI run npm install to download all required packages and npm start to start the application.
 
# Background
 
MixTech is an application that first got started off as a group project for my database management systems class in Summer 2019. It was actually my first exposure to building a full-stack application using a React front end, a Java Spring Boot back end and a MySQL database. I was really interested in building an app that interfaces with the [Spotify API](https://developer.spotify.com/documentation/web-api/) since it had so much data on all of their songs. I thought it would be really cool to build an app that would be useful for DJs to find songs that mix well together using certain audio features such as key, tempo, time signature, etc. Or it could even be useful for everyday musicians like me to quickly find out about a song's key and modality (major or minor).
 
In 2022, I decided to completely refactor and rewrite a lot of the existing code base. Originally, all of the 300,000 songs in the application were scraped off of the Billboard's Top 200 Albums dating back to 1967. It was a very hackish way to get data into the application and was very complicated to do. Furthermore, the security implementation only used a JSON web token to authenticate resource access, and users had to be registered within the database in order to do anything with the app.
 
To improve on the user experience, I decided to integrate everything with Spotify. Users are now able to use their Spotify accounts as a single-sign-on (SSO) experience and be assured of a secure experience. Instead of querying songs from the database, users can directly query from Spotify through MixTech and it will return the 20 most popular results. I also added extra functionality. Users are now able to listen to songs in the app using a custom Spotify player much like ones found on the Spotify desktop/web/mobile apps. They are also able to add their playlists on MixTech directly to Spotify.
 
# Technical Details
 
## MixTech API
 
MixTech API was refactored extensively and is modeled after hexagonal architecture. There are three main layers. Each layer has specific interfaces that other layers can interact with and specific domain POJO objects it acts on.
 
- API Layer
   - Interfaced by controllers
   - Acts on and returns RestResponse objects
 
- Application Layer
   - Interfaced by services
   - Acts on domain model objects
 
- Ports Layer (database and third-person APIs)
   - JPA port
       - Interfaced by daos
       - Acts on entity objects
   - Spotify port
       - Interfaced by clients
       - Acts on relevant objects from the Spotify API wrapper
 
## MixTech UI
 
Previously MixTech UI used higher class components. It has since been all converted to functional components with relevant useEffects to replace componentDidMount and componentWillUnmount, and useState to replace component states. Additionally, the redux implementation has been changed from connect to useSelector and dispatch hooks.
 
## Resource Data
 
It's important to look at the core data that MixTech acts on. MixTech has four main resources:
 
- **Matches** (either complete or incomplete) are pairs of songs that the user has decided goes well together. When a user adds a new match, it is known as an incomplete match. When a user decides to "pair" a match, that match will be considered a complete match.
 
- **Playlists** are lists of songs that a MixTech user curates. They can either be in a match or not. The standard convention is that users should first add songs that go well together and then add similar matches to a playlists to create a "setlist". However, this restriction is not enforced and the user has a lot of choice in how they use MixTech.
 
- **Songs** are extended and composite models for Spotify tracks. They contain both standard information as well as all of the audio features of a track.  Specifically a MixTech song has data on:
   - Spotify ID
   - song name
   - album name
   - artist name
   - danceability
   - energy
   - key
   - loudness
   - mode
   - speechiness
   - acousticness
   - instrumentalness
   - liveness
   - valence
   - tempo
   - duration
   - time signature
   - popularity
  
   Note that songs are only added to the database if they are added in a match or a playlist. This means that users can only perform advanced search queries if a song in the database matches that query. Theoretically, with enough users most songs will be able to be queried via advanced search.
 
- **Users** are the authenticated MixTech representations of a Spotify user. MixTech only stores data on a user's Spotify ID, email and name.
 
## Endpoints
 
MixTech users can access these resources through four main ways, which are represented as the pages in the front-end application.
 
- **Matches Page**
   - get complete and incomplete matches
   - delete an existing match
   - add songs in match to a playlist
 
- **Playlists Page**
   - get user playlists
   - create a playlist
   - delete a playlist
   - view all songs in playlist
   - create a match, pair a song to a match
 
- **Search Page**
   - query songs from Spotify using any search query term (i.e song name, artist name, album name, etc.)
   - create a match, pair a song to a match
   - add a song to a playlist
   - see complete matches matching the search query from all MixTech users 
 
- **Advanced Search Page**
   - query songs based on audio features (key, mode, time signature, duration, tempo, danceability, energy, loudness, speechiness, acousticness, instrumentalness, liveness, valence)
   - create a match, pair a song to a match
   - add a song to a playlist
   - see complete matches matching the search query from all MixTech users 
 
Everywhere a song is found in MixTech users can:
- view song details
- play/pause the song through the custom MixTech Spotify player
 
Note that the response value from the back end is standardized in a RESTful manner. Each response type DTO extends from the RestResponse parent class containing the fields status, description and errorMessage. The child class in turn provides the actual data the request asks for. The status can either be SUCCESS or FAILURE and the description indicates the type of data the client is asking for and the endpoint that it is requested at. If the status is a FAILURE, no data will be returned, instead a response will be returned and the errorMessage field will be populated with the specific exception message description that triggered an error.
 
## Security
 
A philosophy I had while implementing MixTech's security is to have the back end application contain and secure all of the resources and endpoints. If the user is not authenticated, the client application will redirect them to the login page. The most sensitive information in MixTech is the Spotify Access token that lets MixTech access resources Spotify. This token is only stored in the back end. Instead, authentication is done using a semi-stateless session with JSON web tokens. There are three main parts of the security implementation. They are described below.
 
### OAuth2 Login
 
When the user logs in to MixTech, Java Spring Security will redirect them to sign in to Spotify (if they are not already logged in). If the user decides not to proceed with logging in, they are redirected to the login page again. If the user logs in successfully, Spring Security will request an authorization code from Spotify and exchange it for access and refresh tokens. These tokens will be saved by the SpotifyApi from the aforementioned Spotify API wrapper. This means that MixTech implements the authorization code flow. If OAuth2 is successful, the back-end application will issue a JSON web token (JWT) that last about as long as the Spotify access token, authenticate the user and redirect them back to the front-end application. An unsuccessful login will again redirect back to the login page, but a successfully login will save the issued JWT to local storage and redirect them to a protected route (the actual application).
 
### Application lifetime
 
While MixTech is being used, the front-end application will continue to check if the user is authenticated on every call to the back-end API. If any call returns a 401, MixTech will redirect the user to the login page, prompting the user to restart the authorization/authentication flow. MixTech will also keep track of idleness state using react-idle-timer. If a user has not been active on MixTech for 5 minutes, they are considered idle.
 
### Staying logged in and refresh tokens
 
The lifetime of the application is dependent on Spotify's access token, which lasts for one hour. However, the access token can be refreshed using a refresh token an unlimited amount of times. The idea is to prevent this access token from expiring and keep refreshing it if the user is still using the application, otherwise log them out. The critical time period for this implementation is 5 minutes before the access token expires. If the user is still active (i.e not idle) 5 minutes before the access token is set to expire, the front end will automatically make a call to the back end to refresh the access token and generate a new JWT for itself. The user will have no indication that this is happening, thus providing a smooth user experience. However, if the user is idle 5 minutes before the access token is set to expire, the front end will prompt the user via a modal to either stay logged in or log out. If the user clicks log out, they will be redirected to the login page. If the user clicks stay logged in, the back-end will refresh the access token and generate a new JWT. Otherwise, if the user remains idle after 5 minutes, the user will automatically be logged out. This prompt will only allow the user to click to stay logged in or log out, they cannot dismiss the modal.
 
# Room For Improvement
 
- Ability to onboard non-spotify users without compromising on security.
- A revamp of the user interface using custom CSS and React components without relying on Bootstrap and third-person components.
- Add unit / integration tests.
 
