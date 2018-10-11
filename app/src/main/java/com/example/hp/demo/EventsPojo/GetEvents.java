package com.example.hp.demo.EventsPojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetEvents {

	@SerializedName("coins")
	private List<CoinsItem> coins;

	@SerializedName("description")
	private String description;

	@SerializedName("source")
	private String source;

	@SerializedName("title")
	private String title;

	@SerializedName("can_occur_before")
	private boolean canOccurBefore;

	@SerializedName("positive_vote_count")
	private int positiveVoteCount;

	@SerializedName("tip_adress")
	private String tipAdress;

	@SerializedName("is_hot")
	private boolean isHot;

	@SerializedName("date_event")
	private String dateEvent;

	@SerializedName("percentage")
	private int percentage;

	@SerializedName("tip_symbol")
	private String tipSymbol;

	@SerializedName("twitter_account")
	private String twitterAccount;

	@SerializedName("id")
	private int id;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("proof")
	private String proof;

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	@SerializedName("vote_count")
	private int voteCount;

	public void setCoins(List<CoinsItem> coins){
		this.coins = coins;
	}

	public List<CoinsItem> getCoins(){
		return coins;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return source;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setCanOccurBefore(boolean canOccurBefore){
		this.canOccurBefore = canOccurBefore;
	}

	public boolean isCanOccurBefore(){
		return canOccurBefore;
	}

	public void setPositiveVoteCount(int positiveVoteCount){
		this.positiveVoteCount = positiveVoteCount;
	}

	public int getPositiveVoteCount(){
		return positiveVoteCount;
	}

	public void setTipAdress(String tipAdress){
		this.tipAdress = tipAdress;
	}

	public String getTipAdress(){
		return tipAdress;
	}

	public void setIsHot(boolean isHot){
		this.isHot = isHot;
	}

	public boolean isIsHot(){
		return isHot;
	}

	public void setDateEvent(String dateEvent){
		this.dateEvent = dateEvent;
	}

	public String getDateEvent(){
		return dateEvent;
	}

	public void setPercentage(int percentage){
		this.percentage = percentage;
	}

	public int getPercentage(){
		return percentage;
	}

	public void setTipSymbol(String tipSymbol){
		this.tipSymbol = tipSymbol;
	}

	public String getTipSymbol(){
		return tipSymbol;
	}

	public void setTwitterAccount(String twitterAccount){
		this.twitterAccount = twitterAccount;
	}

	public String getTwitterAccount(){
		return twitterAccount;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setProof(String proof){
		this.proof = proof;
	}

	public String getProof(){
		return proof;
	}

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	@Override
 	public String toString(){
		return 
			"GetEvents{" +
			"coins = '" + coins + '\'' + 
			",description = '" + description + '\'' + 
			",source = '" + source + '\'' + 
			",title = '" + title + '\'' + 
			",can_occur_before = '" + canOccurBefore + '\'' + 
			",positive_vote_count = '" + positiveVoteCount + '\'' + 
			",tip_adress = '" + tipAdress + '\'' + 
			",is_hot = '" + isHot + '\'' + 
			",date_event = '" + dateEvent + '\'' + 
			",percentage = '" + percentage + '\'' + 
			",tip_symbol = '" + tipSymbol + '\'' + 
			",twitter_account = '" + twitterAccount + '\'' + 
			",id = '" + id + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",proof = '" + proof + '\'' + 
			",categories = '" + categories + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			"}";
		}
}