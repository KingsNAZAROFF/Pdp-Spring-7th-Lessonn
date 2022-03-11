package uz.pdp.pdpspring7thlessonn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.pdpspring7thlessonn.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
