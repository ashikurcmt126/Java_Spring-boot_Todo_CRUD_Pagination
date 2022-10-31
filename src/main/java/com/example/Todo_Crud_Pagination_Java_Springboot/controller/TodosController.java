package com.example.Todo_Crud_Pagination_Java_Springboot.controller;

import com.example.Todo_Crud_Pagination_Java_Springboot.entity.Todo;
import com.example.Todo_Crud_Pagination_Java_Springboot.entity.Tutorial;
import com.example.Todo_Crud_Pagination_Java_Springboot.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/todos")
public class TodosController {

    @Autowired
    TodosRepository todosRepository;

    @GetMapping("/pagination")
    public ResponseEntity<Page<Todo>> getAllTodos(@DefaultValue("1") @QueryParam("page") int page,
                                                  @DefaultValue("5") @QueryParam("page_size") int pageSize) {
        try {
            Pageable paging = PageRequest.of(
                    page, pageSize);
            Page<Todo> pages = todosRepository.findAll(paging);

            if (pages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") long id) {
        Optional<Todo> todoData = todosRepository.findById(id);

        if (todoData.isPresent()) {
            return new ResponseEntity<>(todoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/completed")
    public ResponseEntity<Page<Todo>> getCompletedTodos(@DefaultValue("0") @QueryParam("page") int page,
                                                        @DefaultValue("5") @QueryParam("page_size") int pageSize) {
        try {
            Pageable paging = PageRequest.of(
                    page, pageSize);
            Page<Todo> pages = todosRepository.findByCompletedIsTrue(paging);

            if (pages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pending")
    public ResponseEntity<Page<Todo>> getNotCompletedTodos(@DefaultValue("0") @QueryParam("page") int page,
                                                  @DefaultValue("5") @QueryParam("page_size") int pageSize) {
        try {
            Pageable paging = PageRequest.of(
                    page, pageSize);
            Page<Todo> pages = todosRepository.findByCompletedFalse(paging);

            if (pages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<Todo>> search(@RequestParam(defaultValue = "lklj", required = false) String searchValue) {

            List<Todo> todos = todosRepository.findByHqlTitleLike(searchValue);
            if (todos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(todos, HttpStatus.OK);


    }

    @PostMapping("")
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        try {
            Todo _todo = todosRepository
                    .save(new Todo(todo.getTitle(), todo.getDescription(), todo.isCompleted()));
            return new ResponseEntity<>(_todo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") long id, @RequestBody Todo todo) {
        Optional<Todo> todoData = todosRepository.findById(id);

        if (todoData.isPresent()) {
            Todo _todo = todoData.get();
            _todo.setTitle(todo.getTitle());
            _todo.setDescription(todo.getDescription());
            _todo.setCompleted(todo.isCompleted());
            return new ResponseEntity<>(todosRepository.save(_todo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable("id") long id) {
        try {
            todosRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllTodos() {
        try {
            todosRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
