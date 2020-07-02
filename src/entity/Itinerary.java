package entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Itinerary {

    private String ItineraryID;
    //true of false
    private String Category;
    private int PartySize;


    private Itinerary(ItineraryBuilder builder){
        this.ItineraryID = builder.ItineraryID;
        this.Category = builder.Category;
        this.PartySize = builder.PartySize;
    }


    public static class ItineraryBuilder{
        private String ItineraryID;
        //true of false
        private String Category;
        private int PartySize;



        public ItineraryBuilder setItID(String Id){
            this.ItineraryID = Id;
            return this;
        }

        public ItineraryBuilder setCategory(String c){
            this.Category = c;
            return this;
        }

        public ItineraryBuilder setPartSize(int size){
            this.PartySize = size;
            return this;
        }


        public Itinerary build(){return new Itinerary(this);};


    }

    public String getItineraryID() {
        return ItineraryID;
    }

    public String getCategory() {
        return Category;
    }

    public int getPartySize() {
        return PartySize;
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "getItineraryID='" + ItineraryID + '\'' +
                ", itineraryType='" + Category + '\'' +
                ", getPartySize='" + PartySize +
                '}';
    }


    public JSONObject toJSONObject() { // for use of frontend to present to client
        JSONObject obj = new JSONObject();
        try {
            obj.put("ItineraryID", ItineraryID);
            obj.put("Category", Category);
            obj.put("PartySize",PartySize);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }



}
