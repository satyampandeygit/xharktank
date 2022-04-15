package com.crio.xharktank.Service;

import com.crio.xharktank.Models.OfferForPitch;
import com.crio.xharktank.Models.Pitch;
import com.crio.xharktank.Models.PitchWithOffers;
import com.crio.xharktank.exceptions.InvalidBodyException;
import com.crio.xharktank.exceptions.PitchNotFoundException;
import com.crio.xharktank.repository.XharkTankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class XharkTankServiceImpl implements XharkTankService {

    @Autowired
    XharkTankRepository repository;

    @Override
    public List<PitchWithOffers> sendPitches() {

        List<Pitch> allPitches = repository.getAllThePitches();

        int totalPitches = allPitches.size();
        List<PitchWithOffers> responseToSend = new ArrayList<>();

        if(totalPitches==0){
            return responseToSend;
        }

        Map<Integer,List<OfferForPitch>> pitchWithOffers = new HashMap<>();

        for(Pitch pitch: allPitches){

            PitchWithOffers pitchWithOffersTemp = new PitchWithOffers();

            pitchWithOffersTemp.setId( "" +pitch.getPitch_id());
            pitchWithOffersTemp.setEntrepreneur(pitch.getEntrepreneur());
            pitchWithOffersTemp.setPitchTitle(pitch.getPitchTitle());
            pitchWithOffersTemp.setPitchIdea(pitch.getPitchIdea());
            pitchWithOffersTemp.setAskAmount(pitch.getAskAmount());
            pitchWithOffersTemp.setEquity(pitch.getEquity());

            responseToSend.add(pitchWithOffersTemp);

            if( repository.findOffer(pitch.getPitch_id()) >0){
                //pitchWithOffers.put(pitch.getPitch_id(), repository.fetchOffer(pitch.getPitch_id()));
                pitchWithOffersTemp.setOffers(repository.fetchOffer(pitch.getPitch_id()));
            }else{
                //pitchWithOffers.put(pitch.getPitch_id(), new ArrayList<>());
                pitchWithOffersTemp.setOffers(new ArrayList<>());
            }
        }

//        for(Pitch pitch : allPitches){
//
//            PitchWithOffers pitchWithOffersTemp = new PitchWithOffers();
//
//            pitchWithOffersTemp.setOffers(pitchWithOffers.get(pitch.getPitch_id()));
//            pitchWithOffersTemp.setId( "" +pitch.getPitch_id());
//            pitchWithOffersTemp.setEntrepreneur(pitch.getEntrepreneur());
//            pitchWithOffersTemp.setPitchTitle(pitch.getPitchTitle());
//            pitchWithOffersTemp.setPitchIdea(pitch.getPitchIdea());
//            pitchWithOffersTemp.setAskAmount(pitch.getAskAmount());
//            pitchWithOffersTemp.setEquity(pitch.getEquity());
//
//            responseToSend.add(pitchWithOffersTemp);
//        }

        return responseToSend;
    }

    @Override
    public Integer savePitch(String entrepreneur, String pitchTitle, String pitchIdea, Double askAmount, Double equity) throws InvalidBodyException {
        if(entrepreneur == null || entrepreneur.equals("") || pitchTitle == null || pitchTitle.equals("") ||
            pitchIdea == null || pitchIdea.equals("") || askAmount == null || equity == null || equity >100){
            throw new InvalidBodyException("Invalid Request Body");
        }

        Integer pitch_id = repository.createPitch(entrepreneur, pitchIdea, pitchTitle, askAmount, equity);

        return  pitch_id;
    }

    @Override
    public Integer saveOffer(Integer pitch_id, String investor, Double amount, Double equity, String comment) throws PitchNotFoundException {
        if( pitch_id == null || investor == null || investor.equals("") || amount == null || equity == null ||
                comment == null || comment.equals("") || equity >100){
            throw new InvalidBodyException("Invalid Request Body");
        }

        Integer count;

        try {
            count = repository.findPitch(pitch_id);
        }catch (PitchNotFoundException p){
            throw new PitchNotFoundException("Pitch Not Found");
        }

        System.out.println("Count: " + count);

        if(count == 0){
            throw new PitchNotFoundException("Pitch Not Found");
        }

        Integer offer_id = repository.createOffer(pitch_id, investor, amount, equity, comment);

        return offer_id;
    }

    @Override
    public PitchWithOffers getPitchWithOffer(Integer pitch_id) throws PitchNotFoundException {
        if(pitch_id == null){
            throw new PitchNotFoundException("Pitch Not Found");
        }

        Integer count;
        try {
            count = repository.findPitch(pitch_id);
        } catch (PitchNotFoundException p){
            throw new PitchNotFoundException("Pitch Not Found");
        }

        if(count == 0){
            throw new PitchNotFoundException("Pitch Not Found");
        }

        Pitch pitch = repository.fetchPitch(pitch_id);

        List<OfferForPitch> offersForPitch = repository.fetchOffer(pitch_id);

        PitchWithOffers responseToSend = new PitchWithOffers();

        responseToSend.setId("" + pitch.getPitch_id());
        responseToSend.setEntrepreneur(pitch.getEntrepreneur());
        responseToSend.setPitchTitle(pitch.getPitchTitle());
        responseToSend.setPitchIdea(pitch.getPitchIdea());
        responseToSend.setAskAmount(pitch.getAskAmount());
        responseToSend.setEquity(pitch.getEquity());
        responseToSend.setOffers(offersForPitch);

        return responseToSend;
    }
}
