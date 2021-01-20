package me.study.shop.product.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import me.study.shop.product.dto.CategoryRequestDto;
import me.study.shop.product.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"4. Category"})
@RequiredArgsConstructor
@RestController
public class CategoryApi {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/categories/{categoryId}")
    @ApiOperation(value = "카테고리 등록", notes = "카테고리를 등록한다.")
    public Long register(
        @PathVariable @ApiParam(value = "부모 카테고리 id") Long categoryId,
        @RequestBody CategoryRequestDto dto) {
        return categoryService.register(categoryId, dto);
    }
}
