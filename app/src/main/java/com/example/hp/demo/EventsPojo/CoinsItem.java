package com.example.hp.demo.EventsPojo;

import com.google.gson.annotations.SerializedName;

public class CoinsItem{

	@SerializedName("symbol")
	private String symbol;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol(){
		return symbol;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"CoinsItem{" + 
			"symbol = '" + symbol + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}