package com.crio.xharktank.Controller;

import com.crio.xharktank.Models.Offer;
import com.crio.xharktank.Models.Pitch;
import com.crio.xharktank.Models.PitchWithOffers;
import com.crio.xharktank.Service.XharkTankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pitches")
@CrossOrigin
public class ServiceController {

    @Autowired
    private XharkTankService xharkTankService;

    @GetMapping
    public ResponseEntity<List<PitchWithOffers>> sendPitches(){
        System.out.println("Sending Pitches");
        List<PitchWithOffers> responseToSend = xharkTankService.sendPitches();
        return new ResponseEntity<>(responseToSend,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> savePitches(@RequestBody Pitch pitch){
        System.out.println("Saving Pitch " + pitch.getEntrepreneur());
        String entrepreneur = pitch.getEntrepreneur();
        String pitchTitle = pitch.getPitchTitle();
        String pitchIdea = pitch.getPitchIdea();
        Double askAmount = pitch.getAskAmount();
        Double equity = pitch.getEquity();

        Integer pitch_id = xharkTankService.savePitch(entrepreneur, pitchIdea,pitchTitle , askAmount, equity);

        Map<String, String> map = new HashMap<>();
        map.put("id", ""+ pitch_id);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{pitch_id}")
    public ResponseEntity<PitchWithOffers> fetchPitch(@PathVariable Integer pitch_id){
        return new ResponseEntity<>(xharkTankService.getPitchWithOffer(pitch_id),HttpStatus.OK);
    }

    @PostMapping("/{pitch_id}/makeOffer")
    public ResponseEntity<Map<String,String>> saveOffer(@RequestBody Offer offer, @PathVariable Integer pitch_id){
        System.out.println("Inside save offer");
        String investor = offer.getInvestor();
        Double amount = offer.getAmount();
        Double equity = offer.getEquity();
        String comment = offer.getComment();
        Integer offer_id = xharkTankService.saveOffer(pitch_id, investor, amount, equity, comment);
        Map<String, String> map = new HashMap<>();
        map.put("id", "" + offer_id);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}

