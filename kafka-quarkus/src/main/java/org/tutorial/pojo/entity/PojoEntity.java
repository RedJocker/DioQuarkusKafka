package org.tutorial.pojo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
public class PojoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name= "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    public String id;

    @NotBlank
    @Column
    @Email
    public String email;

    @NotBlank
    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Pattern(regexp = "^\\d{8}$")
    @Column(name = "social_security")
    public String socialSecurity;

    @NotNull
    @Past
    @Column(name = "birth_date")
    public LocalDate birthDate;

    @FutureOrPresent
    @NotNull
    @Column(name="register_date")
    public LocalDate registerDate;




}
