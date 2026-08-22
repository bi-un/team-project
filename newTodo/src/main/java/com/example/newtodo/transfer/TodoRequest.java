package com.example.newtodo.transfer;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class TodoRequest {
    private String title;
    private LocalDateTime deadline;
    private int priority;
}
