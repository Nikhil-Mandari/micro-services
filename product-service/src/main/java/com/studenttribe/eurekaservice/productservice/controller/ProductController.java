package com.studenttribe.eurekaservice.productservice.controller;

import com.studenttribe.eurekaservice.productservice.dto.request.ProductRequestDto;
import com.studenttribe.eurekaservice.productservice.dto.request.ProductUpdateRequestDto;
import com.studenttribe.eurekaservice.productservice.dto.response.*;
import com.studenttribe.eurekaservice.productservice.enums.Category;
import com.studenttribe.eurekaservice.productservice.enums.Status;
import com.studenttribe.eurekaservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> save(
            @RequestBody ProductRequestDto requestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseDto.<ProductResponseDto>builder()
                        .success(true)
                        .status(HttpStatus.CREATED.value())
                        .message("Product Created Successfully")
                        .data(productService.save(requestDto))
                        .build()
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> getProductById(
            @PathVariable String productId
    ) {
        return ResponseEntity.ok(
                ApiResponseDto.<ProductResponseDto>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Product Retrieved Successfully")
                        .data(productService.getById(productId))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getAll() {
        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Products Retrieved Successfully")
                        .data(productService.getAll())
                        .build()
        );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<ProductResponseDto>> update(
            @PathVariable String productId,
            @RequestBody ProductUpdateRequestDto requestDto
    ) {
        return ResponseEntity.ok(
                ApiResponseDto.<ProductResponseDto>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Product Updated Successfully")
                        .data(productService.update(productId, requestDto))
                        .build()
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable String productId) {
        productService.delete(productId);
        return ResponseEntity.ok(
                ApiResponseDto.<Void>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Product Deleted Successfully")
                        .build()
        );
    }

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponseDto<Page<ProductResponseDto>>> getAllByPage(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy
    ) {
        return ResponseEntity.ok(
                ApiResponseDto.<Page<ProductResponseDto>>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Products Retrieved Successfully")
                        .data(productService.getAllByPage(page, size, sortBy))
                        .build()
        );
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getProductsByCategory(
            @PathVariable Category category
    ) {
        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Products Retrieved Successfully")
                        .data(productService.getByCategory(category))
                        .build()
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getProductsByStatus(
            @PathVariable Status status
    ) {
        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Products Retrieved Successfully")
                        .data(productService.getByStatus(status))
                        .build()
        );
    }

    @GetMapping("/top-rated")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> getTopRatedProducts() {
        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Top Rated Products Retrieved Successfully")
                        .data(productService.getTopRatedProducts())
                        .build()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponseDto<List<ProductResponseDto>>> searchProducts(
            @RequestParam String keyword
    ) {
        return ResponseEntity.ok(
                ApiResponseDto.<List<ProductResponseDto>>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Products Retrieved Successfully")
                        .data(productService.searchProducts(keyword))
                        .build()
        );
    }

    @GetMapping("/count-by-category")
    public ResponseEntity<ApiResponseDto<List<CategoryCountDto>>> countProductsByCategory() {
        return ResponseEntity.ok(
                ApiResponseDto.<List<CategoryCountDto>>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Category Count Retrieved Successfully")
                        .data(productService.countProductsByCategory())
                        .build()
        );
    }

    @GetMapping("/count-by-company")
    public ResponseEntity<ApiResponseDto<List<CompanyCountDto>>> countProductsByCompany() {
        return ResponseEntity.ok(
                ApiResponseDto.<List<CompanyCountDto>>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Company Count Retrieved Successfully")
                        .data(productService.countProductsByCompany())
                        .build()
        );
    }

    @GetMapping("/count-by-status")
    public ResponseEntity<ApiResponseDto<List<StatusCountDto>>> countProductsByStatus() {
        return ResponseEntity.ok(
                ApiResponseDto.<List<StatusCountDto>>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Status Count Retrieved Successfully")
                        .data(productService.countProductsByStatus())
                        .build()
        );
    }
}
