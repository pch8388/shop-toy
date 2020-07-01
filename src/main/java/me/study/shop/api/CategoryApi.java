package me.study.shop.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.study.shop.dto.CategoryRequestDto;
import me.study.shop.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"4. Category"})
@RequiredArgsConstructor
@RestController
public class CategoryApi {

    private final CategoryService categoryService;

    @ApiOperation(value = "카테고리 등록", notes = "카테고리를 등록한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/categories/{categoryId}")
    public Long register(
        @PathVariable Long categoryId,
        @RequestBody CategoryRequestDto dto) {
        return categoryService.register(categoryId, dto);
    }
}
