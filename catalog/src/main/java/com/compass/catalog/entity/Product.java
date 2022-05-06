package com.compass.catalog.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product implements Comparable<Product> {
	
	@Id
    private int id;

    private String name;

    private String description;

    private Boolean active;

    @NotNull
    private int[] category_ids;

    @DBRef
    private Variation variation;

    @Override
    public int compareTo(Product p) {
        return this.id - p.getId();
    }

}
