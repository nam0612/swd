package com.fpt.swd.database.entity;

import com.fpt.swd.enums.OAuth2Provider;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "swd392_database")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "id_picture_upload")
    private String idPictureUpload;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "status")
    private byte status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private OAuth2Provider provider;

    public User(String username, String password, String email, Integer role, String imageUrl, OAuth2Provider provider) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.idPictureUpload = imageUrl;
        this.provider = provider;
        this.roleId = role;
    }

}
