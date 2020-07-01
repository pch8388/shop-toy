package me.study.shop.service;

import me.study.shop.domain.Category;
import me.study.shop.dto.CategoryRequestDto;
import me.study.shop.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 등록")
    public void category_register() {

        final Category parent = Category.of("PARENT");
        given(categoryRepository.findById(anyLong())).willReturn(Optional.of(parent));

        CategoryRequestDto dto = Mockito.mock(CategoryRequestDto.class);
        categoryService.register(1L ,dto);

        verify(categoryRepository).save(any());
    }
}