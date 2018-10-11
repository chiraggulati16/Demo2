package com.example.hp.demo.GlobalPojo;

public class Quotes{
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
