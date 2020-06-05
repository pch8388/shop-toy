package me.study.shop.mapper;

import me.study.shop.domain.Product;
import me.study.shop.dto.ProductRequestDto;
import me.study.shop.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	Product toProductEntity(ProductRequestDto dto);

	ProductResponseDto toProductResponseDto(Product products);
}
