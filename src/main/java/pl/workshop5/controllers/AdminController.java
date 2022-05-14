package pl.workshop5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import pl.workshop5.model.Book;
import pl.workshop5.model.BookService;
import pl.workshop5.model.MockBookService;

@Controller
public class AdminController {

    BookService bookService;

    public AdminController(MockBookService mockBookService) {
        this.bookService = mockBookService;
    }

    @GetMapping("/")
    public String landingPage(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "home";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("books", bookService.getBooks());
        return "list";
    }

    @GetMapping("/add")
    public String addBook() {

        return "add";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam String addConfirmButton, @RequestParam String isbn, @RequestParam String title,
                          @RequestParam String author, @RequestParam String publisher, @RequestParam String type, Model model) {

        if (addConfirmButton.equals("yes")) {
            bookService.addBook(new Book(bookService.generateNewId(), isbn, title, author, publisher, type));
        }
        model.addAttribute("books", bookService.getBooks());
        return "list";    }

    @GetMapping("/edit")
    public String editBook(Model model, @RequestParam String id) {
        if (bookService.getBookById(Long.valueOf(id)).isPresent()) {
            model.addAttribute("book", bookService.getBookById(Long.valueOf(id)).get());
            return "edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }


    @PostMapping("/edit")
    public String addBook(@RequestParam String editConfirmButton, @RequestParam String id, @RequestParam String isbn, @RequestParam String title,
                          @RequestParam String author, @RequestParam String publisher, @RequestParam String type, Model model) {
        if (editConfirmButton.equals("yes")) {
            bookService.editBook(new Book(Long.valueOf(id), isbn, title, author, publisher, type));
        }
        model.addAttribute("books", bookService.getBooks());
        return "list";    }

    @GetMapping("/delete")
    public String deleteBook(Model model, @RequestParam String id) {
        if (bookService.getBookById(Long.valueOf(id)).isPresent()) {

            model.addAttribute("book", bookService.getBookById(Long.valueOf(id)).get());
            return "delete";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam String deleteConfirmButton, @RequestParam String id,Model model) {

        if (deleteConfirmButton.equals("yes")) {
            bookService.deleteBook(Long.valueOf(id));
        }
        model.addAttribute("books", bookService.getBooks());
        return "list";    }

}
