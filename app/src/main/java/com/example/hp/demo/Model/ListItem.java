package com.example.hp.demo.Model;

public class ListItem {
    private String id;
    private String name;
    private String symbol;
    private String percentChange1h;
    private String marketCap;
    private String percentChange24h;
    private String price;
    private String volume24h;
    private String percentChange7d;
    private String rank;
    private String totalSupply;
    private String maxSupply;
    private String circulatorySupply;
    private String btcPrice;
    private String website;

    public ListItem(String id,String rank, String name, String symbol, String price,
                    String marketCap, String volume24h, String percentChange1h,
                    String percentChange24h, String percentChange7d, String totalSupply,
                    String maxSupply, String circulatorySupply, String btcPrice, String website) {
        this.id = id;
        this.rank=rank;
        this.name = name;
        this.symbol = symbol;
        this.percentChange1h = percentChange1h;
        this.marketCap = marketCap;
        this.percentChange24h = percentChange24h;
        this.price = price;
        this.volume24h = volume24h;
        this.percentChange7d = percentChange7d;
        this.totalSupply=totalSupply;
        this.circulatorySupply=circulatorySupply;
        this.maxSupply=maxSupply;
        this.btcPrice=btcPrice;
        this.website=website;
    }

    @Override
    public boolean equals(Object obj) {
        ListItem obj1 = (ListItem) obj;
        if (this.id.equals(obj1.getId())) {
            return true;
        }
        return false;
    }
    /*public ListItem(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }*/

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public String getPrice() {
        return price;
    }

    public String getVolume24h() {
        return volume24h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public String getRank() {
        return rank;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public String getCirculatorySupply() {
        return circulatorySupply;
    }

    public String getBtcPrice() {
        return btcPrice;
    }

    public String getWebsite() {
        return website;
    }
}