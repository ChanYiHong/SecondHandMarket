package hcy.secondhandmarket.controller;

import hcy.secondhandmarket.dto.offer.OfferResponseDTO;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.service.offer.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/{itemId}")
    public String offerList(@PathVariable("itemId") Long itemId
            ,@ModelAttribute PageRequestDTO pageRequestDTO, Model model
    ) {

        PageResponseDTO<Object[], OfferResponseDTO> result = offerService.getList(pageRequestDTO, itemId);

        model.addAttribute("result", result);

        return "/offers/list";

    }

}
