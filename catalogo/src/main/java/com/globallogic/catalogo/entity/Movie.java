package com.globallogic.catalogo.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String uuid;

    @NotNull
    @Column(length = 150, unique = true)
    @Length(min = 2, max = 150, message = "el tamaño debe ser entre 2 y 150")
    private String title;

    @Column(length = 1024)
    private String description;

    @NotNull
    private LocalDate releaseDate;

    private double valoration;

    private String urlImagePoster;

    private String urlVideotrailer;

    private LocalDate createdOn;

    private LocalDate lastUpdated;

    @PrePersist
    private void prePersist() {
        this.uuid = UUID.randomUUID().toString();
        this.createdOn = LocalDate.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.lastUpdated = LocalDate.now();
    }

}
