package ru.denisvukolov.domain.entity;

import java.io.Serializable;

public class GeneOrigin implements Serializable {
    private int id;

    private String phylum;

    private String age;

    private int order;

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public String getAge() {
        return age;
    }

    public String getPhylum() {
        return phylum;
    }
}
