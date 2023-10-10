//package com.fpt.swd.api;
//
//
//import com.ecommerce.dto.ProductDTO;
//import com.hainam.bamboo.api.request.CreateProductReq;
//import com.hainam.bamboo.api.request.SearchProductReq;
//import com.hainam.bamboo.business.ProductBusiness;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping
//public class ProductController extends BaseController {
//
//    @Autowired
//    private ProductBusiness productBusiness;
//
//
//    @GetMapping("/products")
//    public ResponseEntity<Object> search(
//            @ModelAttribute SearchProductReq req,
//            Pageable pageable
//    ) {
//        Page<ProductDTO> pProduct = productBusiness.searchProduct(
//                req.getProductCode(),
//                req.getProductName(),
//                req.getSearch(),
//                req.getProductType(),
//                req.getStatus(),
//                pageable);
//        return processPageResponse(pProduct);
//    }
//
//    @PostMapping ("/product")
//    public ResponseEntity<Object> create(
//            @RequestBody @Valid CreateProductReq req
//    ) {
//         ;
//        return ResponseEntity.ok(productBusiness.createProduct(req));
//    }
//
//}
