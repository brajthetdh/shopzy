package productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import productservice.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}