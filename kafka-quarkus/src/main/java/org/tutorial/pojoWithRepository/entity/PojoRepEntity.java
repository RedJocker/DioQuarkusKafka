package org.tutorial.pojoWithRepository.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import java.time.LocalDate;

@Entity
public class PojoRepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank
    public String name;

    @Past
    @NotNull
    public LocalDate birth;

    public LocalDate now = LocalDate.now();

    @FutureOrPresent
    @NotNull
    public LocalDate reservedDay;

    // return name as uppercase in the model
    public String getName(){
        return name.toUpperCase();
    }

    // store all names in lowercase in the DB
    public void setName(String name){
        this.name = name.toLowerCase();
    }


}
