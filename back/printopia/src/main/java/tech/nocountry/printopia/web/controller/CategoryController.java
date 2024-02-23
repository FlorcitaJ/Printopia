/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.nocountry.printopia.web.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.nocountry.printopia.persistence.entity.Category;
import tech.nocountry.printopia.service.CategoryService;

/**
 *
 * @author Cris
 */
    @RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Retrieves all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    // Retrieves a category by ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.findById(id);
    }

    // Creates a new category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    // Updates an existing category
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Integer id, @RequestBody Category updatedCategory) {
        return categoryService.update(id, updatedCategory);
    }

    // Deletes a category by ID
    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Integer id) throws Exception{
        categoryService.deleteById(id);
    }

    // Searches for categories by name
    @GetMapping("/search/name")
    public List<Category> findByName(@RequestParam String name) {
        return categoryService.findByName(name);
    }

    // Searches for categories by description containing the given text
    @GetMapping("/search/description")
    public List<Category> findByDescriptionContaining(@RequestParam String description) throws Exception {
        return categoryService.findByDescriptionContaining(description);
    }
}



