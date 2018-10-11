package com.example.hp.demo.GlobalPojo;

public class Data{
	private double bitcoinPercentageOfMarketCap;
	private int lastUpdated;
	private int activeCryptocurrencies;
	private int activeMarkets;
	private Quotes quotes;

	public void setBitcoinPercentageOfMarketCap(double bitcoinPercentageOfMarketCap){
		this.bitcoinPercentageOfMarketCap = bitcoinPercentageOfMarketCap;
	}

	public double getBitcoinPercentageOfMarketCap(){
		return bitcoinPercentageOfMarketCap;
	}

	public void setLastUpdated(int lastUpdated){
		this.lastUpdated = lastUpdated;
	}

	public int getLastUpdated(){
		return lastUpdated;
	}

	public void setActiveCryptocurrencies(int activeCryptocurrencies){
		this.activeCryptocurrencies = activeCryptocurrencies;
	}

	public int getActiveCryptocurrencies(){
		return activeCryptocurrencies;
	}

	public void setActiveMarkets(int activeMarkets){
		this.activeMarkets = activeMarkets;
	}

	public int getActiveMarkets(){
		return activeMarkets;
	}

	public void setQuotes(Quotes quotes){
		this.quotes = quotes;
	}

	public Quotes getQuotes(){
		return quotes;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"bitcoin_percentage_of_market_cap = '" + bitcoinPercentageOfMarketCap + '\'' + 
			",last_updated = '" + lastUpdated + '\'' + 
			",active_cryptocurrencies = '" + activeCryptocurrencies + '\'' + 
			",active_markets = '" + activeMarkets + '\'' + 
			",quotes = '" + quotes + '\'' + 
			"}";
		}
}
