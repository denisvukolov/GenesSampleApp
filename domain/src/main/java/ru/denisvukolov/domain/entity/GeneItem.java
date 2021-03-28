package ru.denisvukolov.domain.entity;

import java.io.Serializable;
import java.util.List;

public class GeneItem implements Serializable {

    private int id;

    private String name;

    private String symbol;

    private GeneOrigin origin;

    private List<FunctionalCluster> functionalClusters;

    private String ageMya;

    private String agePhylo;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAgeMya() {
        return ageMya;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getAgePhylo() {
        return agePhylo;
    }

    public List<FunctionalCluster> getFunctionalClusters() {
        return functionalClusters;
    }

    public GeneOrigin getOrigin() {
        return origin;
    }
}

