package com.vuejs.backend.domain.todo.domain;

import com.vuejs.backend.domain.todo.dto.TodoSaveRequest;
import com.vuejs.backend.domain.user.domain.User;
import com.vuejs.backend.global.domain.BaseAuditingEntity;

import javax.persistence.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Slf4j
@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(0)
    @Max(10000000)
    private Integer id;
    @Setter
    private String title;
    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void updateTodo(TodoSaveRequest todoSaveRequest) {
        this.title = todoSaveRequest.getTitle();
        this.completed = todoSaveRequest.getCompleted();
    }



}
