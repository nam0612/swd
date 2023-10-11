//package com.fpt.swd.business.impl;
//
//import com.ecommerce.dto.ProductDTO;
//import com.ecommerce.enums.CatalogStatus;
//import com.ecommerce.enums.ProductType;
//import com.hainam.bamboo.api.request.CreateProductReq;
//import com.hainam.bamboo.business.ProductBusiness;
//import com.hainam.bamboo.database.entity.Product;
//import com.hainam.bamboo.database.repo.ProductRepo;
//import com.hainam.bamboo.utils.DataUtil;
//import com.hainam.bamboo.utils.QueryUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProductBusinessImpl implements ProductBusiness {
//
//    @Autowired
//    private ProductRepo productRepo;
//
//    @Override
//    public Page<ProductDTO> searchProduct(String productCode, String productName, String search, ProductType productType, CatalogStatus status, Pageable pageable) {
//        productCode = QueryUtils.appendPercent(DataUtil.safeToString(productCode));
//        productName = QueryUtils.appendPercent(DataUtil.safeToString(productName));
//        search = QueryUtils.appendPercent(DataUtil.safeToString(search));
//        Page<Product> lstProduct = productRepo.searchProduct(productCode, productName, search, productType, status, pageable);
//        return null;
//    }
//
//    @Override
//    public ProductDTO createProduct(CreateProductReq req) {
//        Product product = new Product();
//        BeanUtils.copyProperties(req, product);
//        productRepo.save(product);
//        return ProductDTO.builder()
//                .productId(product.getProductId())
//                .productCode(product.getProductCode())
//                .productName(product.getProductName())
//                .build();
//    }
//}
