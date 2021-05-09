package hcy.secondhandmarket.controller;

import hcy.secondhandmarket.security.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal MemberDTO memberDTO, Model model) {

        model.addAttribute("memberDTO", memberDTO);

        return "/index";
    }

}
