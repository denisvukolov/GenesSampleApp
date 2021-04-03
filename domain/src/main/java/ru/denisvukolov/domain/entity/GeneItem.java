package ru.denisvukolov.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class GeneItem implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("origin")
    private GeneOrigin origin;

    @SerializedName("functionalClusters")
    private List<FunctionalCluster> functionalClusters;

    @SerializedName("ncbiId")
    private String ncbiId;

    @SerializedName("commentEvolution")
    private String commentEvolution;

    @SerializedName("commentFunction")
    private String commentFunction;

    @SerializedName("commentCause")
    private String[] commentCause;

    @SerializedName("commentAging")
    private String commentAging;

    @SerializedName("band")
    private String band;

    @SerializedName("orientation")
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

