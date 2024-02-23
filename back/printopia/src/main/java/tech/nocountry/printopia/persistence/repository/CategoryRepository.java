/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.nocountry.printopia.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.nocountry.printopia.persistence.entity.Category;

/**
 *
 * @author Cris
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public List<Category> findByName(String name);

    public List<Category> findByDescriptionContaining(String description);

}

