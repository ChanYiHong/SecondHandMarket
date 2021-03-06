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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;
    private final ItemService itemService;

    @ModelAttribute("memberDTO")
    public MemberDTO memberResponseDTO(@AuthenticationPrincipal MemberDTO memberDTO) {
        return memberDTO;
    }

    // 해당 아이템에 요청된 내역..
    @GetMapping("/{itemId}")
    public String offerList(@PathVariable("itemId") Long itemId
            ,@ModelAttribute PageRequestDTO pageRequestDTO, Model model
    ) {

        PageResponseDTO<Object[], OfferResponseDTO> result = offerService.getList(pageRequestDTO, itemId);

        model.addAttribute("result", result);

        return "/offers/list";

    }

    // 내 아이템에 요청된 내역들..
    @GetMapping
    public String offerList(@ModelAttribute PageRequestDTO pageRequestDTO, @AuthenticationPrincipal MemberDTO memberDTO, Model model) {

        log.info("Offer list owner email : {}", memberDTO.getEmail());

        PageResponseDTO<Object[], OfferResponseDTO> result = offerService.getListMyPage(pageRequestDTO, memberDTO.getEmail());

        model.addAttribute("result", result);

        return "/offers/owner";

    }

    @GetMapping("/new/{itemId}")
    public String createOffer(@PathVariable("itemId") Long itemId, Model model) {

        ItemResponseDTO item = itemService.getOne(itemId);

        model.addAttribute("item", item);
        model.addAttribute("offerSaveDTO", new OfferSaveDTO());

        return "/offers/new";

    }

    @PostMapping("/new/{itemId}")
    public String saveOffer(@PathVariable("itemId") Long itemId,
                            @Validated  @ModelAttribute OfferSaveDTO offerSaveDTO, BindingResult bindingResult,
                            @AuthenticationPrincipal MemberDTO memberDTO, Model model) {

        if(bindingResult.hasErrors()) {
            log.info("errors : {}", bindingResult);
            ItemResponseDTO item = itemService.getOne(itemId);
            model.addAttribute("item", item);
            return "/offers/new";
        }

        offerSaveDTO.setEmail(memberDTO.getEmail());

        log.info("Offer Save : {}", offerSaveDTO);

        offerService.save(offerSaveDTO);

        return "redirect:/";

    }

    @PostMapping("/{id}/deny")
    public String denyOffer(@PathVariable("id") Long offerId) {

        offerService.denyOffer(offerId);

        return "redirect:/offers";

    }

    @PostMapping("/{id}/nego")
    public String negoOffer(@PathVariable("id") Long offerId) {

        offerService.startNegotiation(offerId);

        return "redirect:/offers";

    }

    @PostMapping("/chat")
    public String offerChat(@RequestParam("id") Long id, Model model, @AuthenticationPrincipal MemberDTO memberDTO) {

        log.info("Chatting request,,, offer id : {}", id);

        OfferResponseDTO result = offerService.getOne(id);

        model.addAttribute("result", result);
        model.addAttribute("username", memberDTO.getName());

        log.info("Chatting processing...");

        return "/chat/offer-chat";

    }

}
