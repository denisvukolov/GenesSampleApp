package ru.denisvukolov.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GeneOrigin implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("phylum")
    private String phylum;

    @SerializedName("age")
    private String age;

    @SerializedName("order")
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

    @Override
    public String toString() {
        return "GeneOrigin{" +
                "id=" + id +
                ", phylum='" + phylum + '\'' +
                ", age='" + age + '\'' +
                ", order=" + order +
                '}';
    }
}
