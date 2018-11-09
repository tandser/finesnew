package ru.tandser.finesnew.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

import static ru.tandser.finesnew.util.EntityHelper.getEntityIfLoaded;

@Entity
@Table(name = "cars")
@NamedEntityGraph(name = Car.WITH_FINES, attributeNodes = @NamedAttributeNode("fines"))
public class Car extends AbstractEntity {

    public static final String WITH_FINES = "Car.withFines";

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String number;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonBackReference
    @OneToMany(mappedBy = "car")
    private List<Fine> fines;

    //<editor-fold desc="Getters and Setters">

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Fine> getFines() {
        return fines;
    }

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Car that = (Car) o;

        return Objects.equals(brand,  that.brand) &&
               Objects.equals(model,  that.model) &&
               Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, number);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",     id)
                .add("brand",  brand)
                .add("model",  model)
                .add("number", number)
                .add("user",   getEntityIfLoaded(user))
                .toString();
    }
}