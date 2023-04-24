package qwer.qwerserver.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class UrlController {

    private static final String TEXT_SERVER_ENDPOINT = "http://203.252.166.225:5000/extract";

    @GetMapping("/summary")
    public String getUrl(@RequestBody HashMap<String, String> url, @RequestParam String[] secondFilteringWords) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");

        RestTemplate rt = new RestTemplate();
        //text extract server 로 보낼 request 생성
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(url, headers);

        //POST 요청을 보내고 응답 받기
        ResponseEntity<Map> responseEntity =  rt.exchange(TEXT_SERVER_ENDPOINT, HttpMethod.POST, requestEntity, Map.class);
        System.out.println(responseEntity);

        return "ok";
    }

}
