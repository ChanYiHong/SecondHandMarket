package hcy.secondhandmarket.service.member;

import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.domain.MemberRole;
import hcy.secondhandmarket.dto.member.MemberInfoDTO;
import hcy.secondhandmarket.dto.member.MemberSaveDTO;
import hcy.secondhandmarket.repository.member.MemberRepository;
import hcy.secondhandmarket.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
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

    @Override
    public MemberInfoDTO getMemberInfo(String email) {

        List<Object[]> result = memberRepository.findByEmailWithCnt(email);

        Member member = (Member) result.get(0)[0];
        Long itemCnt = (Long) result.get(0)[1];
        Long reviewCnt = reviewRepository.findReviewCntByEmail(email);

        return MemberInfoDTO.builder()
                .email(member.getEmail())
                .nickname(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .postCnt(itemCnt.intValue())
                .reviewCnt(reviewCnt.intValue())
                .build();

    }
}
