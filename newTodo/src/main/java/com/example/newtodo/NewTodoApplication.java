package com.example.newtodo; // 본인 패키지명에 맞게 유지해주세요

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewTodoApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(NewTodoApplication.class, args);
        } catch (Exception e) {
            // 스프링이 삼켜버린 진짜 에러 원인을 강제로 콘솔에 빨간 피로 출력합니다.
            e.printStackTrace();
        }
    }
}