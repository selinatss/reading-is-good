package com.bookstore.controler;

import com.bookstore.response.RestResponse;
import com.bookstore.service.BookService;
import com.bookstore.model.Book;
import com.bookstore.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/book/v1")
public class BookController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookService bookService;

    @PostMapping(value = "/save")
    public ResponseEntity<RestResponse> create(@RequestBody Book book) {
        try {
            Book savedBook = bookService.createBook(book);
            logger.info("Book saved successfully");
            return ResponseEntity.ok().body(RestResponse.of("id = " + savedBook.getId(), HttpStatus.OK, Constant.successMessage));
        } catch (Exception e) {
            logger.error("Error during book saved");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<RestResponse> getBook(){
        try {
            return ResponseEntity.ok().body(RestResponse.of(bookService.getAllBooks(),
                    HttpStatus.OK, Constant.successMessage));}
        catch (Exception e){
            logger.error("Error during book saved");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<RestResponse> updateBookStock(@RequestParam String id, @RequestParam int stockCount){
        try{
            bookService.updateBookStock(id, stockCount);
            logger.error("Books stock updated process");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.OK, Constant.successMessage));
        } catch (Exception e) {
            logger.error("Error during stock update process");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<RestResponse>  deleteBook(@RequestParam String id){
        try{
            bookService.deleteBooks(id);
            logger.info("Book deleted successfully");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.OK, Constant.successMessage));
        }
        catch (Exception e){
            logger.error("Error during book delete process");
            return ResponseEntity.ok().body(RestResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, Constant.errorMessage));
        }
    }
}
