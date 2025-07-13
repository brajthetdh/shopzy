package productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import productservice.dto.ProductRequest;
import productservice.dto.ProductResponse;
import productservice.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired ProductService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductRequest req) {
        return service.update(id, req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/search")
    public List<ProductResponse> search(@RequestParam("name") String name) {
        return service.search(name);
    }
    
    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> getByCategory(@PathVariable Long categoryId) {
        return service.getByCategory(categoryId);
    }

    @GetMapping("/brand/{brandId}")
    public List<ProductResponse> getByBrand(@PathVariable Long brandId) {
        return service.getByBrand(brandId);
    }

    @GetMapping("/filter")
    public List<ProductResponse> filter(
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return service.filter(brandId, categoryId, sortBy, sortDir);
    }

    

}
