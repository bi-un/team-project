package com.example.newtodo.fromToFront;

import com.example.newtodo.calcScore.TodoService;
import com.example.newtodo.transfer.TodoRequest;
import com.example.newtodo.transfer.TodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public List<TodoResponse> getTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody TodoRequest request){
        todoService.createTodo(request);
        return ResponseEntity.ok("등록 완료!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> toggleComplete(@PathVariable Long id, @RequestBody Map<String, Boolean> request){
        Boolean isCompleted=request.get("isCompleted");
        todoService.toggleComplete(id, isCompleted);
        return ResponseEntity.ok("변경 완료!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("삭제 완료!");
    }

    @GetMapping("/alerts")
    public ResponseEntity<Map<String, Object>> checkAlerts(){
        List<TodoResponse> alertTodos=todoService.getAlerts();

        Map<String, Object> response=new HashMap<>();
        response.put("hasAlarm", alertTodos.isEmpty());
        response.put("todos", alertTodos);

        return ResponseEntity.ok(response);
    }
}
