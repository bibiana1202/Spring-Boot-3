package me.parkhyejung.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 프리젠테이션 계층

@RestController // 라우터 역할을 하는 애너테이션
public class TestController {
    @Autowired // TestService 에 빈 주입
    TestService testService;

    @GetMapping("/test")
    public List<Member> getAllMembers(){
        List<Member> members = testService.getAllMembers();
        return members;
    }
}
