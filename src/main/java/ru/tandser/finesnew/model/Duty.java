package ru.tandser.finesnew.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "duties")
@NamedEntityGraph(name = Duty.WITH_FINES, attributeNodes = @NamedAttributeNode("fines"))
public class Duty extends AbstractEntity {

    public static final String WITH_FINES = "Duty.withFines";

    @NotBlank
    private String name;

    @NotNull @Min(0)
    private Integer price;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonBackReference
    @OneToMany(mappedBy = "duty")
    private List<Fine> fines;

    //<editor-fold desc="Getters and Setters">

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Fine> getFines() {
        return fines;
    }

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    //</editor-fold>

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",    id)
                .add("name",  name)
                .add("price", price)
                .toString();

    }
}