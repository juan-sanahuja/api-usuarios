package com.juan.usuarios.repositories;

import com.juan.usuarios.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PhoneRepository extends JpaRepository<Phone, UUID> {
}
