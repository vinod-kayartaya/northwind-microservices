package com.infosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
