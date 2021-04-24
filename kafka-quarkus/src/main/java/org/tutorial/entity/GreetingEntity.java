package org.tutorial.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class GreetingEntity extends PanacheEntityBase {

    @NotBlank
    @Column
    public String message;
}
