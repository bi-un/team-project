package com.example.newtodo.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class TodoRequest {
    private String title;

    @JsonProperty("dueDate")
    private LocalDateTime deadline;

    private int priority;
}
