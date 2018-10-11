package com.example.hp.demo.GraphHistory;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("high")
	private double high;

	@SerializedName("low")
	private double low;

	@SerializedName("volumeto")
	private double volumeto;

	@SerializedName("volumefrom")
	private double volumefrom;

	@SerializedName("time")
	private double time;

	@SerializedName("close")
	private double close;

	@SerializedName("open")
	private double open;

	public void setHigh(double high){
		this.high = high;
	}

	public double getHigh(){
		return high;
	}

	public void setLow(double low){
		this.low = low;
	}

	public double getLow(){
		return low;
	}

	public void setVolumeto(double volumeto){
		this.volumeto = volumeto;
	}

	public double getVolumeto(){
		return volumeto;
	}

	public void setVolumefrom(double volumefrom){
		this.volumefrom = volumefrom;
	}

	public double getVolumefrom(){
		return volumefrom;
	}

	public void setTime(double time){
		this.time = time;
	}

	public double getTime(){
		return time;
	}

	public void setClose(double close){
		this.close = close;
	}

	public double getClose(){
		return close;
	}

	public void setOpen(double open){
		this.open = open;
	}

	public double getOpen(){
		return open;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"high = '" + high + '\'' + 
			",low = '" + low + '\'' + 
			",volumeto = '" + volumeto + '\'' + 
			",volumefrom = '" + volumefrom + '\'' + 
			",time = '" + time + '\'' + 
			",close = '" + close + '\'' + 
			",open = '" + open + '\'' + 
			"}";
		}
}