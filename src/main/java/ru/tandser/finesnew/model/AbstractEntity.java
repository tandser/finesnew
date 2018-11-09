package ru.tandser.finesnew.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    protected Long id;

    @Version
    protected int version;

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }

    //<editor-fold desc="Getters and Setters">

    @Override
    public Long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    //</editor-fold>
}