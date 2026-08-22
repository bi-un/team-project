package com.example.newtodo.manipData;

import com.example.newtodo.withTable.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByIsCompletedFalse();
}
