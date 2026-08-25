package com.example.todobackend.service;

import com.example.todobackend.entity.Todo;
import com.example.todobackend.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 전체 Todo 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    // Todo 등록
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    // Todo 수정
    public Todo updateTodo(Long id, Todo todo) {

        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (todo.getTitle() != null) {
            existingTodo.setTitle(todo.getTitle());
        }

        if (todo.getDueDate() != null) {
            existingTodo.setDueDate(todo.getDueDate());
        }

        if (todo.getPriority() != null) {
            existingTodo.setPriority(todo.getPriority());
        }

        if (todo.getIsCompleted() != null) {
            existingTodo.setIsCompleted(todo.getIsCompleted());
        }

        return todoRepository.save(existingTodo);
    }

    // Todo 삭제
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
    
    // 	긴급 알림 대상 Todo 조회 추가
    public List<Todo> getAlertTodos() {
        // 현재 시간 기준으로 24시간 뒤의 시간을 계산
        LocalDateTime twentyFourHoursLater = LocalDateTime.now().plusHours(24);
        
        // 24시간 이내에 마감되거나 이미 마감이 지난 미완료 할 일 목록 반환
        return todoRepository.findByIsCompletedFalseAndDueDateBeforeOrderByDueDateAsc(twentyFourHoursLater);
    }
}