package entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class PointsOfInterests {

    private String PID;
    //true of false
    private String pName;
    private int pDuration;
    private int pPrice;




    private PointsOfInterests(PointsOfInterestsBuilder builder){
        this.PID = builder.PID;
        this.pName = builder.pName;
        this.pDuration = builder.pDuration;
        this.pPrice = builder.pPrice;
    }


    public static class PointsOfInterestsBuilder {
        private String PID;
        //true of false
        private String pName;
        private int pDuration;
        private int pPrice;

        public PointsOfInterestsBuilder setPID(String PID){
            this.PID =PID;
            return this;
        }

        public PointsOfInterestsBuilder setpName(String pName){
            this.pName = pName;
            return this;
        }

        public PointsOfInterestsBuilder setpDuration(int pDuration){
            this.pDuration = pDuration;
            return this;
        }

        public PointsOfInterestsBuilder setpPrice(int pPrice){
            this.pPrice = pPrice;
            return this;
        }

        public PointsOfInterests build(){return new PointsOfInterests(this);};


    }

    public String getPID() {
        return PID;
    }

    public String getpName() {
        return pName;
    }

    public int getpDuration() {
        return pDuration;
    }

    public int getpPrice() {
        return pPrice;
    }


    @Override
    public String toString() {
        return "PointsOfInterests{" +
                "PID='" + PID + '\'' +
                ", pName='" + pName + '\'' +
                ", pDuration='" + pDuration + '\'' +
                ", pPrice='" + pPrice +
                '}';
    }


    public JSONObject toJSONObject() { // for use of frontend to present to client
        JSONObject obj = new JSONObject();
        try {
            obj.put("PID", PID);
            obj.put("pName", pName);
            obj.put("pDuration",pDuration);
            obj.put("pPrice", pPrice);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        PointsOfInterests p = (PointsOfInterests) o;
        return PID.equals(p.PID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PID);
    }
}

