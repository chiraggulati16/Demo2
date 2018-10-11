package com.example.hp.demo.GlobalPojo;

public class Metadata{
	private Object error;
	private int timestamp;

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
			"error = '" + error + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}
