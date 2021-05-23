package rev.team.POINT_SERVICE.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rev.team.POINT_SERVICE.domain.entity.PointRecord;

@Repository
public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE rev_user SET point = point + :point WHERE (user_id = :userId);", nativeQuery = true)
    void updateUserPointById(String userId, int point);

    @Override
    Page<PointRecord> findAll(Pageable pageable);

    Page<PointRecord> findByUserId(String userId, Pageable pageable);

}