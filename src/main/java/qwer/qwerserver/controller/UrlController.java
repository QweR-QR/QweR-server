package qwer.qwerserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import qwer.qwerserver.dto.UrlPostRequestDto;
import qwer.qwerserver.dto.UrlPostResponseDto;
import qwer.qwerserver.service.GptService;
import qwer.qwerserver.service.UrlService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UrlController {

    private static final String TEXT_SERVER_ENDPOINT = "http://203.252.166.225:5000/extract";
    private static final String TEXT_SERVER_ENDPOINT_LOCAL = "http://127.0.0.1:5000/extract";

    private final GptService gptService;
    private final UrlService urlService;

    @PostMapping("/summary")
    public UrlPostResponseDto getUrl(@RequestBody UrlPostRequestDto request) {

        String url = request.getUrl();
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("url", url);

        List<String> secondFilteringWords = request.getSecondFilteringWords();
        log.info("secondFilteringWord={}", secondFilteringWords.get(0));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");

        RestTemplate rt = new RestTemplate();
        //text extract server 로 보낼 request 생성
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(urlMap, headers);

        //Text Server에 POST 요청을 보내고 응답 받기
        ResponseEntity<Map> responseEntity =  rt.exchange(TEXT_SERVER_ENDPOINT, HttpMethod.POST, requestEntity, Map.class);
        String content = (String) responseEntity.getBody().get("content");

        log.info("추출된 텍스트={}", content);

        //gpt로 요약하기
        String summarizeByGpt = gptService.getSummarizeByGpt(content);

        return urlService.getUrlResponse(summarizeByGpt);
    }

}
