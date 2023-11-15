package com.mirea.book.catalog

import java.util.UUID
import java.util.concurrent.TimeUnit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping('/books')
public class BookController {

    @Autowired
    private BookRepository bookRepository

    @GetMapping('/list')
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll())
    }

    @GetMapping('/{id}')
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
            .map(book -> ResponseEntity.ok().body(book))
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book()
        
        book.setAuthor(bookDTO.getAuthor())
        book.setTitle(bookDTO.getTitle())
        book.setYear(bookDTO.getYear())
        book.setGenre(bookDTO.getGenre())
        
        return ResponseEntity.ok(bookRepository.save(book))
    }

    @PutMapping('/{id}')
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO updatedBook) {
        return bookRepository.findById(id)
            .map(book -> {
                book.setAuthor(updatedBook.getAuthor())
                book.setTitle(updatedBook.getTitle())
                book.setYear(updatedBook.getYear())
                book.setGenre(updatedBook.getGenre())
                Book savedBook = bookRepository.save(book)
                return ResponseEntity.ok(savedBook)
            })
            .orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping('/{id}')
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        return bookRepository.findById(id)
            .map(book -> {
                bookRepository.delete(book)
                return ResponseEntity.ok('Book deleted successfully')
            })
            .orElse(ResponseEntity.notFound().build())
    }
}