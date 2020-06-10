package me.study.shop.web;

import lombok.RequiredArgsConstructor;
import me.study.shop.dto.ProductRequestDto;
import me.study.shop.dto.ProductResponseDto;
import me.study.shop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/product")
	public Long save(@RequestBody @Valid ProductRequestDto dto) {
		return productService.save(dto.toEntity()).getId();
	}

	@GetMapping("/api/v1/product")
	public Page<ProductResponseDto> list(Pageable pageable) {
		return productService.findProducts(pageable)
			.map(ProductResponseDto::of);
	}

	@GetMapping("/api/v1/product/{productId}")
	public ProductResponseDto detail(@PathVariable Long productId) {
		return ProductResponseDto.of(productService.findProduct(productId));
	}
}
