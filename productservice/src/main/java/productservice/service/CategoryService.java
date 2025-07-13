package productservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import productservice.dto.CategoryRequest;
import productservice.dto.CategoryResponse;
import productservice.entity.Category;
import productservice.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired private CategoryRepository repo;

    public CategoryResponse create(CategoryRequest req) {
        Category c = new Category();
        c.setName(req.name);
        c.setActive(req.active);
        return toResponse(repo.save(c));
    }

    public CategoryResponse update(Long id, CategoryRequest req) {
        Category c = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Category not found"));
        c.setName(req.name);
        c.setActive(req.active);
        return toResponse(repo.save(c));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("Category not found");
        repo.deleteById(id);
    }

    public CategoryResponse get(Long id) {
        return toResponse(repo.findById(id).orElseThrow(() -> new NoSuchElementException("Category not found")));
    }

    public List<CategoryResponse> getAll() {
        List<CategoryResponse> list = new ArrayList<>();
        for (Category c : repo.findAll()) list.add(toResponse(c));
        return list;
    }

    public CategoryResponse toggleActive(Long id) {
        Category c = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Category not found"));
        c.setActive(!c.isActive());
        return toResponse(repo.save(c));
    }

    private CategoryResponse toResponse(Category c) {
        CategoryResponse r = new CategoryResponse();
        r.id = c.getId();
        r.name = c.getName();
        r.active = c.isActive();
        return r;
    }
}

