package com.vuejs.backend.domain.todo.application;

import com.vuejs.backend.domain.todo.dao.TodoRepository;
import com.vuejs.backend.domain.todo.domain.Todo;
import com.vuejs.backend.domain.todo.dto.TodoSaveRequest;
import com.vuejs.backend.domain.user.domain.User;
import com.vuejs.backend.global.utils.MediaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

//    @PostConstruct
//    public void initTodo() {
//        todoRepository.save(new Todo(0,"Vue 끝내기 BackEnd Data",false,null));
//        todoRepository.save(new Todo(1,"우헤헤헹 BackEnd Data",false,null));
//    }

    @Value("${resource-path}")
    private String resourcePath;

    @Override
    public Optional<Todo> findTodo(Integer todoId) {
        return todoRepository.findById(todoId);
    }

    @Override
    public Optional<Todo> updateTodo(Integer todoId, TodoSaveRequest todoSaveRequest) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if(!todo.isPresent())
            return Optional.empty();
        Todo findTodo = todo.get();
        findTodo.updateTodo(todoSaveRequest);
        return Optional.of(todoRepository.save(findTodo));
    }

    @Override
    public Optional<Todo> insertTodo(TodoSaveRequest todoSaveRequest, User user) {
        Todo saveTodo = todoSaveRequest.toEntity(user);
        return Optional.of(todoRepository.save(saveTodo));
    }



    @Override
    public void deleteTodo(Integer todoId) {
        Optional<Todo> todo = todoRepository.findById(todoId);
        if(!todo.isPresent()) {
            // if todo is not exist, do someting or throw Exception
        }
        todoRepository.delete(todo.get());
    }

    @Override
    public Optional<List<Todo>> findAllTodos(Pageable pageable) {
        Page<Todo> allTodo = todoRepository.findAll(pageable);
        return Optional.of(allTodo.getContent());
    }

    @Override
    public void uploadImage(MultipartFile multipartFile) {
        MediaUtil.saveImageFile(multipartFile,resourcePath,"");
    }
}
