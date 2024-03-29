package mate.academy.service;

import java.util.List;
import mate.academy.dto.BookDto;
import mate.academy.dto.BookSearchParametersDto;
import mate.academy.dto.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    BookDto findBookById(Long id);

    List<BookDto> findAll(Pageable pageable);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParametersDto searchParameters, Pageable pageable);
}
