package ru.tandser.finesnew.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedEntityGraph(name = User.WITH_CARS, attributeNodes = @NamedAttributeNode("cars"))
public class User extends AbstractEntity {

    public static final String WITH_CARS = "User.withCars";

    @NotBlank
    private String name;

    @NotBlank
    private String patronymic;

    @NotBlank
    private String surname;

    @NotBlank
    private String license;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private Set<Car> cars;

    //<editor-fold desc="Getters and Setters">

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    //</editor-fold>

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",         id)
                .add("name",       name)
                .add("patronymic", patronymic)
                .add("surname",    surname)
                .add("license",    license)
                .toString();
    }
}