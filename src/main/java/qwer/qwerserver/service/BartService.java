package qwer.qwerserver.service;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class BartService {

    private static final String TEXT_SERVER_ENDPOINT = "http://203.252.166.225:5000/summarize";

    public String getSummarizeByBart(String content) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("content", content);

        RestTemplate rt = new RestTemplate();
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(contentMap, headers);

        ResponseEntity<Map> responseEntity = rt.exchange(TEXT_SERVER_ENDPOINT, HttpMethod.POST, requestEntity, Map.class);

        return (String) responseEntity.getBody().get("content");
    }
}
