package com.example.hp.demo.CMCChart;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CmcChart{

	@SerializedName("price_usd")
	private List<List<Float>> priceUsd;

	@SerializedName("market_cap_by_available_supply")
	private List<List<Long>> marketCapByAvailableSupply;

	@SerializedName("price_btc")
	private List<List<Float>> priceBtc;

	@SerializedName("volume_usd")
	private List<List<Long>> volumeUsd;

	public void setPriceUsd(List<List<Float>> priceUsd){
		this.priceUsd = priceUsd;
	}

	public List<List<Float>> getPriceUsd(){
		return priceUsd;
	}

	public void setMarketCapByAvailableSupply(List<List<Long>> marketCapByAvailableSupply){
		this.marketCapByAvailableSupply = marketCapByAvailableSupply;
	}

	public List<List<Long>> getMarketCapByAvailableSupply(){
		return marketCapByAvailableSupply;
	}

	public void setPriceBtc(List<List<Float>> priceBtc){
		this.priceBtc = priceBtc;
	}

	public List<List<Float>> getPriceBtc(){
		return priceBtc;
	}

	public void setVolumeUsd(List<List<Long>> volumeUsd){
		this.volumeUsd = volumeUsd;
	}

	public List<List<Long>> getVolumeUsd(){
		return volumeUsd;
	}

	@Override
 	public String toString(){
		return 
			"CmcChart{" + 
			"price_usd = '" + priceUsd + '\'' + 
			",market_cap_by_available_supply = '" + marketCapByAvailableSupply + '\'' + 
			",price_btc = '" + priceBtc + '\'' + 
			",volume_usd = '" + volumeUsd + '\'' + 
			"}";
		}
}