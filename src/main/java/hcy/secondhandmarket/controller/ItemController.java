package hcy.secondhandmarket.controller;

import hcy.secondhandmarket.dto.category.CategoryResponseDTO;
import hcy.secondhandmarket.dto.item.ItemModifyDTO;
import hcy.secondhandmarket.dto.item.ItemResponseDTO;
import hcy.secondhandmarket.dto.item.ItemSaveDTO;
import hcy.secondhandmarket.dto.page.PageRequestDTO;
import hcy.secondhandmarket.dto.page.PageResponseDTO;
import hcy.secondhandmarket.dto.sidoarea.SidoAreaResponseDTO;
import hcy.secondhandmarket.repository.item.ItemSearch;
import hcy.secondhandmarket.security.dto.MemberDTO;
import hcy.secondhandmarket.service.category.CategoryService;
import hcy.secondhandmarket.service.emdarea.EmdAreaService;
import hcy.secondhandmarket.service.item.ItemService;
import hcy.secondhandmarket.service.sidoarea.SidoAreaService;
import hcy.secondhandmarket.service.siggarea.SiggAreaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    @ModelAttribute("memberDTO")
    public MemberDTO memberResponseDTO(@AuthenticationPrincipal MemberDTO memberDTO) {
        return memberDTO;
    }

    // Item 생성. 카테고리, 지역정보를 선택할 수 있고, 사용자 정보는 자동으로 들어가도록 한다.
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/new")
    public String createItem(Model model) {
        List<CategoryResponseDTO> categories = categoryService.findAll();

//        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("categories", categories);
        model.addAttribute("itemSaveDTO", new ItemSaveDTO());

        return "/items/new";
    }

    /** Item 새로 생성 : POST **/
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/new")
    public String saveItem(@Validated @ModelAttribute ItemSaveDTO itemSaveDTO, BindingResult bindingResult,
                           @AuthenticationPrincipal MemberDTO memberDTO, Model model) {

        if(bindingResult.hasErrors()) {
            log.info("errors : {}", bindingResult);
            List<CategoryResponseDTO> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            return "/items/new";
        }

        log.info("Item Save : {}", itemSaveDTO);

        itemService.save(itemSaveDTO, memberDTO);

        return "redirect:/";

    }

    /** 최초 item 목록 화면 **/
    @PreAuthorize("permitAll()")
    @GetMapping
    public String itemList(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {
        log.info("Get Item List");

        PageResponseDTO<Object[], ItemResponseDTO> result = itemService.getList(pageRequestDTO);

        model.addAttribute("result", result);

        return "/items/list";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") Long id, Model model) {

        log.info("Get Item Id : {}", id);

        ItemResponseDTO result = itemService.getOne(id);

        model.addAttribute("item", result);

        return "/items/read";

    }

    /** GET : Item 수정 화면 **/
    @GetMapping("/modify/{id}")
    public String modifyForm(@PathVariable("id") Long id, Model model) {

        ItemResponseDTO item = itemService.getOne(id);

        model.addAttribute("item", item);

        return "/items/modify";

    }

    /** POST : Item 수정 **/
    @PostMapping("/modify/{id}")
    public String modifyItem(@PathVariable("id") Long id, @ModelAttribute ItemModifyDTO itemModifyDTO) {

        log.info("Item Modify {} : {}", id, itemModifyDTO);

        itemService.modifyItem(itemModifyDTO);

        return "redirect:/items";
    }

    /** Item 제거. **/
    @PostMapping("/remove/{id}")
    public String removeItem(@PathVariable("id") Long id) {

        log.info("Item remove {}", id);

        itemService.removeItem(id);

        return "redirect:/items";

    }

    // 회원이 등록한 item 리스트
    @GetMapping("/info/{email}")
    public String memberInfoItemList(@PathVariable("email") String email, @ModelAttribute PageRequestDTO pageRequestDTO, Model model) {

//        email = URLDecoder.decode(email, "UTF-8");

        log.info("Member Info Item List / email : {}", email);

        PageResponseDTO<Object[], ItemResponseDTO> result = itemService.getListByEmail(email, pageRequestDTO);

        model.addAttribute("result", result);

        return "/items/list";

    }

    /** Item 검색 **/
    @GetMapping("/search")
    public String itemSearchResult(@RequestParam("title") String title, PageRequestDTO pageRequestDTO,
                                   Model model) {

        log.info("Item Search title : {}", title);

        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setTitle(title);

        PageResponseDTO<Object[], ItemResponseDTO> result = itemService.getListBySearchCond(itemSearch, pageRequestDTO);

        model.addAttribute("result", result);
        model.addAttribute("searchTitle", title);

        return "/items/search";

    }

}
