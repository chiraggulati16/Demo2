package com.example.hp.demo.Pojo;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")

    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("symbol")

    private String symbol;
    @SerializedName("website_slug")

    private String websiteSlug;
    @SerializedName("rank")

    private Integer rank;
    @SerializedName("circulating_supply")

    private Double circulatingSupply;
    @SerializedName("total_supply")
    private Double totalSupply;
    @SerializedName("max_supply")

    private String maxSupply;
    @SerializedName("quotes")

    private Quotes quotes;
    @SerializedName("last_updated")

    private Integer lastUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getWebsiteSlug() {
        return websiteSlug;
    }

    public void setWebsiteSlug(String websiteSlug) {
        this.websiteSlug = websiteSlug;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(Double circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public Double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Quotes getQuotes() {
        return quotes;
    }

    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
    }

    public Integer getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
