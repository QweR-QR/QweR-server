package qwer.qwerserver.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UrlPostRequestDto {

    private String url;
    private List<String> secondFilteringWords;
}
