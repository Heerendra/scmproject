package com.contactmanager.scm.entities;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "contact")
@Table(name = "contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Contact {
    @Id
    private String contactId;
    @Column(nullable = false, length = 30)
    private String cName;
    @Column(length = 30)
    private String email;
    @Column(length = 12, nullable = false)
    private String phoneNumber;
    @Column(length = 100)
    private String address;
    private String cPicture;
    private String description;
    private boolean favorite = false;
    private String websiteUrl;
    private String linkedInUrl;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SocialLinks> links = new ArrayList<>();
}
