package entity;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class User {

    private String userId;
    //true of false
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String userAddress;

    private int points;
    private String PaymentId;
    private String CreditCardNumber;
    private String BillingAddress;



    private User(UserBuilder builder){
        this.userId = builder.userId;
        this.userName = builder.userName;
        this.userPassword = builder.userPassword;
        this.userEmail = builder.userEmail;
        this.userPhone = builder.userPhone;
        this.userAddress  = builder.userAddress;

        this.points = builder.points;
        this.PaymentId = builder.PaymentId;
        this.CreditCardNumber = builder.CreditCardNumber;
        this.BillingAddress = builder.BillingAddress;
    }


    public static class UserBuilder{
        private String userId;
        //true of false
        private String userName;

        private String userPassword;

        private String userEmail;

        private String userPhone;

        private String userAddress;

        private int points;
        private String PaymentId;
        private String CreditCardNumber;
        private String BillingAddress;

        public UserBuilder setUserId(String userId){
            this.userId =userId;
            return this;
        }

        public UserBuilder setUserName(String userName){
            this.userName = userName;
            return this;
        }

        public UserBuilder setUserPassword(String userPassword){
            this.userPassword = userPassword;
            return this;
        }

        public UserBuilder setUserEmail(String userEmail){
            this.userEmail = userEmail;
            return this;
        }

        public UserBuilder setUserPhone(String userPhone){
            this.userPhone = userPhone;
            return this;
        }

        public UserBuilder setAddress(String userAddress){
            this.userAddress = userAddress;
            return this;
        }

        public UserBuilder setPoints(int points){
            this.points = points;
            return this;
        }

        public UserBuilder setPaymentId(String PaymentId){
            this.PaymentId = PaymentId;
            return this;
        }

        public UserBuilder setCreditCardNumber(String CreditCardNumber){
            this.CreditCardNumber = CreditCardNumber;
            return this;
        }

        public UserBuilder setBillingAddress(String BillingAddress){
            this.BillingAddress = BillingAddress;
            return this;
        }

        public User build(){return new User(this);};


    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int pts) {
        this.points = pts;
    }
    public String getPaymentId() {
        return PaymentId;
    }
    public String getCreditCardNumber() {
        return CreditCardNumber;
    }
    public String getBillingAddress() {
        return BillingAddress;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +

                ", PaymentId='" + PaymentId + '\'' +
                ", CreditCardNumber='" + CreditCardNumber + '\'' +
                ", BillingAddress='" + BillingAddress + '\'' +
                ", points=" + points +
                '}';
    }


    public JSONObject toJSONObject() { // for use of frontend to present to client
        JSONObject obj = new JSONObject();
        try {
            obj.put("CustomerID", userId);
            obj.put("FullName", userName);
            obj.put("Password",userPassword);
            obj.put("PhoneNumber", userPhone);
            obj.put("Address",userAddress);
            obj.put("PaymentId", PaymentId);
            obj.put("CreditCardNumber", CreditCardNumber);
            obj.put("BillingAddress", BillingAddress);
            obj.put("Points", points);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
