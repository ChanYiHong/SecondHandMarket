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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String joinMember(Model model) {
        log.info("Join Member Page");
        model.addAttribute("memberSaveDTO", new MemberSaveDTO());
        return "/security/join";
    }

    @PostMapping("/join")
    public String joinMemberPost(@Validated @ModelAttribute MemberSaveDTO memberSaveDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("Error : {}", bindingResult);
            return "/security/join";
        }

        log.info("회원 가입 성공!");
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
