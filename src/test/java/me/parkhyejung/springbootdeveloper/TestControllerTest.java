package me.parkhyejung.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
class TestControllerTest {

    @Autowired // 빈 주입
    protected MockMvc mockMvc;

    @Autowired // 빈 주입
    private WebApplicationContext context;

    @Autowired // 빈 주입
    private MemberRepository memberRepository;

    @BeforeEach // 테스트 실행 전 실행하는 메소드
    public void mockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @AfterEach // 테스트 실행 후 실행하는 메서드
    public void cleanup(){
        memberRepository.deleteAll();
    }


    @DisplayName("getAllMembers : 아티클 조회에 설공한다.")
    @Test
    public void getAllMembers() throws Exception {
        //given : 멤버를 저장한다.
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L,"홍길동"));

        //when : 멤버 리스트를 조회하는 API 를 호출 한다.
        final ResultActions result = mockMvc.perform(get(url) //1.요청을 전송
                .accept(MediaType.APPLICATION_JSON)); // 요청을 보낼 때 무슨 타입으로 응답을 받을지 결정

        //then : 응답코드가 200이고 , 반환값 중에 0번째 요소의 id 와 name이 저장된 값과 같은지 확인
        result
                .andExpect(status().isOk()) // 3. 응답을 검증 , 응답코드가 ok(200)인지 확인
                //4. json 응답값의 값을 가져오는 역할
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}