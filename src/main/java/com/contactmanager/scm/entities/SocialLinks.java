package com.contactmanager.scm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "socialLinks")
@Table(name = "sociallinks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SocialLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sId;
    private String link;
    private String title;

    @ManyToOne
    private Contact contact;

}
