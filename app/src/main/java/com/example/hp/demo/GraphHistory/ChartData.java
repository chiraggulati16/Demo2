package com.example.hp.demo.GraphHistory;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ChartData{

	@SerializedName("Response")
	private String response;

	@SerializedName("Type")
	private int type;

	@SerializedName("FirstValueInArray")
	private boolean firstValueInArray;

	@SerializedName("ConversionType")
	private ConversionType conversionType;

	@SerializedName("Aggregated")
	private boolean aggregated;

	@SerializedName("TimeFrom")
	private int timeFrom;

	@SerializedName("TimeTo")
	private int timeTo;

	@SerializedName("Data")
	private List<DataItem> data;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setFirstValueInArray(boolean firstValueInArray){
		this.firstValueInArray = firstValueInArray;
	}

	public boolean isFirstValueInArray(){
		return firstValueInArray;
	}

	public void setConversionType(ConversionType conversionType){
		this.conversionType = conversionType;
	}

	public ConversionType getConversionType(){
		return conversionType;
	}

	public void setAggregated(boolean aggregated){
		this.aggregated = aggregated;
	}

	public boolean isAggregated(){
		return aggregated;
	}

	public void setTimeFrom(int timeFrom){
		this.timeFrom = timeFrom;
	}

	public int getTimeFrom(){
		return timeFrom;
	}

	public void setTimeTo(int timeTo){
		this.timeTo = timeTo;
	}

	public int getTimeTo(){
		return timeTo;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ChartData{" + 
			"response = '" + response + '\'' + 
			",type = '" + type + '\'' + 
			",firstValueInArray = '" + firstValueInArray + '\'' + 
			",conversionType = '" + conversionType + '\'' + 
			",aggregated = '" + aggregated + '\'' + 
			",timeFrom = '" + timeFrom + '\'' + 
			",timeTo = '" + timeTo + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}