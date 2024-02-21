package mate.academy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import mate.academy.service.BookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class IsbnValidator implements ConstraintValidator<Isbn, String> {
    private final BookService bookService;

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isNotEmpty(isbn)) {
            return isValidIsbn10(isbn) || isValidIsbn13(isbn);
        }
        return true;
    }

    private boolean isValidIsbn10(String isbn) {
        String regex = "^(?:\\d{9}[\\d|Xx])|(?:\\d{1,5}-\\d{1,7}-\\d{1,6}-[\\d|Xx])$";
        return Pattern.matches(regex, isbn);
    }

    private boolean isValidIsbn13(String isbn) {
        String regex = "^(?:\\d{12}\\d|[\\d|-]{1,5}-\\d{1,7}-\\d{1,6}-\\d)$";
        return Pattern.matches(regex, isbn);
    }
}
