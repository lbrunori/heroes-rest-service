package com.w2m.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="heroes")
@NamedQuery(name="find_all_heroes", query="select h from Heroe h")
public class Heroe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    @NotNull
    @Length(min = 1, max = 255)
    private String name;

    public Heroe(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Heroe() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
