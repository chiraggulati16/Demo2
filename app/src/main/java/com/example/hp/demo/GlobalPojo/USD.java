package com.example.hp.demo.GlobalPojo;

public class USD{
	private double totalVolume24h;
	private double totalMarketCap;

	public void setTotalVolume24h(double totalVolume24h){
		this.totalVolume24h = totalVolume24h;
	}

	public double getTotalVolume24h(){
		return totalVolume24h;
	}

	public void setTotalMarketCap(double totalMarketCap){
		this.totalMarketCap = totalMarketCap;
	}

	public double getTotalMarketCap(){
		return totalMarketCap;
	}

	@Override
 	public String toString(){
		return 
			"USD{" + 
			"total_volume_24h = '" + totalVolume24h + '\'' + 
			",total_market_cap = '" + totalMarketCap + '\'' + 
			"}";
		}
}
