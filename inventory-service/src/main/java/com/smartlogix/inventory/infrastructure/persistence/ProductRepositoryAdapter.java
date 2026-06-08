package com.smartlogix.inventory.infrastructure.persistence;

import com.smartlogix.inventory.domain.model.Product;
import com.smartlogix.inventory.domain.model.Sku;
import com.smartlogix.inventory.domain.repository.ProductRepository;
import com.smartlogix.inventory.infrastructure.factory.ProductFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final SpringDataProductRepository repository;

    public ProductRepositoryAdapter(SpringDataProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> findBySku(Sku sku) {
        return repository.findById(sku.value())
                .map(ProductFactory::toDomain);
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductFactory.toEntity(product);
        ProductEntity savedEntity = repository.save(entity);
        return ProductFactory.toDomain(savedEntity);
    }
}
