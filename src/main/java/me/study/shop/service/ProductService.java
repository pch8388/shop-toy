package me.study.shop.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.domain.Product;
import me.study.shop.domain.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public Product save(Product product) {
		return productRepository.save(product);
	}
}
