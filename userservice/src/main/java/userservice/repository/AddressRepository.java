package userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import userservice.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUsername(String username);
}