package com.vuejs.backend.domain.todo.application;

import com.vuejs.backend.domain.todo.domain.Todo;
import com.vuejs.backend.domain.todo.dto.TodoSaveRequest;
import com.vuejs.backend.domain.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public interface TodoService {
    @Transactional
    Optional<Todo> findTodo(Integer todoId);
    @Transactional
    Optional<Todo> updateTodo(Integer todoId, TodoSaveRequest todoSaveRequest);
    @Transactional
    Optional<Todo> insertTodo(TodoSaveRequest todoSaveRequest, User user);
    @Transactional
    void deleteTodo(Integer todoId);
    @Transactional
    Optional<List<Todo>> findAllTodos(Pageable pageable);
    @Transactional
    void uploadImage(MultipartFile multipartFile);
}
