package me.study.shop.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class CategoryRequestDto {
    @ApiModelProperty(value = "등록할 카테고리 이름", required = true)
    private String categoryName;
}
