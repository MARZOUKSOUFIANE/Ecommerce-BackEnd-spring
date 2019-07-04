package org.glsid.lightecomv1.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor @AllArgsConstructor
@ToString @Data

@Entity
public class Category implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private String description;
    @ManyToMany(mappedBy = "category")
    private Collection<Product> products;
}
