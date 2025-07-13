package productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import productservice.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
