package rev.team.AUTHSERVER.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rev.team.AUTHSERVER.domain.RevUser;

import javax.transaction.Transactional;
import java.util.Optional;

public interface RevUserRepository extends JpaRepository<RevUser, String> {
    Optional<RevUser> findRevUserByUserId(String id);

    @Query(value = "SELECT userId FROM rev_user WHERE (name = :name AND phone = :phone)", nativeQuery = true)
    String findUserId(String name, String phone);

    @Query(value = "SELECT * FROM rev_user WHERE (name = :name AND userId = :userId AND phone = :phone)", nativeQuery = true)
    boolean findPw(String name, String userId, String phone);

    @Modifying
    @Transactional
    @Query(value = "UPDATE rev_user SET password = :password WHERE (userId = :userId);", nativeQuery = true)
    void changePw(String userId, String password);
}
