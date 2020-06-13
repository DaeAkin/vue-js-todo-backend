package com.vuejs.backend.domain.todo.dao;

import com.vuejs.backend.domain.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
}
