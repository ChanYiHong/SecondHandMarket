package hcy.secondhandmarket.repository.member;

import hcy.secondhandmarket.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void memberInfoTest() throws Exception {
        List<Object[]> result = memberRepository.findByEmailWithCnt("user1@hcy.com");

        System.out.println(result.size());
        System.out.println(result.get(0)[0]);
        System.out.println(result.get(0)[1]);
       // System.out.println(result.get(0)[2]);

    }


}