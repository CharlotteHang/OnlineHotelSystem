package entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Manager {

    private String staffId;

    private String staffName;
    private String staffPassword;
    private String staffPhone;


    private Manager(StaffBuilder builder){
        this.staffId = builder.staffId;
        this.staffName = builder.staffName;
        this.staffPassword = builder.staffPassword;

        this.staffPhone = builder.staffPhone;

    }


    public static class StaffBuilder{
        private String staffId;
        //true of false
        private String staffName;
        private String staffPassword;
        private String staffPhone;

        public StaffBuilder setStaffId(String staffId){
            this.staffId = staffId;
            return this;
        }

        public StaffBuilder setstaffName(String staffName){
            this.staffName = staffName;
            return this;
        }

        public StaffBuilder setstaffPassword(String staffPassword){
            this.staffPassword = staffPassword;
            return this;
        }

        public StaffBuilder setstaffPhone(String staffPhone){
            this.staffPhone = staffPhone;
            return this;
        }



        public Manager build(){return new Manager(this);};


    }

    public String getStaffId() {
        return staffId;
    }

    public String getstaffName() {
        return staffName;
    }

    public String getstaffPassword() {
        return staffPassword;
    }

    public String getstaffPhone() {
        return staffPhone;
    }


    @Override
    public String toString() {
        return "Staff{" +
                "StaffId='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                ", staffPassword='" + staffPassword + '\'' +
                ", staffPhone='" + staffPhone +
                '}';
    }


    public JSONObject toJSONObject() { // for use of frontend to present to client
        JSONObject obj = new JSONObject();
        try {
            obj.put("StaffId", staffId);
            obj.put("staffName", staffName);
            obj.put("staffPassword",staffPassword);
            obj.put("staffPhone", staffPhone);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof entity.User)) return false;
        Manager s = (Manager) o;
        return staffId.equals(s.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId);
    }
}