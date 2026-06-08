package com.smartlogix.inventory.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, String> {
}
