//package com.fpt.swd.business;
//
//import com.ecommerce.dto.ProductDTO;
//import com.ecommerce.enums.CatalogStatus;
//import com.ecommerce.enums.ProductType;
//import com.hainam.bamboo.api.request.CreateProductReq;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//public interface ProductBusiness {
//    Page<ProductDTO> searchProduct(String productCode, String productName, String search, ProductType productType, CatalogStatus status, Pageable pageable);
//
//
//    ProductDTO createProduct(CreateProductReq reqquest);
//}
