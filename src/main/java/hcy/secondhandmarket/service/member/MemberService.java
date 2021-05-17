package hcy.secondhandmarket.service.member;

import hcy.secondhandmarket.domain.Member;
import hcy.secondhandmarket.dto.member.MemberInfoDTO;
import hcy.secondhandmarket.dto.member.MemberSaveDTO;

public interface MemberService {

    void join(MemberSaveDTO memberSaveDTO);

    MemberInfoDTO getMemberInfo(String email);

}
