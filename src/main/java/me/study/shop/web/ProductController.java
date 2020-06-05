package me.study.shop.web;

import lombok.RequiredArgsConstructor;
import me.study.shop.dto.ProductRequestDto;
import me.study.shop.dto.ProductResponseDto;
import me.study.shop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static me.study.shop.mapper.ProductMapper.INSTANCE;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/product")
	public Long save(@RequestBody ProductRequestDto dto) {
		return productService.save(INSTANCE.toProductEntity(dto)).getId();
	}

	@GetMapping("/api/v1/products")
	public Page<ProductResponseDto> list(Pageable pageable) {
		return productService.findProducts(pageable)
			.map(INSTANCE::toProductResponseDto);
	}
}
