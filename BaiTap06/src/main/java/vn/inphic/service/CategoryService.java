package vn.inphic.service;

import vn.inphic.entity.Category;
import vn.inphic.model.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Page<Category> findAll(Pageable pageable);
    Optional<Category> findById(Long id);
    Category save(Category category);
    void deleteById(Long id);
    Optional<Category> findByName(String name);
    Page<Category> findByNameContaining(String name, Pageable pageable);
}
