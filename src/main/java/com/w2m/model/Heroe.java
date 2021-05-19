package com.w2m.model;

import javax.persistence.*;

@Entity
@Table(name="heroes")
public class Heroe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
}
