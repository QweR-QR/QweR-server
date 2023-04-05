package qwer.qwerserver.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import qwer.qwerserver.dto.GptChatRequest;
import qwer.qwerserver.dto.GptChatResponse;
import qwer.qwerserver.dto.Messages;

@Slf4j
@Service
public class GptService {

    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private final String MODEL = "gpt-3.5-turbo";

    @Value("${gpt.api-key}")
    private String apiKey;


    public GptChatResponse getGPTChatResponse(String content) {
        log.info("getGPTChatResponse content={}", content);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");
        headers.add("Authorization", "Bearer " + apiKey);
        //log.info("header Authorization={}", headers.get("Authorization"));
        //log.info("header content type={}, ", headers.get("Content-Type"));

        List<Messages> messagesList = new ArrayList<>();
        Messages messages = new Messages();
        messages.setRole("user");
        messages.setContent("아래 content를 한국어로 요약해줘.\n" + content);
        messagesList.add(messages);

        GptChatRequest request = new GptChatRequest();
        request.setModel(MODEL);
        request.setMax_tokens(1000);
        request.setTemperature(1);
        request.setMessages(messagesList);

        String requestBody = request.toJson();

        RestTemplate rt = new RestTemplate();
        HttpEntity<String> gptChatRequest = new HttpEntity<>(requestBody, headers);

        return rt.exchange(
                ENDPOINT,
                HttpMethod.POST,
                gptChatRequest,
                GptChatResponse.class
        ).getBody();
    }
}
