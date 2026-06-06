package com.studenttribe.eurekaservice.productservice.service.impl;

import com.studenttribe.eurekaservice.productservice.document.Product;
import com.studenttribe.eurekaservice.productservice.dto.request.ProductRequestDto;
import com.studenttribe.eurekaservice.productservice.dto.request.ProductUpdateRequestDto;
import com.studenttribe.eurekaservice.productservice.dto.response.CategoryCountDto;
import com.studenttribe.eurekaservice.productservice.dto.response.CompanyCountDto;
import com.studenttribe.eurekaservice.productservice.dto.response.ProductResponseDto;
import com.studenttribe.eurekaservice.productservice.dto.response.StatusCountDto;
import com.studenttribe.eurekaservice.productservice.enums.Category;
import com.studenttribe.eurekaservice.productservice.enums.Status;
import com.studenttribe.eurekaservice.productservice.exception.ProductNotFoundException;
import com.studenttribe.eurekaservice.productservice.mapper.ProductMapper;
import com.studenttribe.eurekaservice.productservice.repository.ProductRepository;
import com.studenttribe.eurekaservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto save(ProductRequestDto requestDto) {
        Product product = modelMapper.map(requestDto, Product.class);
        product.setRating(0F);
        product.setReviewsCount(0);
        product.setCreatedDate(LocalDateTime.now());
        product.setUpdatedDate(null);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto getById(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found With Id : " + productId));
        return productMapper.mapToResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToResponseDto)
                .toList();
    }

    @Override
    public ProductResponseDto update(String productId, ProductUpdateRequestDto productUpdateRequestDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found With Id : " + productId));
        modelMapper.map(productUpdateRequestDto, product);
        product.setUpdatedDate(LocalDateTime.now());
        Product updatedProduct = productRepository.save(product);
        return productMapper.mapToResponseDto(updatedProduct);
    }

    @Override
    public void delete(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found With Id : " + productId));
        productRepository.delete(product);
    }

    @Override
    public Page<ProductResponseDto> getAllByPage(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(productMapper::mapToResponseDto);
    }

    @Override
    public List<ProductResponseDto> getByCategory(Category category) {
        return productRepository.findByCategory(category)
                .stream()
                .map(productMapper::mapToResponseDto)
                .toList();
    }

    @Override
    public List<ProductResponseDto> getByStatus(Status status) {
        return productRepository.findByStatus(status)
                .stream()
                .map(productMapper::mapToResponseDto)
                .toList();
    }

    @Override
    public List<ProductResponseDto> getTopRatedProducts() {
        return productRepository.findTop10ByOrderByRatingDesc()
                .stream()
                .map(productMapper::mapToResponseDto)
                .toList();
    }

    @Override
    public List<ProductResponseDto> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(productMapper::mapToResponseDto)
                .toList();
    }

    @Override
    public List<CategoryCountDto> countProductsByCategory() {
        return productRepository.countProductsByCategory()
                .stream()
                .map(response -> CategoryCountDto.builder()
                        .category(response.getCategory())
                        .totalProducts(response.getTotalProducts())
                        .build())
                .toList();
    }

    @Override
    public List<CompanyCountDto> countProductsByCompany() {
        return productRepository.countProductsByCompany()
                .stream()
                .map(response -> CompanyCountDto.builder()
                        .company(response.getCompany())
                        .totalProducts(response.getTotalProducts())
                        .build())
                .toList();
    }

    @Override
    public List<StatusCountDto> countProductsByStatus() {
        return productRepository.countProductsByStatus()
                .stream()
                .map(response -> StatusCountDto.builder()
                        .status(response.getStatus())
                        .totalProducts(response.getTotalProducts())
                        .build())
                .toList();
    }
}
