package sqlutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.ChatInnerResp;
import entity.ChatMessage;
import entity.ChatRequest;
import entity.TokenResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class gpt_yun {

    private static String getAccessToken() throws IOException {
        String apiKey = "lNiXykCWosvV8mWOt1VGAFFQ";
        String secretKey = "28VenBQWggxqSGhHlYSn4dCOCx8NpILm";
        String url = "https://aip.baidubce.com/oauth/2.0/token?" +
                "grant_type=client_credentials&" +
                "client_id=" + apiKey + "&" +
                "client_secret=" + secretKey;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write("".getBytes());
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Reading the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();
            reader.close();

            ObjectMapper mapper = new ObjectMapper();
            TokenResponse tokenResponse = mapper.readValue(response, TokenResponse.class);
            return tokenResponse.getAccess_token();
        } else {
            // Error handling
            throw new IOException("Failed to obtain access token. Response code: " + responseCode);
        }
    }

    public static String gpt(String s) {
       try {

            String que = s;
            String accessToken = getAccessToken();

            String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions" +
                    "?access_token=" + accessToken;

            ChatRequest request = new ChatRequest();
            ChatMessage message = new ChatMessage();
            message.setRole("user");
            message.setContent(que);
            request.getMessages().add(message);

            ObjectMapper mapper = new ObjectMapper();
            String requestJson = mapper.writeValueAsString(request);

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestJson.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            String response = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Reading the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                response = reader.readLine();
                reader.close();

                System.out.println(response);
            } else {
                // Error handling
                System.err.println("Failed to make chat completions. Response code: " + responseCode);
            }

            ChatInnerResp chatInnerResp = new ChatInnerResp();
            ObjectMapper objectMapper = new ObjectMapper();
            chatInnerResp = objectMapper.readValue(response, ChatInnerResp.class);
            // 下面将response信息返回客户端
            String answer = chatInnerResp.getResult();
            return answer;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
