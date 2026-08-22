package com.example.newtodo.calcScore;

import com.example.newtodo.manipData.TodoRepository;
import com.example.newtodo.transfer.TodoRequest;
import com.example.newtodo.transfer.TodoResponse;
import com.example.newtodo.withTable.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public void createTodo(TodoRequest request){
        Todo todo=new Todo();
        todo.setTitle(request.getTitle());
        todo.setDeadline(request.getDeadline());
        todo.setPriority(request.getPriority());
        todoRepository.save(todo);
    }

    public List<TodoResponse> getAllTodos(){
        return todoRepository.findAll().stream()
                .map(TodoResponse::new).collect(Collectors.toList());
    }

    public List<TodoResponse> getAlerts(){
        LocalDateTime now=LocalDateTime.now();
        List<Todo> incompleteTodos=todoRepository.findByIsCompletedFalse();

        return incompleteTodos.stream().filter(todo->calculateScore(todo, now)>=60)
                .map(TodoResponse::new).collect(Collectors.toList());
    }

    private int calculateScore(Todo todo, LocalDateTime now){
        int score=0;
        long hoursBetw= ChronoUnit.HOURS.between(now, todo.getDeadline());

        if(hoursBetw<=24)score+=50;
        else if(hoursBetw<=48)score+=30;
        else if(hoursBetw<=72)score+=10;

        if(todo.getPriority()==3)score+=30;
        else if(todo.getPriority()==2)score+=20;
        else if(todo.getPriority()==1)score+=10;

        return score;
    }

    public void toggleComplete(Long id, Boolean isCompleted){
        Todo todo=todoRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("할 일이 없음.."));
        todo.setIsCompleted(isCompleted);
        todoRepository.save(todo);
    }

    public void deleteTodo(Long id){
        todoRepository.deleteById(id);
    }
}
