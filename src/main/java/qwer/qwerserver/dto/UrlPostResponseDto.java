package qwer.qwerserver.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UrlPostResponseDto {

    private String message;
    private String content;
}
