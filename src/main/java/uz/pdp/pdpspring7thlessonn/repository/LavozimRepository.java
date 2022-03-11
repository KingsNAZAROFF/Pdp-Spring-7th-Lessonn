package uz.pdp.pdpspring7thlessonn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.pdpspring7thlessonn.entity.Lavozim;

import java.util.Optional;

@Repository
public interface LavozimRepository extends JpaRepository<Lavozim,Long> {
    Optional<Lavozim> findByName(String name);
    boolean existsByName(String name);
}
