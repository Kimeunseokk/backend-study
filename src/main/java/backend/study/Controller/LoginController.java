package backend.study.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.study.Dto.KakaoUserInfoResponseDto;
import backend.study.Entity.User;
import backend.study.Service.KakaoService;
import backend.study.Service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final KakaoService kakaoService;
    private final UserService userService;

    @Value("${kakao.client_id}")
    private String client_id;

    @Value("${kakao.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/page")
    public String loginPage(Model model) {
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+client_id+"&redirect_uri="+redirect_uri;
        model.addAttribute("location", location);

        return "Login";
    }
    @GetMapping("/")
    public ResponseEntity<?> callback(@RequestParam("code") String code, HttpSession session) {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);
        
        User user = userService.findOrSave(userInfo); // DB에 저장/조회
        session.setAttribute("LoginUser", user);
        session.setAttribute("accessToken", accessToken); // 세션에 저장
        log.info("Authorization code: {}", code);
        log.info("Access Token: {}", accessToken);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 1️⃣ 세션에서 로그인 정보와 accessToken 가져오기
        KakaoUserInfoResponseDto loginUser = (KakaoUserInfoResponseDto) session.getAttribute("LoginUser");

        if (loginUser != null) {
            // 2️⃣ 카카오 로그아웃 API 호출
            String accessToken = (String) session.getAttribute("accessToken"); // 로그인 시 세션에 저장한 accessToken
            if (accessToken != null) {
                kakaoService.kakaoLogout(accessToken);
            }
        }
        // 3️⃣ 세션 초기화
        session.invalidate();
        // 4️⃣ 로그아웃 후 홈 페이지로 이동
        return "redirect:/";
    }
}