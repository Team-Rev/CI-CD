package rev.team.AUTHSERVER.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rev.team.AUTHSERVER.domain.RevUser;

import javax.transaction.Transactional;
import java.util.Optional;

public interface RevUserRepository extends JpaRepository<RevUser, String> {
    Optional<RevUser> findRevUserByUserId(String id);

    Optional<RevUser> findUserIdByNameAndPhone(String name, String phone);

    Optional<RevUser> findUserForPwBySomeInfo(String name, String userId, String phone);

    @Modifying
    @Transactional
    @Query(value = "UPDATE rev_user SET password = :password WHERE (userId = :userId);", nativeQuery = true)
    void changePw(String userId, String password);

    Optional<RevUser> saveNewPwByUserId(String userId, String pw);


}
