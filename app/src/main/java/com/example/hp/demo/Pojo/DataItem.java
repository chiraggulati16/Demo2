package com.example.hp.demo.Pojo;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("symbol")
	private String symbol;

	@SerializedName("circulating_supply")
	private double circulatingSupply;

	@SerializedName("last_updated")
	private int lastUpdated;

	@SerializedName("total_supply")
	private double totalSupply;

	@SerializedName("name")
	private String name;

	@SerializedName("max_supply")
	private String maxSupply;

	@SerializedName("rank")
	private int rank;

	@SerializedName("id")
	private int id;

	@SerializedName("website_slug")
	private String websiteSlug;

	@SerializedName("quotes")
	private Quotes quotes;

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol(){
		return symbol;
	}

	public void setCirculatingSupply(double circulatingSupply){
		this.circulatingSupply = circulatingSupply;
	}

	public double getCirculatingSupply(){
		return circulatingSupply;
	}

	public void setLastUpdated(int lastUpdated){
		this.lastUpdated = lastUpdated;
	}

	public int getLastUpdated(){
		return lastUpdated;
	}

	public void setTotalSupply(double totalSupply){
		this.totalSupply = totalSupply;
	}

	public double getTotalSupply(){
		return totalSupply;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMaxSupply(String maxSupply){
		this.maxSupply = maxSupply;
	}

	public String getMaxSupply(){
		return maxSupply;
	}

	public void setRank(int rank){
		this.rank = rank;
	}

	public int getRank(){
		return rank;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setWebsiteSlug(String websiteSlug){
		this.websiteSlug = websiteSlug;
	}

	public String getWebsiteSlug(){
		return websiteSlug;
	}

	public void setQuotes(Quotes quotes){
		this.quotes = quotes;
	}

	public Quotes getQuotes(){
		return quotes;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"symbol = '" + symbol + '\'' + 
			",circulating_supply = '" + circulatingSupply + '\'' + 
			",last_updated = '" + lastUpdated + '\'' + 
			",total_supply = '" + totalSupply + '\'' + 
			",name = '" + name + '\'' + 
			",max_supply = '" + maxSupply + '\'' + 
			",rank = '" + rank + '\'' + 
			",id = '" + id + '\'' + 
			",website_slug = '" + websiteSlug + '\'' + 
			",quotes = '" + quotes + '\'' + 
			"}";
		}
}