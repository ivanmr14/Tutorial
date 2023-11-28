package com.ccsw.springboot.category;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.springboot.category.model.Category;

/**
 * @author ccsw
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

}