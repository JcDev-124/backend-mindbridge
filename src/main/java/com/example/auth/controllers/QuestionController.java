package com.example.auth.controllers;

import com.example.auth.domain.questions.Question;
import com.example.auth.services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/question")

public class QuestionController {

    @Autowired
    QuestionService sendQuestionService;

    @PostMapping
    public ResponseEntity<String> sendQuestion(@RequestBody Question question) {
        try {
            System.out.println("Recebido: " + question);
            String result = sendQuestionService.receiverQuestion(question);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Log completo da exceção
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity test(){
        return ResponseEntity.ok().build();
    }
}
