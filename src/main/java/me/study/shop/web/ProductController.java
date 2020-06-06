package me.study.shop.web;

import lombok.RequiredArgsConstructor;
import me.study.shop.domain.Product;
import me.study.shop.dto.ProductRequestDto;
import me.study.shop.dto.ProductResponseDto;
import me.study.shop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static me.study.shop.mapper.ProductMapper.PRODUCT_MAPPER;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/product")
	public Long save(@RequestBody ProductRequestDto dto) {
		return productService.save(PRODUCT_MAPPER.toProductEntity(dto)).getId();
	}

	@GetMapping("/api/v1/products")
	public Page<ProductResponseDto> list(Pageable pageable) {
		return productService.findProducts(pageable)
			.map(PRODUCT_MAPPER::toProductResponseDto);
	}

	@GetMapping("/api/v1/product/{productId}")
	public ProductResponseDto detail(@PathVariable Long productId) {
		return PRODUCT_MAPPER.toProductResponseDto(productService.findProduct(productId));
	}
}
