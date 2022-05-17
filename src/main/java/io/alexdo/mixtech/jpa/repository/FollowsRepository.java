package io.alexdo.mixtech.jpa.repository;

import io.alexdo.mixtech.jpa.entity.FollowsEntity;
import io.alexdo.mixtech.jpa.entity.key.FollowsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FollowsRepository extends JpaRepository<FollowsEntity, FollowsKey> {
    FollowsEntity findByPidAndUid(Long pid, Long uid);
    List<FollowsEntity> findAllByPid(Long pid);

    @Transactional
    @Modifying
    @Query(value = "Update FollowsEntity f set f.access = :access where f.pid = :pid and f.uid = :uid")
    void updateAccessByPidAndUid(@Param("pid") Long pid, @Param("uid") Long uid, @Param("access") int access);
}
