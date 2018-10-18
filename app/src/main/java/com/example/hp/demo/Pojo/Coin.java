package com.example.hp.demo.Pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Coin{

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("data")
	private List<DataItem> data;

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
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
			"Coin{" + 
			"metadata = '" + metadata + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}