package me.study.shop.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.domain.Product;
import me.study.shop.domain.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Page<Product> findProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
}
