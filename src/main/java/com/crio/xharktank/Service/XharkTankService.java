package com.crio.xharktank.Service;

import com.crio.xharktank.Models.PitchWithOffers;
import com.crio.xharktank.exceptions.InvalidBodyException;
import com.crio.xharktank.exceptions.PitchNotFoundException;

import java.util.List;

public interface XharkTankService {

    List<PitchWithOffers> sendPitches();

    Integer savePitch(String entrepreneur, String pitchTitle, String pitchIdea, Double askAmount, Double equity) throws InvalidBodyException;

    Integer saveOffer(Integer pitch_id, String investor, Double amount, Double equity, String comment) throws PitchNotFoundException;

    PitchWithOffers getPitchWithOffer(Integer pitch_id) throws PitchNotFoundException;

}
