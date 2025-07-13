package productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import productservice.dto.ProductRequest;
import productservice.dto.ProductResponse;
import productservice.entity.Product;
import productservice.repository.BrandRepository;
import productservice.repository.CategoryRepository;
import productservice.repository.ProductRepository;

import java.util.*;

@Service
public class ProductService {
    @Autowired ProductRepository productRepo;
    @Autowired BrandRepository brandRepo;
    @Autowired CategoryRepository categoryRepo;

    public ProductResponse create(ProductRequest req) {
        Product p = new Product();
        p.setName(req.name);
        p.setDescription(req.description);
        p.setPrice(req.price);
        p.setStock(req.stock);
        p.setSku(req.sku);
        p.setActive(true);
        p.setCreatedAt(new Date());
        p.setBrand(brandRepo.findById(req.brandId).orElseThrow(() -> new NoSuchElementException("Brand not found")));
        p.setCategory(categoryRepo.findById(req.categoryId).orElseThrow(() -> new NoSuchElementException("Category not found")));
        return toResponse(productRepo.save(p));
    }

    public ProductResponse update(Long id, ProductRequest req) {
        Product p = productRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));
        p.setName(req.name);
        p.setDescription(req.description);
        p.setPrice(req.price);
        p.setStock(req.stock);
        p.setSku(req.sku);
        p.setUpdatedAt(new Date());
        p.setBrand(brandRepo.findById(req.brandId).orElseThrow(() -> new NoSuchElementException("Brand not found")));
        p.setCategory(categoryRepo.findById(req.categoryId).orElseThrow(() -> new NoSuchElementException("Category not found")));
        return toResponse(productRepo.save(p));
    }

    public void delete(Long id) {
        if (!productRepo.existsById(id)) {
            throw new NoSuchElementException("Product not found");
        }
        productRepo.deleteById(id);
    }

    public ProductResponse getById(Long id) {
        return toResponse(productRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found")));
    }

    public List<ProductResponse> getAll() {
        List<Product> list = productRepo.findAll();
        List<ProductResponse> res = new ArrayList<>();
        for (Product p : list) res.add(toResponse(p));
        return res;
    }

    public List<ProductResponse> search(String name) {
        List<Product> list = productRepo.findByNameContainingIgnoreCase(name);
        List<ProductResponse> res = new ArrayList<>();
        for (Product p : list) res.add(toResponse(p));
        return res;
    }
    
    public ProductResponse toResponse(Product p) {
        ProductResponse r = new ProductResponse();
        r.id = p.getId();
        r.name = p.getName();
        r.description = p.getDescription();
        r.price = p.getPrice();
        r.stock = p.getStock();
        r.sku = p.getSku();
        r.brand = (p.getBrand() != null) ? p.getBrand().getName() : null;
        r.category = (p.getCategory() != null) ? p.getCategory().getName() : null;
        r.imageUrls = new ArrayList<>();
        if (p.getImages() != null) {
            for (Image img : p.getImages()) {
                r.imageUrls.add(img.getUrl());
            }
        }
        return r;
    }


}

