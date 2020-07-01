package me.study.shop.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.domain.Category;
import me.study.shop.dto.CategoryRequestDto;
import me.study.shop.exception.NotFoundCategoryException;
import me.study.shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long register(Long categoryId, CategoryRequestDto dto) {
        final Category parent = categoryRepository.findById(categoryId)
            .orElseThrow(NotFoundCategoryException::new);

        final Category child = Category.of(dto.getCategoryName());
        parent.addChildCategory(child);
        categoryRepository.save(child);
        return child.getId();
    }
}
