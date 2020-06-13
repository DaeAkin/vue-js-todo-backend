package com.vuejs.backend.domain.todo.dto;

import com.vuejs.backend.domain.todo.domain.Todo;
import com.vuejs.backend.domain.user.domain.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class TodoSaveRequest {
    @NotNull
    private String title;

    private Boolean completed;

    public Todo toEntity(User user) {
        return Todo.builder()
                .title(title)
                .completed(completed)
//                .user(user)
                .build();

    }
}
