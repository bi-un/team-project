package com.example.newtodo.withTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
//class에 적용
public class Todo {
    @Id //PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT
    //변수 id에 적용(아래 Column도 각 변수에 적용)
    private long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDateTime deadline;
    @Column(nullable = false)
    private int priority;
            //or Integer
    @Column(nullable = false)
    private Boolean isCompleted=false;
}
