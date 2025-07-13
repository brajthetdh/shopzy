package productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import productservice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}