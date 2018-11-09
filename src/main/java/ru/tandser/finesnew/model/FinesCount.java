package ru.tandser.finesnew.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static ru.tandser.finesnew.util.EntityHelper.getEntityIfLoaded;

/* synthetic entity */

@Entity
@Table(name = "fines")
public class FinesCount extends AbstractEntity {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Duty duty;

    @NotNull
    private Boolean status;

    @NotBlank
    private String dutyName;

    @NotNull @Min(0)
    private Long count;

    public FinesCount(String dutyName, Long count) {
        this.dutyName = dutyName;
        this.count    = count;
    }

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

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    //</editor-fold>

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",       id)
                .add("car",      getEntityIfLoaded(car))
                .add("duty",     getEntityIfLoaded(duty))
                .add("status",   status)
                .add("dutyName", dutyName)
                .add("count",    count)
                .toString();
    }
}