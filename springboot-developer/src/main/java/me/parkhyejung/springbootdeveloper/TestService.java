package me.parkhyejung.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 비즈니스 계층

@Service
public class TestService {
    @Autowired // 스프링 컨테이너에 있는 빈이라는 것 주입하는 역할
    MemberRepository memberRepository; // 1. 빈 주입

    public List<Member> getAllMembers(){
        return memberRepository.findAll(); // 2. 멤버 목록 얻기
    }
}
