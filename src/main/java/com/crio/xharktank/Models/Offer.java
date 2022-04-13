package com.crio.xharktank.Models;

public class Offer {

    private Integer offer_id;
    private Integer pitch_id;
    private String investor;
    private Double amount;
    private Double equity;
    private String comment;

    public Offer(Integer offer_id, Integer pitch_id, String investor, Double amount, Double equity, String comment) {
        this.offer_id = offer_id;
        this.pitch_id = pitch_id;
        this.investor = investor;
        this.amount = amount;
        this.equity = equity;
        this.comment = comment;
    }

    public Integer getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(Integer offer_id) {
        this.offer_id = offer_id;
    }

    public Integer getPitch_id() {
        return pitch_id;
    }

    public void setPitch_id(Integer pitch_id) {
        this.pitch_id = pitch_id;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getEquity() {
        return equity;
    }

    public void setEquity(Double equity) {
        this.equity = equity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

