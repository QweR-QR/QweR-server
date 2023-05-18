package qwer.qwerserver.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import qwer.qwerserver.dto.UrlPostResponseDto;


@Slf4j
@Service
@RequiredArgsConstructor
public class UrlService {

    private final ModelMapper mapper = new ModelMapper();

    public UrlPostResponseDto getUrlResponse(String content, boolean isPassed) {
        UrlPostResponseDto response = new UrlPostResponseDto();
        response.setContent(content);
        response.setMessage("success");
        response.setPassed(isPassed);
        return mapper.map(response, UrlPostResponseDto.class);
    }

    //2차 필터링 단어 리스트의 단어가 합쳐서 2번 이상 감지되면 false
    public boolean isSecondFilteringPassed(String content, List<String> secondFilteringWords) {
        int cnt = 0;
        for (String secondFilteringWord : secondFilteringWords) {
            Pattern pattern = Pattern.compile(secondFilteringWord);
            Matcher m = pattern.matcher(content);

            while (m.find()) {
                cnt++;
            }
        }

        if (cnt >= 2) {
            return false;
        }

        return true;
    }
}
