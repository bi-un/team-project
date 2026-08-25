package com.example.todobackend.controller;

import com.example.todobackend.entity.Todo;
import com.example.todobackend.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 전체 Todo 조회
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(todoService.getTodos());
    }

    // Todo 등록
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    // Todo 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable("id") Long id,    // <--- ("id") 추가!
            @RequestBody Todo todo) {

        return ResponseEntity.ok(todoService.updateTodo(id, todo));
    }

    // Todo 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Long id) {    // <--- ("id") 추가!
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/alerts")
    public ResponseEntity<Map<String, Object>> getAlerts() {
        List<Todo> alertTodos = todoService.getAlertTodos();
        
        // 프론트엔드의 조건문(!data.hasAlarm && data.todos.length > 0)을
        // 통과하기 위해 맞춤형 JSON 구조(Map) 생성
        Map<String, Object> response = new HashMap<>();
        response.put("hasAlarm", false);
        response.put("todos", alertTodos);
        
        return ResponseEntity.ok(response);
    }
}