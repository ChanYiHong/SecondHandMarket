package hcy.secondhandmarket.controller;

import hcy.secondhandmarket.dto.item.ItemResponseDTO;
import hcy.secondhandmarket.security.dto.MemberDTO;
import hcy.secondhandmarket.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final ItemService itemService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal MemberDTO memberDTO, Model model) {

        List<ItemResponseDTO> result = itemService.getListMostLike();

        model.addAttribute("items", result);
        model.addAttribute("memberDTO", memberDTO);

        return "/index";
    }

//    @GetMapping("/header")
//    public String temp() {
//
//        return "/layout/headers";
//
//    }
}
