package hcy.secondhandmarket.controller;

import hcy.secondhandmarket.dto.category.CategoryResponseDTO;
import hcy.secondhandmarket.dto.item.ItemResponseDTO;
import hcy.secondhandmarket.dto.item.ItemSaveDTO;
import hcy.secondhandmarket.service.category.CategoryService;
import hcy.secondhandmarket.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    // Item 생성. 카테고리, 지역정보를 선택할 수 있고, 사용자 정보는 자동으로 들어가도록 한다.
    @GetMapping("/new")
    public String createItem(Model model) {
        List<CategoryResponseDTO> categories = categoryService.findAll();

        model.addAttribute("categories", categories);

        return "/items/new";
    }

    @PostMapping("/new")
    public String saveItem(@ModelAttribute ItemSaveDTO itemSaveDTO) {

        log.info("Item Save : {}", itemSaveDTO);

        itemService.save(itemSaveDTO);

        return "redirect:/";

    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") Long id, Model model) {

        log.info("Get Item Id : {}", id);

        ItemResponseDTO result = itemService.getOne(id);

        model.addAttribute("item", result);

        return "/items/read";

    }

}
