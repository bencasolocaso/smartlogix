package com.smartlogix.inventory.domain.repository;

import com.smartlogix.inventory.domain.model.Product;
import com.smartlogix.inventory.domain.model.Sku;
import java.util.Optional;

/**
 * Puerto de salida para persistencia de productos.
 * Las implementaciones viven en la capa de infraestructura.
 */
public interface ProductRepository {
    /**
     * Busca un producto por su SKU único.
     * @param sku el SKU del producto
     * @return Optional con el producto si existe
     */
    Optional<Product> findBySku(Sku sku);

    /**
     * Guarda o actualiza un producto.
     * @param product el producto a guardar
     * @return el producto guardado
     */
    Product save(Product product);
}
