package ru.tandser.finesnew.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static ru.tandser.finesnew.util.EntityHelper.getEntityIfLoaded;

@Entity
@Table(name = "fines")
@NamedEntityGraph(name = Fine.WITH_CAR_AND_DUTY, attributeNodes = {@NamedAttributeNode("car"), @NamedAttributeNode("duty")})
public class Fine extends AbstractEntity {

    public static final String WITH_CAR_AND_DUTY = "Fine.withCarAndDuty";

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Duty duty;

    @NotNull
    private Boolean status;

    //<editor-fold desc="Getters and Setters">

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Duty getDuty() {
        return duty;
    }

    public void setDuty(Duty duty) {
        this.duty = duty;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    //</editor-fold>

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",   id)
                .add("car",  getEntityIfLoaded(car))
                .add("duty", getEntityIfLoaded(duty))
                .toString();
    }
}