package com.smartlogix.inventory.infrastructure.factory;

import com.smartlogix.inventory.domain.model.Product;
import com.smartlogix.inventory.domain.model.Sku;
import com.smartlogix.inventory.infrastructure.persistence.ProductEntity;

public class ProductFactory {

    public static Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Product(new Sku(entity.getSku()), entity.getName(), entity.getStockQuantity());
    }

    public static ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductEntity(product.getSku().value(), product.getName(), product.getStockQuantity());
    }
}
