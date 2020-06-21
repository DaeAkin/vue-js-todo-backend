package com.vuejs.backend.domain.todo.api;

import com.vuejs.backend.domain.todo.application.TodoService;
import com.vuejs.backend.domain.todo.domain.Todo;
import com.vuejs.backend.domain.todo.dto.TodoSaveRequest;
import com.vuejs.backend.domain.user.domain.User;
import com.vuejs.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoApi {

    private final TodoService todoService;

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable(value = "todoId") Integer todoId) throws NotFoundException {
        Optional<Todo> todo = todoService.findTodo(todoId);
        return  todo.map(t -> new ResponseEntity(t,HttpStatus.OK)).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable(value = "todoId") Integer todoId,
                                           @RequestBody @Valid TodoSaveRequest todoSaveRequest) throws NotFoundException {
        Optional<Todo> todo = todoService.updateTodo(todoId, todoSaveRequest);
        return todo.map(t -> new ResponseEntity(t,HttpStatus.OK)).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable(value = "todoId") Integer todoId)  {
        log.info("todoId :: {}" , todoId);
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody @Valid TodoSaveRequest todoSaveRequest , Authentication authentication) throws NotFoundException {
//        User user = (User) authentication.getPrincipal();
        Optional<Todo> todo = todoService.insertTodo(todoSaveRequest,null);
        log.info("created Todo : {}" ,todo.get() );
        return todo.map(t -> new ResponseEntity(t,HttpStatus.CREATED)).orElseThrow(NotFoundException::new);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(@RequestParam("skip") int skip, @RequestParam("limit") int limit) throws NotFoundException {
        log.info("getAllTodos");
        List<Todo> todoList = new ArrayList<>();
        Pageable pageable = PageRequest.of(skip, limit);
        Optional<List<Todo>> todo = todoService.findAllTodos(pageable);
        return todo.map(t -> new ResponseEntity(t,HttpStatus.OK)).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadImage( @RequestParam(value = "imageFile") MultipartFile imageFile) {
        todoService.uploadImage(imageFile);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
