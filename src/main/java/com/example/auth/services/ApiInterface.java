package com.example.auth.services;

import com.example.auth.domain.questions.Question;
import org.json.JSONException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


@Configuration
public interface ApiInterface {

    String questionImage(Question question) throws JSONException, UnsupportedEncodingException;

    String questionText(Question question) throws JSONException, IOException;
}
