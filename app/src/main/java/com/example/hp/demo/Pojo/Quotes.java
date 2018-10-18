package com.example.hp.demo.Pojo;

import com.google.gson.annotations.SerializedName;

public class Quotes{

	@SerializedName("BTC")
	private BTC bTC;

	@SerializedName("USD")
	private USD uSD;

	public void setBTC(BTC bTC){
		this.bTC = bTC;
	}

	public BTC getBTC(){
		return bTC;
	}

	public void setUSD(USD uSD){
		this.uSD = uSD;
	}

	public USD getUSD(){
		return uSD;
	}

	@Override
 	public String toString(){
		return 
			"Quotes{" + 
			"bTC = '" + bTC + '\'' + 
			",uSD = '" + uSD + '\'' + 
			"}";
		}
}