package me.study.shop.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.domain.Product;
import me.study.shop.repository.ProductRepository;
import me.study.shop.exception.NotFoundProductException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

	private final ProductRepository productRepository;

	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Page<Product> findProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Product findProduct(Long productId) {
		return productRepository.findById(productId)
			.orElseThrow(NotFoundProductException::new);
	}
}
