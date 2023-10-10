//package com.hainam.bamboo.database.entity;
//
//import com.ecommerce.enums.CatalogStatus;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.io.Serializable;
//import java.util.Date;
//
//@EntityListeners(AuditingEntityListener.class)
//@Builder
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "product", schema = "ecommerce")
//public class Product implements Serializable {
//
//    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "product_id", nullable = false)
//    private String productId;
//
//    @Column(name = "product_code")
//    private String productCode;
//
//    @Column(name = "product_name")
//    private String productName;
//
//    @Column(name = "description")
//    private String description;
//
//    @Column(name = "image_url")
//    private String imageUrl;
//
//    @Column(name = "check_serial")
//    private Boolean checkSerial;
//
//    @Column(name = "product_type")
//    private String productType;
//
//    @Column(name = "unit")
//    private String unit;
//
//    @Column(name = "vat")
//    private Integer vat;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    private CatalogStatus status;
//
//    @CreatedBy
//    @Column(name = "created_user")
//    private String createdUser;
//
//    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_date")
//    private Date createdDate;
//
//    @LastModifiedBy
//    @Column(name = "updated_user")
//    private String updatedUser;
//
//    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_date")
//    private Date updatedDate;
//}
//
