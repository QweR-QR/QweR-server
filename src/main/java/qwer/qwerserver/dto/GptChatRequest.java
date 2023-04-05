package qwer.qwerserver.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * https://api.openai.com/v1/chat/completion 으로 보낼 요청 body
 */
@Component
@Getter
@Setter
public class GptChatRequest {

    private String model;
    private Integer temperature;
    private Integer max_tokens;
    private List<Messages> messages;

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert GptChatRequest to JSON");
        }
    }

}
