package me.study.shop.product.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import me.study.shop.product.dto.ProductRequestDto;
import me.study.shop.product.dto.ProductResponseDto;
import me.study.shop.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"3. Product"})
@RestController
@RequiredArgsConstructor
public class ProductApi {

	private final ProductService productService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/products")
	@ApiOperation(value = "상품 등록", notes = "상품을 등록한다")
	public Long save(@RequestBody @Valid ProductRequestDto dto) {
		return productService.save(dto.toEntity()).getId();
	}

	@GetMapping("/api/v1/products")
	@ApiOperation(value = "상품 목록 조회", notes = "상품을 조회한다")
	public Page<ProductResponseDto> list(Pageable pageable) {
		return productService.findProducts(pageable)
			.map(ProductResponseDto::of);
	}

	@GetMapping("/api/v1/products/{productId}")
	@ApiOperation(value = "상품 상세조회", notes = "상품 상세정보를 조회한다")
	public ProductResponseDto detail(@PathVariable @ApiParam(value = "조회할 상품 id") Long productId) {
		return ProductResponseDto.of(productService.findProduct(productId));
	}
}
