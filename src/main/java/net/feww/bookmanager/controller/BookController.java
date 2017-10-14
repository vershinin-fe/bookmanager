package net.feww.bookmanager.controller;

import net.feww.bookmanager.model.Paging;
import net.feww.bookmanager.service.BookService;
import net.feww.bookmanager.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.List;

@Controller
public class BookController {
    private BookService bookService;
    private Paging paging = new Paging();
    private String searchString = "";

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;

        paging.setCurrentCount(bookService.getCurrentCount(searchString));
    }

    @RequestMapping(value = "books/{page}", method = RequestMethod.GET)
    public String listBooks(@PathVariable int page, Model model){
        paging.setCurrentPage(page);

        model.addAttribute("paging", paging);
        model.addAttribute("searchString", searchString);

        List<Book> bookList = this.bookService.listBooks(paging, searchString);

        model.addAttribute("listBooks", bookList);

        return "booksForm";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") @Validated Book book){
        this.bookService.addBook(book);

        paging.setCurrentCount(bookService.getCurrentCount(searchString));

        return "redirect:/books/0";
    }

    @RequestMapping(value = "/books/update", method = RequestMethod.POST)
    public String updateBook(@ModelAttribute("book") @Validated Book book){
        Book oldBook = this.bookService.getBookById(book.getId());

        if(!book.equals(oldBook)){
            book.setReadAlready(false);
            this.bookService.updateBook(book);
        }

        return "redirect:/books/0";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id){
        this.bookService.removeBook(id);

        paging.setCurrentCount(bookService.getCurrentCount(searchString));

        if(paging.getCurrentPage() == paging.getPagesCount()
                && (paging.getCurrentCount() % Paging.MAX_LINES_PER_PAGE) == 0){
            paging.decrementPage();
        }

        return "redirect:/books/" + paging.getCurrentPage();
    }

    @RequestMapping(value = "/books/new", method = RequestMethod.GET)
    public String showAddUserForm(Model model) {

        Book newBook = new Book();

        // set default value
        newBook.setTitle("");
        newBook.setDescription("");
        newBook.setAuthor("");
        newBook.setIsbn("");
        newBook.setPrintYear((Calendar.getInstance().get(Calendar.YEAR)));
        newBook.setReadAlready(false);
        model.addAttribute("newBook", newBook);

        return "newBookForm";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("updatedBook", this.bookService.getBookById(id));

        return "editBookForm";
    }

    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model){
        Book book = this.bookService.getBookById(id);

        if(!book.isReadAlready()){
            book.setReadAlready(true);
            this.bookService.updateBook(book);
        }

        model.addAttribute("book", book);
        model.addAttribute("paging", paging);

        return "bookDataForm";
    }

    @RequestMapping("books/search")
    public String search(@RequestParam ("searchstring") String searchString){

        this.searchString = searchString;

        paging.setCurrentCount(bookService.getCurrentCount(searchString));

        return "redirect:/books/0";
    }
}
