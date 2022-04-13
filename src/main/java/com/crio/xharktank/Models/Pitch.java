package com.crio.xharktank.Models;

public class Pitch {

    private Integer pitch_id;
    private String entrepreneur;
    private String pitchTitle;
    private String pitchIdea;
    private Double askAmount;
    private Double equity;

    public Pitch(Integer pitch_id, String entrepreneur, String pitchTitle, String pitchIdea, Double askAmount, Double equity) {
        this.pitch_id = pitch_id;
        this.entrepreneur = entrepreneur;
        this.pitchTitle = pitchTitle;
        this.pitchIdea = pitchIdea;
        this.askAmount = askAmount;
        this.equity = equity;
    }

    public Integer getPitch_id() {
        return pitch_id;
    }

    public void setPitch_id(Integer pitch_id) {
        this.pitch_id = pitch_id;
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
}
