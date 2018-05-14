package cloud.cinder.cindercloud.arcane.user.repository;

import cloud.cinder.cindercloud.arcane.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByExternalId(@Param("externalId") final String externalId);

}
