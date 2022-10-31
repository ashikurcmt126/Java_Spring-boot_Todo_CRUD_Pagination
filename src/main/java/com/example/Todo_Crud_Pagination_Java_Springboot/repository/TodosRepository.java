package com.example.Todo_Crud_Pagination_Java_Springboot.repository;

import com.example.Todo_Crud_Pagination_Java_Springboot.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodosRepository extends CrudRepository<Todo, Long> {

    Page<Todo> findAll(Pageable pageRequest);
    Page<Todo> findByCompletedFalse(Pageable pageRequest);

    List<Todo> findAll();

    Page<Todo> findByCompletedIsTrue(Pageable pageRequest);

    @Query("select t from Todo t where t.completed = :completed")
    Page<Todo> findByHqlCompletedIs(Pageable pageRequest, boolean completed);

    List<Todo> findByCompleted(boolean done);

    Page<Todo> findByCompleted(Pageable pageable, boolean done);

    List<Todo> findByCompletedTrue();

    List<Todo> findByCompletedFalse();

    List<Todo> findByCompletedIsTrue();

    List<Todo> findByCompletedIsFalse();

    List<Todo> findByTitleContains(String title);

    List<Todo> findByDescriptionContains(String description);

    Page<Todo> findByCompletedTrue(PageRequest pageRequest);

    @Query("select t from Todo t where t.completed = :completed")
    List<Todo> findByHqlCompletedIs(boolean completed);

    @Query("select t from Todo t where t.title like %:word%")
    Page<Todo> findByHqlTitleLike(Pageable pageRequest, String word);

    @Query("select t from Todo t where t.title like %:title%")
    List<Todo> findByHqlTitleLike(@Param("title") String word);

    @Query("SELECT t FROM Todo t WHERE title = :title and description  = :description")
    List<Todo> findByHqlTitleAndDescription(String title, @Param("description") String description);

    @Query("select t FROM Todo t WHERE t.title like %:title%")
    List<Todo> findByHqlTitleContains(String title);

    @Query("select t FROM Todo t WHERE description like %:dscrptn%")
    List<Todo> findByHqlDescriptionContains(@Param("dscrptn") String dscr);

    // @Query("select t FROM Todo t WHERE lower(description) like %lower(:dscrptn)%") <-------- Will not work, use below
    // @Query("select t FROM Todo t WHERE lower(description) like  %lower(?0)%") <------- Neither
    @Query("select t FROM Todo t WHERE lower(description) like lower(concat('%', :dscrptn, '%'))")
    List<Todo> findByHqlDescriptionContainsIgnoreCase(@Param("dscrptn") String dscr);

    @Query("select t FROM Todo t WHERE title = ?0 and description  = ?1")
    List<Todo> findByTHqlTitleAndDescription(String title, String description);

}
