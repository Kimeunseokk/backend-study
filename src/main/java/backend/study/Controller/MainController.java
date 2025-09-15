package backend.study.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")  // 루트 경로
    public String getMain() {
        return "Main"; // templates/Main.html
    }
}
