package com.servidortcc.julio.controllers;

import com.servidortcc.julio.entities.Question;
import com.servidortcc.julio.services.SendQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class SendQuestionController {

    @Autowired
    SendQuestionService sendQuestionService;

    @CrossOrigin(origins = "*")
    @PostMapping("/send")
    public ResponseEntity<String> sendQuestion(@RequestBody Question question) {
        try {
            System.out.println(question);
            String result = sendQuestionService.receiverQuestion(question);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
