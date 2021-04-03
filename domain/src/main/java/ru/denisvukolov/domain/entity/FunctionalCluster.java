package ru.denisvukolov.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FunctionalCluster implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
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
