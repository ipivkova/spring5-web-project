package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author iva = new Author("Iva", "Car");
        Book testBook = new Book("Test Book", "1234", "Publisher Name");
        iva.getBooks().add(testBook);
        testBook.getAuthors().add(iva);

        authorRepository.save(iva);
        bookRepository.save(testBook);

        Author rod = new Author("Rod", "Johnson");
        Book j2eeDev = new Book("J2EE Development without EJB", "2344", "Work");
        rod.getBooks().add(j2eeDev);

        authorRepository.save(rod);
        bookRepository.save(j2eeDev);
    }
}
