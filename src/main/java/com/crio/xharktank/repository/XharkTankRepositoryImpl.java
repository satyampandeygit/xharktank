package com.crio.xharktank.repository;

import com.crio.xharktank.Models.OfferForPitch;
import com.crio.xharktank.Models.Pitch;
import com.crio.xharktank.exceptions.InvalidBodyException;
import com.crio.xharktank.exceptions.PitchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class XharkTankRepositoryImpl implements XharkTankRepository {

    private static final String SQL_CREATE_PITCH = "INSERT INTO pitches(pitch_id, entrepreneur, pitchTitle, " +
            "pitchIdea, askAmount, equity) VALUES(NEXTVAL('xharktank_pitch_seq'), ?, ?, ?, ?, ?)";

    private static final String SQL_CREATE_OFFER = "INSERT INTO offers(offer_id, investor, amount, " +
            "equity, comment, pitch_id) VALUES(NEXTVAL('xharktank_offer_seq'), ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_BY_PITCH_ID = "SELECT COUNT(*) FROM pitches WHERE pitch_id = ?";

    private static final String SQL_GET_PITCH = "SELECT * FROM pitches WHERE pitch_id = ?";

    private static final String SQL_FIND_OFFERS_FOR_PITCH_ID = "SELECT COUNT(*) FROM offers WHERE pitch_id = ?";

    private static final String SQL_GET_OFFER = "SELECT offer_id,investor,amount,equity,comment FROM offers WHERE pitch_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pitch> getAllThePitches() {

        String query = "SELECT pitch_id, entrepreneur, pitchTitle, pitchIdea, askAmount, equity FROM pitches ORDER BY createdTime DESC";

        return jdbcTemplate.query(query, new pitchMapper());
    }

    @Override
    public Integer createPitch(String entrepreneur, String pitchIdea, String pitchTitle, Double askAmount, Double equity) throws InvalidBodyException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            // using lambda to hold generated key
            jdbcTemplate.update( connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_PITCH, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, entrepreneur);
                ps.setString(2, pitchTitle);
                ps.setString(3, pitchIdea);
                ps.setDouble(4, askAmount);
                ps.setDouble(5, equity);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("pitch_id");
        }catch (Exception e){
            throw new InvalidBodyException("Invalid Request Body");
        }
    }

    @Override
    public Integer createOffer(Integer pitch_id, String investor, Double amount, Double equity, String comment) throws InvalidBodyException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update( connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_OFFER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, investor);
                ps.setDouble(2, amount);
                ps.setDouble(3, equity);
                ps.setString(4, comment);
                ps.setInt(5, pitch_id);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("offer_id");
        }catch (Exception e){
            throw new InvalidBodyException("Invalid Request Body");
        }
    }

    @Override
    public Integer findPitch(Integer pitch_id) throws PitchNotFoundException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_PITCH_ID, new Object[]{pitch_id}, Integer.class);
    }

    @Override
    public Integer findOffer(Integer pitch_id) throws PitchNotFoundException {
        return jdbcTemplate.queryForObject(SQL_FIND_OFFERS_FOR_PITCH_ID, new Object[]{pitch_id}, Integer.class);
    }

    @Override
    public Pitch fetchPitch(Integer pitch_id) {
        return jdbcTemplate.queryForObject(SQL_GET_PITCH, new Object[]{pitch_id}, pitchRowMapper);
    }

    @Override
    public List<OfferForPitch> fetchOffer(Integer pitch_id) {
        String query = "SELECT offer_id,investor,amount,equity,comment FROM offers WHERE pitch_id = " + pitch_id;
        return jdbcTemplate.query(query,new OfferMapper());
    }

    private final RowMapper<Pitch> pitchRowMapper = ((rs, rowNum) -> {
        return new Pitch( rs.getInt("pitch_id"),
                rs.getString("entrepreneur"),
                rs.getString("pitchIdea"),
                rs.getString("pitchTitle"),
                rs.getDouble("askAmount"),
                rs.getDouble("equity"));
    });

    private static final class OfferMapper implements RowMapper<OfferForPitch> {
        public OfferForPitch mapRow(ResultSet rs, int rowNum) throws SQLException{
            String offer_id = "" + rs.getInt("offer_id");
            String investor = rs.getString("investor");
            Double amount = rs.getDouble("amount");
            Double equity = rs.getDouble("equity");
            String comment = rs.getString("comment");

            OfferForPitch offer = new OfferForPitch( offer_id, investor, amount, equity, comment);
            return offer;
        }
    }

    private static final class pitchMapper implements RowMapper<Pitch>{
        public Pitch mapRow(ResultSet rs, int rowNum) throws SQLException{
            return new Pitch( rs.getInt("pitch_id"),
                    rs.getString("entrepreneur"),
                    rs.getString("pitchIdea"),
                    rs.getString("pitchTitle"),
                    rs.getDouble("askAmount"),
                    rs.getDouble("equity"));
        }
    }
}

