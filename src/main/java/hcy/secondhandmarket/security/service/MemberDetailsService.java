package hcy.secondhandmarket.security.service;

import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.repository.member.MemberRepository;
import hcy.secondhandmarket.security.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service // Bean으로 등록되면, 스프링 시큐리티에서 자동으로 UserDetailsService로 인식.
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MemberDetailsService loadUserByUsername {}", username);

        Optional<Member> result = memberRepository.findByEmail(username, false);

        if(result.isEmpty()) {
            throw new UsernameNotFoundException("Check Email or Social");
        }

        Member member = result.get();

        log.info("----------------------------------------------");
        log.info("Member : {}", member);

        MemberDTO memberDTO = new MemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.isFromSocial(),
                member.getPhoneNumber(),
                member.getRating(),
                member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toList())
        );

        memberDTO.setName(member.getName());

        return memberDTO;

    }
}
