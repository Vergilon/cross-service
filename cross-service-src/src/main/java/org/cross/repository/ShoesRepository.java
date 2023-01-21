package org.cross.repository;

import java.util.Optional;
import java.util.UUID;
import org.cross.model.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, UUID> {
    Optional<Shoes> findByClientId(UUID clientId);
}
