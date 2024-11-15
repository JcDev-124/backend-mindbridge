package com.example.auth.services;

import com.example.auth.domain.questions.Question;
import org.json.JSONException;

public interface ApiInterface {

    String questionImage(Question question) throws JSONException;

    String questionText(Question question) throws JSONException;
}
