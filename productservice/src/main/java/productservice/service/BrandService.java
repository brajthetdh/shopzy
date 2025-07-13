package productservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import productservice.dto.BrandRequest;
import productservice.dto.BrandResponse;
import productservice.entity.Brand;
import productservice.repository.BrandRepository;

@Service
public class BrandService {

    @Autowired private BrandRepository repo;

    public BrandResponse create(BrandRequest req) {
        Brand b = new Brand();
        b.setName(req.name);
        b.setActive(req.active);
        return toResponse(repo.save(b));
    }

    public BrandResponse update(Long id, BrandRequest req) {
        Brand b = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Brand not found"));
        b.setName(req.name);
        b.setActive(req.active);
        return toResponse(repo.save(b));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("Brand not found");
        repo.deleteById(id);
    }

    public BrandResponse get(Long id) {
        return toResponse(repo.findById(id).orElseThrow(() -> new NoSuchElementException("Brand not found")));
    }

    public List<BrandResponse> getAll() {
        List<BrandResponse> list = new ArrayList<>();
        for (Brand b : repo.findAll()) list.add(toResponse(b));
        return list;
    }

    public BrandResponse toggleActive(Long id) {
        Brand b = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Brand not found"));
        b.setActive(!b.isActive());
        return toResponse(repo.save(b));
    }

    private BrandResponse toResponse(Brand b) {
        BrandResponse r = new BrandResponse();
        r.id = b.getId();
        r.name = b.getName();
        r.active = b.isActive();
        return r;
    }
}
