package com.example.auth.services;
import com.example.auth.domain.questions.Question;
import org.json.JSONException;
import org.springframework.stereotype.Service;


@Service
public class QuestionService {

    private ApiInterface apiInterface;

    public String receiverQuestion(Question question) throws JSONException {
        if(question.getImageBase64().isEmpty()){
            return sendQuestionText(question);
        }else{
            return sendQuestionImage(question);
        }
    }

    private String sendQuestionImage(Question question) throws JSONException {
        return apiInterface.questionImage(question);

    }

    private String sendQuestionText(Question question) throws JSONException {
        return apiInterface.questionText(question);
    }


}
