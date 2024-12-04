package com.example.auth.services;
import com.example.auth.domain.questions.Question;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


@Service
public class QuestionService {

    private ApiInterface apiInterface;
    public QuestionService(ApiInterface apiInterface){
        this.apiInterface = apiInterface;
    }


    public String receiverQuestion(Question question) throws JSONException, IOException {
        if (question.getImageBase64() == null || question.getImageBase64().isEmpty())
            return sendQuestionText(question);

        return sendQuestionImage(question);

    }


    private String sendQuestionImage(Question question) throws JSONException, UnsupportedEncodingException {
        return apiInterface.questionImage(question);

    }

    private String sendQuestionText(Question question) throws JSONException, IOException {
        return apiInterface.questionText(question);
    }


}
