package com.example.hp.demo.Pojo;


import com.google.gson.annotations.SerializedName;

public class Metadata{

	@SerializedName("num_cryptocurrencies")
	private int numCryptocurrencies;

	@SerializedName("error")
	private Object error;

	@SerializedName("timestamp")
	private int timestamp;

	public void setNumCryptocurrencies(int numCryptocurrencies){
		this.numCryptocurrencies = numCryptocurrencies;
	}

	public int getNumCryptocurrencies(){
		return numCryptocurrencies;
	}

	public void setError(Object error){
		this.error = error;
	}

	public Object getError(){
		return error;
	}

	public void setTimestamp(int timestamp){
		this.timestamp = timestamp;
	}

	public int getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"Metadata{" + 
			"num_cryptocurrencies = '" + numCryptocurrencies + '\'' + 
			",error = '" + error + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}