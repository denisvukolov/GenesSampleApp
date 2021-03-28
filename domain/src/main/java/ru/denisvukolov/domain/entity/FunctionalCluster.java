package ru.denisvukolov.domain.entity;

import java.io.Serializable;

public class FunctionalCluster implements Serializable {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FunctionalCluster{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
