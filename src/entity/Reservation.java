package entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Reservation {
    private String reId;
    private String bookDate;
    private String checkInDate;
    private String checkOutDate;
    private Boolean isCancelled;

    private String userId;
    private int roomNo;
    private String ItineraryID;
    private String StaffID;
    private String BillingID;
    private boolean hasPaid;
    private int amount;

    // extra attributes
    private String roomType;
    private int roomPrice;


    private  Reservation(ReservationBuilder builder){
        this.reId = builder.reId;
        this.bookDate = builder.bookDate;
        this.checkInDate = builder.checkInDate;
        this.checkOutDate = builder.checkOutDate;
        this.isCancelled = builder.isCancelled;

        this.userId = builder.userId;
        this.roomNo = builder.roomNo;
        this.ItineraryID = builder.ItineraryID;
        this.StaffID = builder.StaffID;
        this.BillingID = builder.BillingID;
        this.hasPaid = builder.hasPaid;
        this.amount = builder.amount;

        this.roomType = builder.roomType;
        this.roomPrice = builder.roomPrice;
    }

    public static class ReservationBuilder{
        private String reId;
        private String bookDate;
        private String checkInDate;
        private String checkOutDate;
        private Boolean isCancelled;

        private String userId;
        private int roomNo;
        private String ItineraryID;
        private String StaffID;
        private String BillingID;
        private boolean hasPaid;
        private int amount;

        private String roomType;
        private int roomPrice;

        public ReservationBuilder setReId( String reId){
            this.reId = reId;
            return this;
        }

        public ReservationBuilder setBookDate(String bookDate){
            this.bookDate = bookDate;
            return this;
        }

        public ReservationBuilder setCheckInDate(String checkInDate){
            this.checkInDate = checkInDate;
            return this;
        }

        public ReservationBuilder setCheckOutDate(String checkOutDate){
            this.checkOutDate = checkOutDate;
            return this;
        }

        public ReservationBuilder setIsCancelled(Boolean isCancelled){
            this.isCancelled = isCancelled;
            return this;
        }

        public ReservationBuilder setUserId(String userId){
            this.userId = userId;
            return this;
        }

        public ReservationBuilder setRoomNo(int roomNo){
            this.roomNo = roomNo;
            return this;
        }

        public ReservationBuilder setItineraryID(String ItineraryID){
            this.ItineraryID = ItineraryID;
            return this;
        }

        public ReservationBuilder setStaffID(String StaffID){
            this.StaffID = StaffID;
            return this;
        }

        public ReservationBuilder setBillingID(String BillingID){
            this.BillingID = BillingID;
            return this;
        }

        public ReservationBuilder setHasPaid(Boolean hasPaid){
            this.hasPaid = hasPaid;
            return this;
        }

        public ReservationBuilder setAmount(int amount){
            this.amount = amount;
            return this;
        }

        public ReservationBuilder setRoomType(String roomType){
            this.roomType = roomType;
            return this;
        }

        public ReservationBuilder setRoomPrice(int roomPrice){
            this.roomPrice = roomPrice;
            return this;
        }

        public int getRoomNo(){
            return this.roomNo;
        }

        public String getRoomType(){
            return this.roomType;
        }

        public int getRoomPrice(){
            return this.roomPrice;
        }

        public String getCheckInDate(){
            return this.checkInDate;
        }

        public String getCheckOutDate(){
            return this.checkOutDate;
        }

        public Reservation build(){return new Reservation(this);}

    }


    public String getReId() {
        return reId;
    }

    public String getBookDate() {
        return bookDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public String getUserId() {
        return userId;
    }
    public int getRoomNo() {
        return roomNo;
    }
    public String getItineraryID() {
        return ItineraryID;
    }
    public String getStaffID() {
        return StaffID;
    }
    public String getBillingID() {
        return BillingID;
    }
    public boolean gethasPaid() {
        return hasPaid;
    }
    public int getamount() {
        return amount;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomPrice() {
        return roomPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return reId.equals(that.reId);
    }

    // TODO: this has roomNo as the hash instead of rId!!!
    @Override
    public int hashCode() {
        return Objects.hash(roomNo);
    }



    public JSONObject toJSONObject() { // for use of frontend to present to client
        JSONObject obj = new JSONObject();
        try {
            obj.put("reId", reId);
            obj.put("bookDate", bookDate);
            obj.put("checkInDate",checkInDate);
            obj.put("checkOutDate", checkOutDate);
            obj.put("isCancelled", isCancelled);

            obj.put("userId", userId);
            obj.put("roomNo", roomNo);
            obj.put("ItineraryID", ItineraryID);
            obj.put("StaffID", StaffID);
            obj.put("BillingID", BillingID);
            obj.put("hasPaid", hasPaid);
            obj.put("amount", amount);

            obj.put("roomType", roomType);
            obj.put("roomPrice", roomPrice);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reId='" + reId + '\'' +
                ", bookDate='" + bookDate + '\'' +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                ", isCancelled=" + isCancelled + '\'' +

                ", userId=" + userId + '\'' +
                ", roomNo=" + roomNo + '\'' +
                ", ItineraryID=" + ItineraryID + '\'' +
                ", StaffID=" + StaffID + '\'' +
                ", BillingID=" + BillingID + '\'' +
                ", hasPaid=" + hasPaid +
                ", amount=" + amount +
                '}';
    }
}
