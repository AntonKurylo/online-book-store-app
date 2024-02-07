package mate.academy;

import java.math.BigDecimal;
import mate.academy.model.Book;
import mate.academy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBookStoreAppApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner getCommandLineRunner() {
        return args -> {
            // create books
            Book harryPotter = new Book();
            harryPotter.setTitle("Harry Potter");
            harryPotter.setAuthor("Joanne Kathleen Rowling");
            harryPotter.setIsbn("978-1-940313-09-2");
            harryPotter.setPrice(new BigDecimal(999));

            Book aliceInWonderland = new Book();
            aliceInWonderland.setTitle("Alice in Wonderland");
            aliceInWonderland.setAuthor("Lewis Carroll");
            aliceInWonderland.setIsbn("978-966-8602-34-4");
            aliceInWonderland.setPrice(new BigDecimal(777));

            System.out.println(harryPotter);
            System.out.println(aliceInWonderland);

            // save books
            bookService.save(harryPotter);
            bookService.save(aliceInWonderland);

            // find all books
            System.out.println(bookService.findAll());
        };
    }

}
