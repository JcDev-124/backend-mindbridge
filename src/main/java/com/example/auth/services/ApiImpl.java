package com.example.auth.services;

import com.example.auth.domain.questions.Question;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

public class ApiImpl implements ApiInterface {

    @Value("${api.gpt}")
    private static String API_KEY;

    private static String sendRequest(JSONObject requestBody) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject responseJson = new JSONObject(response.body());
            return responseJson.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

        } catch (Exception e) {
            e.printStackTrace();
            return "Error communicating with OpenAI API: " + e.getMessage();
        }
    }

    @Override
    public String questionText(Question question) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-4"); // Modelo escolhido

        // Adicionando a mensagem do usuário
        JSONArray messages = new JSONArray();
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", question.getMessage());
        messages.put(userMessage);

        requestBody.put("messages", messages);
        requestBody.put("max_tokens", 300);


        return sendRequest(requestBody);
    }

    @Override
    public String questionImage(Question question) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-4"); // Modelo escolhido

        JSONArray messages = new JSONArray();
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");

        JSONArray contentArray = new JSONArray();
        contentArray.put(new JSONObject().put("type", "text").put("text", question.getMessage()));
        contentArray.put(new JSONObject().put("type", "image").put("image_url", "data:image/png;base64," + question.getImageBase64()));

        userMessage.put("content", contentArray);
        messages.put(userMessage);

        requestBody.put("messages", messages);
        requestBody.put("max_tokens", 300);

        return sendRequest(requestBody);
    }
}
