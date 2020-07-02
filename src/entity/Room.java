package entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

//use Builder pattern to build entity to avoid multiple constructor





public class Room {
    private String roomId;
    //true: Available, false: not Available
    //private Boolean status;
    //single,double,king size of room
    private Type roomType;

    private String StaffID;

    // add
    private String CustomerID = null;

    private int Price;


    private Room(RoomBuilder builder){
        this.roomId = builder.roomId;
        //this.status = builder.status;
        this.roomType = builder.roomType;
        this.StaffID = builder.StaffID;
        this.CustomerID = builder.CustomerID;
        this.Price = builder.Price;
    }


    public static class RoomBuilder{
        private String roomId;
        //true of false
        //private Boolean status;
        //single,double,king size of room
        private Type roomType;

        private String StaffID;

        //add
        private String CustomerID;

        private int Price;

        public RoomBuilder setRoomId(String roomId){
            this.roomId = roomId;
            return this;
        }

//        public RoomBuilder setStatus(Boolean status){
//            this.status = status;
//            return this;
//        }

        public RoomBuilder setRoomType(Type type){
            this.roomType = type;
            return this;
        }

        public RoomBuilder setStaffID(String StaffID){
            this.StaffID = StaffID;
            return this;
        }

        public RoomBuilder setCustomerID(String CustomerID){
            this.CustomerID = CustomerID;
            return this;
        }

        public RoomBuilder setPrice(int Price){
            this.Price = Price;
            return this;
        }

        public Room build(){return new Room(this);};


    }


    public String getRoomId() {
        return roomId;
    }

//    public Boolean getstatus() {
//        return status;
//    }

    public Type getRoomType() {
        return roomType;
    }

    public String getStaffID() {
        return StaffID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public String getRoomID() {
        return roomId;
    }

    public int getPrice() {
        return Price;
    }


    @Override
    public String toString() {
        String s = CustomerID.equals("") ? "" : ("Customer ID: " + CustomerID);
        return "Room{" +
                "roomId='" + roomId + '\'' +
//                ", status=" + status + '\''+
                s + '\'' +
                ", StaffID=" + StaffID + '\''+
                ", roomType=" + roomType +
                ", price=" + Price +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return roomId.equals(room.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }

    public JSONObject toJSONObject() { // for use of frontend to present to client
        JSONObject obj = new JSONObject();
        //String s = getStatusString();

        String staff_id = "Service Staff ID: " + StaffID;
        //add
        String sc = CustomerID == null ? "Available" : ("Customer ID: " + CustomerID);
        try {
            obj.put("roomId", roomId);
           // obj.put("status", s);
            obj.put("CustomerID", sc);
            obj.put("roomType",roomType.toString());
            obj.put("StaffID", staff_id);
            obj.put("Price", Price);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}



