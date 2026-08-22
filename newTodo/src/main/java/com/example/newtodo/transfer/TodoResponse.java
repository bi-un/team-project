package com.example.newtodo.transfer;

import com.example.newtodo.withTable.Todo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponse {
    private long id;
    private String title;

    @JsonProperty("dueDate")
    private LocalDateTime deadline;

    private int priority;
    private boolean isCompleted;

    //생성자
    public TodoResponse(Todo todo){
        this.id=todo.getId();
        this.title=todo.getTitle();
        this.deadline=todo.getDeadline();
        this.priority=todo.getPriority();
        this.isCompleted=todo.getIsCompleted();
    }
}
