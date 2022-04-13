package com.crio.xharktank.Models;

import java.util.List;

public class PitchWithOffers {

    private String id;
    private String entrepreneur;
    private String pitchTitle;
    private String pitchIdea;
    private Double askAmount;
    private Double equity;
    private List<OfferForPitch> offers;

    public PitchWithOffers(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(String entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    public String getPitchTitle() {
        return pitchTitle;
    }

    public void setPitchTitle(String pitchTitle) {
        this.pitchTitle = pitchTitle;
    }

    public String getPitchIdea() {
        return pitchIdea;
    }

    public void setPitchIdea(String pitchIdea) {
        this.pitchIdea = pitchIdea;
    }

    public Double getAskAmount() {
        return askAmount;
    }

    public void setAskAmount(Double askAmount) {
        this.askAmount = askAmount;
    }

    public Double getEquity() {
        return equity;
    }

    public void setEquity(Double equity) {
        this.equity = equity;
    }

    public List<OfferForPitch> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferForPitch> offers) {
        this.offers = offers;
    }
}
