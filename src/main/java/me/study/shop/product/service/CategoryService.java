package me.study.shop.product.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.product.domain.Category;
import me.study.shop.product.dto.CategoryRequestDto;
import me.study.shop.product.exception.NotFoundCategoryException;
import me.study.shop.product.repository.CategoryRepository;
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
