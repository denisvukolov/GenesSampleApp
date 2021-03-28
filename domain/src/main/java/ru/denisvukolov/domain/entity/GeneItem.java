package ru.denisvukolov.domain.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class GeneItem implements Serializable {

    private int id;

    private String name;

    private String symbol;

    private GeneOrigin origin;

    private List<FunctionalCluster> functionalClusters;

    private String ncbiId;

    private String commentEvolution;

    private String commentFunction;

    private String[] commentCause;

    private String commentAging;

    private String band;

    private String orientation;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public List<FunctionalCluster> getFunctionalClusters() {
        return functionalClusters;
    }

    public GeneOrigin getOrigin() {
        return origin;
    }

    public String getNcbiId() {
        return ncbiId;
    }

    public String getCommentAging() {
        return commentAging;
    }

    public String getCommentEvolution() {
        return commentEvolution;
    }

    public String getCommentFunction() {
        return commentFunction;
    }

    public String[] getCommentCause() {
        return commentCause;
    }

    public String getBand() {
        return band;
    }

    public String getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "GeneItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", origin=" + origin +
                ", functionalClusters=" + functionalClusters +
                ", ncbiId='" + ncbiId + '\'' +
                ", commentEvolution='" + commentEvolution + '\'' +
                ", commentFunction='" + commentFunction + '\'' +
                ", commentCause=" + Arrays.toString(commentCause) +
                ", commentAging='" + commentAging + '\'' +
                ", band='" + band + '\'' +
                ", orientation='" + orientation + '\'' +
                '}';
    }
}

