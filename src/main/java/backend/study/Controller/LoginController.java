package backend.study.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="
                + client_id + "&redirect_uri=" + redirect_uri;
        model.addAttribute("location", location);
        return "Login";
    }

    // @GetMapping("/")
    // public ResponseEntity<?> callback(@RequestParam("code") String code, HttpSession session) {
    //     String accessToken = kakaoService.getAccessTokenFromKakao(code);
    //     var userInfo = kakaoService.getUserInfo(accessToken);

    //     // DB에 저장 또는 조회
    //     User user = userService.findOrSave(userInfo);
    //     session.setAttribute("LoginUser", user);
    //     session.setAttribute("accessToken", accessToken);

    //     log.info("Authorization code: {}", code);
    //     log.info("Access Token: {}", accessToken);

    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    @GetMapping("/")
    public String callback(@RequestParam("code") String code, HttpSession session) {
    String accessToken = kakaoService.getAccessTokenFromKakao(code);
    var userInfo = kakaoService.getUserInfo(accessToken);

    User user = userService.findOrSave(userInfo);
    session.setAttribute("LoginUser", user);
    session.setAttribute("accessToken", accessToken);

    log.info("Authorization code: {}", code);
    log.info("Access Token: {}", accessToken);
    log.info("로그인된 사용자: {}", user.getNickname());
    log.info("email: {}", userInfo.getKakaoAccount().getEmail());

    // 메뉴 리스트 페이지로 이동
    return "redirect:/menu/list";
}

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 로그인 User와 accessToken 가져오기
        User loginUser = (User) session.getAttribute("LoginUser");
        String accessToken = (String) session.getAttribute("accessToken");

        if (loginUser != null && accessToken != null) {
            kakaoService.kakaoLogout(accessToken);
            log.info("User {} logged out from Kakao", loginUser.getNickname());
        }

        // 세션 초기화
        session.invalidate();

        // 로그아웃 후 홈 페이지 이동
        return "redirect:/";
    }
}
