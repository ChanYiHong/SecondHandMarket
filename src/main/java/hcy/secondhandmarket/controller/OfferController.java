package hcy.secondhandmarket.controller;

import hcy.secondhandmarket.dto.item.ItemResponseDTO;
import hcy.secondhandmarket.dto.offer.OfferResponseDTO;
import hcy.secondhandmarket.dto.offer.OfferSaveDTO;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.security.dto.MemberDTO;
import hcy.secondhandmarket.service.item.ItemService;
import hcy.secondhandmarket.service.offer.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;
    private final ItemService itemService;

    @GetMapping("/{itemId}")
    public String offerList(@PathVariable("itemId") Long itemId
            ,@ModelAttribute PageRequestDTO pageRequestDTO, Model model
    ) {

        PageResponseDTO<Object[], OfferResponseDTO> result = offerService.getList(pageRequestDTO, itemId);

        model.addAttribute("result", result);

        return "/offers/list";

    }

    @GetMapping("/new/{itemId}")
    public String createOffer(@PathVariable("itemId") Long itemId, Model model) {

        ItemResponseDTO item = itemService.getOne(itemId);

        model.addAttribute("item", item);

        return "/offers/new";

    }

    @PostMapping("/new/{itemId}")
    public String saveOffer(@PathVariable("itemId") Long itemId,
                            @ModelAttribute OfferSaveDTO offerSaveDTO,
                            @AuthenticationPrincipal MemberDTO memberDTO) {

        offerSaveDTO.setEmail(memberDTO.getEmail());

        log.info("Offer Save : {}", offerSaveDTO);

        offerService.save(offerSaveDTO);

        return "redirect:/";

    }

}
