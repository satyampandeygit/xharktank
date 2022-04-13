package com.crio.xharktank.repository;

import com.crio.xharktank.Models.OfferForPitch;
import com.crio.xharktank.Models.Pitch;
import com.crio.xharktank.exceptions.InvalidBodyException;
import com.crio.xharktank.exceptions.PitchNotFoundException;

import java.util.List;

public interface XharkTankRepository {

    List<Pitch> getAllThePitches();

    Integer createPitch(String entrepreneur, String pitchIdea, String pitchTitle,
                        Double askAmount, Double equity) throws InvalidBodyException;

    Integer createOffer(Integer pitch_id, String investor, Double amount, Double equity, String comment) throws InvalidBodyException;

    Integer findPitch(Integer pitch_id) throws PitchNotFoundException;

    Integer findOffer(Integer pitch_id);

    Pitch fetchPitch(Integer pitch_id);

    List<OfferForPitch> fetchOffer(Integer pitch_id);

}
