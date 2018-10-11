package com.example.hp.demo.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Coin {

    @SerializedName("data")
    public List<Datum> data;
    @SerializedName("metadata")
    private Metadata metadata;



    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
    @Override
    public String toString(){
        return
                "ResponseArray{" +
                        "metadata = '" + metadata + '\'' +
                        ",data = '" + data + '\'' +
                        "}";
    }
}
