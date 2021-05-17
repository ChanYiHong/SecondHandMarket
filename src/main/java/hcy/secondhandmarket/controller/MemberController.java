package hcy.secondhandmarket.controller;

import hcy.secondhandmarket.dto.member.MemberInfoDTO;
import hcy.secondhandmarket.dto.member.MemberSaveDTO;
import hcy.secondhandmarket.security.dto.MemberDTO;
import hcy.secondhandmarket.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{email}")
    public String myPage(@PathVariable("email") String email, Model model) {

        MemberInfoDTO result = memberService.getMemberInfo(email);

        model.addAttribute("result", result);

        return "/members/info";

    }

}
