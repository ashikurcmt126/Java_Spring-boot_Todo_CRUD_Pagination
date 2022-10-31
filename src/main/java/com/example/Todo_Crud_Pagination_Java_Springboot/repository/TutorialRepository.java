package com.example.Todo_Crud_Pagination_Java_Springboot.repository;

import com.example.Todo_Crud_Pagination_Java_Springboot.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
}
