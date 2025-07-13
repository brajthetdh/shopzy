package productservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import productservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameContainingIgnoreCase(String name);
	List<Product> findByCategoryId(Long categoryId);
	List<Product> findByBrandId(Long brandId);
	
	// jpql for filter product by brand and category
	@Query("SELECT p FROM Product p WHERE " +
		       "(:brandId IS NULL OR p.brand.id = :brandId) AND " +
		       "(:categoryId IS NULL OR p.category.id = :categoryId)")
		Page<Product> filterProducts(@Param("brandId") Long brandId,
		                             @Param("categoryId") Long categoryId,
		                             Pageable pageable);


}