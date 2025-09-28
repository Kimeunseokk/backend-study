package backend.study.Service;

import org.springframework.stereotype.Service;

import backend.study.Dto.KakaoUserInfoResponseDto;
import backend.study.Entity.User;
import backend.study.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    /**
     * 카카오 사용자 정보를 기반으로 DB 조회 후 없으면 저장
     * @param dto KakaoUserInfoResponseDto
     * @return User 엔터티
     */
    public User findOrSave(KakaoUserInfoResponseDto dto) {
        Optional<User> existingUser = userRepository.findByKakaoId(dto.getId());

        if (existingUser.isPresent()) {
            log.info("기존 사용자 로그인: KakaoId={}", dto.getId());
            return existingUser.get();
        }

        // DB에 사용자 저장
        User user = new User();
        user.setKakaoId(dto.getId());
        user.setNickname(dto.getKakaoAccount().getProfile().getNickName());
        user.setProfileImageUrl(dto.getKakaoAccount().getProfile().getProfileImageUrl());
        user.setEmail(dto.getKakaoAccount().getEmail());

        User savedUser = userRepository.save(user);
        log.info("신규 사용자 저장: KakaoId={}, Nickname={}", savedUser.getKakaoId(), savedUser.getNickname());

        return savedUser;
    }
}
