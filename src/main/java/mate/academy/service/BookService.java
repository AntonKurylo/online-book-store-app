package mate.academy.service;

import java.util.List;
import mate.academy.dto.BookDto;
import mate.academy.dto.CreateBookRequestDto;
import mate.academy.dto.UpdateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    BookDto findBookById(Long id);

    List<BookDto> findAll();

    BookDto updateById(Long id, UpdateBookRequestDto requestDto);

    void deleteById(Long id);
}
