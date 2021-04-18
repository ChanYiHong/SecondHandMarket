package hcy.secondhandmarket.controller;

import hcy.secondhandmarket.dto.member.MemberSaveDTO;
import hcy.secondhandmarket.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        log.info("Login Page");

        return "/security/login";
    }

    @GetMapping("/join")
    public String joinMember() {
        log.info("Join Member Page");

        return "/security/join";
    }

    @PostMapping("/join")
    public String joinMemberPost(@ModelAttribute MemberSaveDTO memberSaveDTO) {

        memberService.join(memberSaveDTO);

        return "redirect:/";

    }

}
