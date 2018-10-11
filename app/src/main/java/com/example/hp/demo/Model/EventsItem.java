package com.example.hp.demo.Model;

public class EventsItem {

    private String id;
    private String name;
    private String mSymbol;
    private String mDate;
    private String createDate;
    private String description;
    private String proof;
    private String source;
    private int voteCount;
    private int percentage;
    private String title;

    public EventsItem(String id, String name, String mSymbol, String mDate, String createDate, String description, String proof, String source, int voteCount, int percentage, String title) {
        this.id = id;
        this.name = name;
        this.mSymbol = mSymbol;
        this.mDate = mDate;
        this.createDate = createDate;
        this.description = description;
        this.proof = proof;
        this.source = source;
        this.voteCount = voteCount;
        this.percentage = percentage;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getmSymbol() {
        return mSymbol;
    }

    public String getmDate() {
        return mDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getDescription() {
        return description;
    }

    public String getProof() {
        return proof;
    }

    public String getSource() {
        return source;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getTitle() {
        return title;
    }
}
