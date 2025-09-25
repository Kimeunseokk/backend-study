package backend.study.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import backend.study.Dto.KakaoUserInfoResponseDto;
import backend.study.Entity.User;
import backend.study.Repository.UserRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Service
@lombok.extern.slf4j.Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    } // @RequiredArgsConstructor 이거쓰면 굳이 다시 선언할 필요가 없음


  public User findOrSave(KakaoUserInfoResponseDto dto) {
    return userRepository.findByKakaoId(dto.getId())
        .orElseGet(() -> {
            User user = new User();
            user.setKakaoId(dto.getId()); // DB PK id는 자동 증가
            user.setNickname(dto.getKakaoAccount().getProfile().getNickName());
            user.setProfileImageUrl(dto.getKakaoAccount().getProfile().getProfileImageUrl());
            return userRepository.save(user);
        });
    }
}
