package hcy.secondhandmarket.service.member;

import hcy.secondhandmarket.dto.member.MemberInfoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void getMemberInfoTest() throws Exception {

        MemberInfoDTO memberInfo = memberService.getMemberInfo("user1@hcy.com");

        System.out.println(memberInfo);

    }

}