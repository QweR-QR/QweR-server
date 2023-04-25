package qwer.qwerserver.service;

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

    public UrlPostResponseDto getUrlResponse(String content) {
        UrlPostResponseDto response = new UrlPostResponseDto();
        response.setContent(content);
        response.setMessage("success");
        return mapper.map(response, UrlPostResponseDto.class);
    }
}
