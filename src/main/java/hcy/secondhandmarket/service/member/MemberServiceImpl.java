package hcy.secondhandmarket.service.member;

import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.domain.MemberRole;
import hcy.secondhandmarket.dto.member.MemberSaveDTO;
import hcy.secondhandmarket.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberSaveDTO memberSaveDTO) {

        Member member = Member.builder()
                .email(memberSaveDTO.getEmail())
                .password(passwordEncoder.encode(memberSaveDTO.getPassword()))
                .phoneNumber(memberSaveDTO.getPhoneNumber())
                .name(memberSaveDTO.getName())
                .rating(5)
                .fromSocial(false)
                .build();

        member.addMemberRole(MemberRole.USER);

        memberRepository.save(member);

    }
}
