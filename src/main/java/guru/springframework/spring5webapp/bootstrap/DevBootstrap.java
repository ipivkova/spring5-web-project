package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author iva = new Author("Iva", "Car");
        Publisher publisher = new Publisher("P name", "P city", "P state", 123);
        Book testBook = new Book("Test Book", "1234", publisher);
        iva.getBooks().add(testBook);
        testBook.getAuthors().add(iva);

        publisherRepository.save(publisher);
        authorRepository.save(iva);
        bookRepository.save(testBook);

        Author rod = new Author("Rod", "Johnson");
        Publisher publisher1 = new Publisher("P1 name", "P1 city", "P1 state", 333);
        Book j2eeDev = new Book("J2EE Development without EJB", "2344", publisher1);
        rod.getBooks().add(j2eeDev);

        publisherRepository.save(publisher1);
        authorRepository.save(rod);
        bookRepository.save(j2eeDev);
    }
}
