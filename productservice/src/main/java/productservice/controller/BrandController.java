package productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import productservice.dto.BrandRequest;
import productservice.dto.BrandResponse;
import productservice.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired BrandService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public BrandResponse create(@RequestBody BrandRequest req) {
        return service.create(req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public BrandResponse update(@PathVariable Long id, @RequestBody BrandRequest req) {
        return service.update(id, req);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public BrandResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<BrandResponse> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/toggle")
    public BrandResponse toggle(@PathVariable Long id) {
        return service.toggleActive(id);
    }
}

