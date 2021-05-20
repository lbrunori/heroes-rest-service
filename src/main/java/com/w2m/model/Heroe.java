package com.w2m.model;

import javax.persistence.*;

@Entity
@Table(name="heroes")
@NamedQuery(name="find_all_heroes", query="select h from Heroe h")
public class Heroe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
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
