package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class itemController {

    private final ItemService itemService;

    /**
     * 상품 등록 폼
     */
    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    /**
     * 상품 등록 기능
     */
    @PostMapping("/items/new")
    public String create(BookForm form){
        //객체 만들어서 객체로 넘겨주기
        Book book = new Book();

        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);

        return "redirect:/";
    }

    /**
     * 상품 목록 조회 기능
     */
    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Book item = (Book) itemService.findOne(itemId);

        //Book entity를 보내는 것이 아니라, BookForm을 보낼 것이다.
        BookForm form = new BookForm();

        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    /**
     * 변경 감지를 이용한 코드 작성
     * 상품 수정 기능, 권장 코드
     */
    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId,@ModelAttribute("form") BookForm form) {
        itemService.updateItem(itemId, form.getName(), form.getPrice());
        return "redirect:/items";
    }

}
