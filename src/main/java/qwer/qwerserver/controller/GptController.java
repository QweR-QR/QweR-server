package qwer.qwerserver.controller;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import qwer.qwerserver.dto.GptChatResponse;
import qwer.qwerserver.service.GptService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GptController {

    private final GptService gptService;

    @GetMapping("/content")
    public String getSummarizeByGpt(@RequestParam String model, @RequestBody HashMap<String, String> content) {
        log.info("Call getSummarizeByGpt.");
        log.info("getSummarizeByGpt content={}", content.get("content"));

        GptChatResponse gptChatResponse = gptService.getGPTChatResponse(content.get("content"));
        return gptChatResponse.getChoices().get(0).getMessage().getContent();
    }

}
