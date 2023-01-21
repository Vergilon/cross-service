package org.cross.repository;

import java.util.UUID;
import org.cross.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
}
