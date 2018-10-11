package com.example.hp.demo.GlobalPojo;

public class Response{
	private Metadata metadata;
	private Data data;

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"GetEvents{" +
			"metadata = '" + metadata + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}
