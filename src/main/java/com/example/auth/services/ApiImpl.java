package com.example.auth.services;

import com.example.auth.domain.questions.Question;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.awt.SystemColor.text;

@Configuration
public class ApiImpl implements ApiInterface {

    //@Value("${api.gpt}")
    private static String API_KEY = "YOUR_API_KEY";

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String MODEL_NAME = "gpt-3.5-turbo";
    private static final String MODEL_NAME_IMAGE = "gpt-4o-mini";


    @Override
    public String questionText(Question question) throws JSONException, IOException {

        // Configuração da requisição HTTP
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL);

        // Cabeçalhos da requisição
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + API_KEY);

        // Corpo da requisição
        String json = "{ \"model\": \"" + MODEL_NAME + "\", \"messages\": [{ \"role\": \"user\", \"content\": \"" + question.getMessage() + "\"}]}";

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);

        // Executa a requisição e obtém a resposta
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                return EntityUtils.toString(responseEntity);
            }
        }
        return null;
    }

    @Override
    public String questionImage(Question question) throws JSONException, UnsupportedEncodingException {
        String base64Image = question.getImageBase64();
        String apiUrl = API_URL;

        // Configuração da requisição HTTP
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiUrl);

        // Cabeçalhos da requisição
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + API_KEY);

        // Corpo da requisição
        String json = "{ \"model\": \"" + MODEL_NAME_IMAGE + "\", \"messages\": [{ \"role\": \"user\", \"content\": [{\"type\": \"text\", \"text\": \"" + question.getMessage() + "\"}, {\"type\": \"image_url\", \"image_url\": {\"url\": \"data:image/jpeg;base64," + base64Image + "\"}}]}]}";

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);

        // Executa a requisição e obtém a resposta
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                return EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
