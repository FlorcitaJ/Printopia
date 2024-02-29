/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.nocountry.printopia.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import tech.nocountry.printopia.persistence.entity.Category;
import tech.nocountry.printopia.persistence.repository.CategoryRepository;

/**
 *
 * @author Cris
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Retrieves all categories from the repository
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Retrieves a category by its ID, throwing an exception if not found
    public Category findById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Category not found with id: " + id));
    }

    // Saves a new category, throwing an exception if it's null
    public Category save(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        return categoryRepository.save(category);
    }

    // Updates an existing category, validating data and handling null values
    public Category update(Integer id, Category updatedCategory) {
        Category category = findById(id);
        if (category != null) {
            // Validate and update name and description
            if (updatedCategory.getName() != null) {
                category.setName(updatedCategory.getName());
            }
            if (updatedCategory.getDescription() != null) {
                category.setDescription(updatedCategory.getDescription());
            }
            return categoryRepository.save(category);
        } else {
            throw new NoSuchElementException("Category not found with id: " + id);
        }
    }

    // Finds categories by description, throwing an exception if description is null
    public List<Category> findByDescriptionContaining(String description) throws Exception {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        return categoryRepository.findByDescriptionContainingIgnoreCase(description);
    }

    // Finds categories by name
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    // Deletes a category by its ID
    public void deleteById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new NoSuchElementException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
