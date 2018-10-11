package com.example.hp.demo.Pojo;

import com.google.gson.annotations.SerializedName;

public class Quotes{

	@SerializedName("USD")
	private USD uSD;

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
			"uSD = '" + uSD + '\'' + 
			"}";
		}
}