package com.example.todobackend.repository;

import com.example.todobackend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    
    // 미완료된 할 일 중, 특정 시간(기준 시간) 이전이 마감인 할 일을 마감일 오름차순으로 조회
    List<Todo> findByIsCompletedFalseAndDueDateBeforeOrderByDueDateAsc(LocalDateTime endTime);
}