package com.servidortcc.julio.services;
import com.servidortcc.julio.domain.questions.Question;
import org.springframework.stereotype.Service;


@Service
public class SendQuestionService {
    public String receiverQuestion(Question question) {
        if(question.getImageBase64().isEmpty()){
            return sendQuestionText(question);
        }else{
            return sendQuestionImage(question);
        }
    }

    private String sendQuestionImage(Question question) {
        // Requisicao pra API
        return question.toString() + "\nRequisicao  de Imaagem ";

    }

    private String sendQuestionText(Question question) {
        // REQUISICAO PRA API
        return question.toString() + "\nRequisicao de texto";
    }


}
