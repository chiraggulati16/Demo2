package com.example.hp.demo.Pojo;

import com.google.gson.annotations.SerializedName;

public class USD{

	@SerializedName("percent_change_1h")
	private double percentChange1h;

	@SerializedName("market_cap")
	private double marketCap;

	@SerializedName("percent_change_24h")
	private double percentChange24h;

	@SerializedName("price")
	private double price;

	@SerializedName("volume_24h")
	private double volume24h;

	@SerializedName("percent_change_7d")
	private double percentChange7d;

	public void setPercentChange1h(double percentChange1h){
		this.percentChange1h = percentChange1h;
	}

	public double getPercentChange1h(){
		return percentChange1h;
	}

	public void setMarketCap(double marketCap){
		this.marketCap = marketCap;
	}

	public double getMarketCap(){
		return marketCap;
	}

	public void setPercentChange24h(double percentChange24h){
		this.percentChange24h = percentChange24h;
	}

	public double getPercentChange24h(){
		return percentChange24h;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setVolume24h(double volume24h){
		this.volume24h = volume24h;
	}

	public double getVolume24h(){
		return volume24h;
	}

	public void setPercentChange7d(double percentChange7d){
		this.percentChange7d = percentChange7d;
	}

	public double getPercentChange7d(){
		return percentChange7d;
	}

	@Override
 	public String toString(){
		return 
			"USD{" + 
			"percent_change_1h = '" + percentChange1h + '\'' + 
			",market_cap = '" + marketCap + '\'' + 
			",percent_change_24h = '" + percentChange24h + '\'' + 
			",price = '" + price + '\'' + 
			",volume_24h = '" + volume24h + '\'' + 
			",percent_change_7d = '" + percentChange7d + '\'' + 
			"}";
		}
}