package com.crio.xharktank.Models;

public class OfferForPitch {

    private String id;
    private String investor;
    private Double amount;
    private Double equity;
    private String comment;

    public OfferForPitch(String id, String investor, Double amount, Double equity, String comment) {
        this.id = id;
        this.investor = investor;
        this.amount = amount;
        this.equity = equity;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
