package com.natrumax.services.interfaces;

import com.natrumax.dto.request.CreateProductRequest;
import com.natrumax.dto.request.UpdateProductRequest;
import com.natrumax.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByStatus(boolean status);

    Optional<Product> getProductById(Long id);

    Page<Product> getProductsByPaging(int page, int size);

    Product createProduct(CreateProductRequest request);

    Product updateProduct(Long id, UpdateProductRequest request, MultipartFile imageFile);
}
