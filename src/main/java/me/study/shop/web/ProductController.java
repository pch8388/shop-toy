package me.study.shop.web;

import lombok.RequiredArgsConstructor;
import me.study.shop.dto.ProductRequestDto;
import me.study.shop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/product")
	public Long save(@RequestBody ProductRequestDto dto) {
		return productService.save(dto.toEntity()).getId();
	}
}
