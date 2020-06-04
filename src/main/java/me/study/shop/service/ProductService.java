package me.study.shop.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.domain.Product;
import me.study.shop.domain.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public void register(Product product) {
		productRepository.save(product);
	}
}
