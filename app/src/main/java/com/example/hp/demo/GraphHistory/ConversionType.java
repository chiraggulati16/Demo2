package com.example.hp.demo.GraphHistory;


import com.google.gson.annotations.SerializedName;


public class ConversionType{

	@SerializedName("conversionSymbol")
	private String conversionSymbol;

	@SerializedName("type")
	private String type;

	public void setConversionSymbol(String conversionSymbol){
		this.conversionSymbol = conversionSymbol;
	}

	public String getConversionSymbol(){
		return conversionSymbol;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"ConversionType{" + 
			"conversionSymbol = '" + conversionSymbol + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}