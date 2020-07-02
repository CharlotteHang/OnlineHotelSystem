package db.mysql;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import db.DBConnection;
import entity.Item;
import entity.Itinerary;
import entity.Manager;
import entity.PointsOfInterests;
import entity.Reservation;
import entity.Room;
import entity.Type;
import entity.User;

public class MySQLConnection implements DBConnection {
    // comment;
    // second comment;

    private static Connection conn;
    private static MySQLConnection instance;


    public MySQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(MySQLDBUtil.URL);
            System.out.println("Conncected to DB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        if(conn != null){
            try{
                conn.close();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }// our project start at here

    @Override
    public Map<String, Integer> managerGetNumOfUserForIt() {
        if (conn == null) {
            return null;
        }
        int res = 0;
        Map<String,Integer> map = new HashMap<>();

        try{
            String sql = "Select count(*) as num, ItineraryID from Reservation group by ItineraryID";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                map.put(rs.getString("ItineraryID"),rs.getInt("num"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return map;


    }

    @Override
    public int managerGetNumOfBookedRoom() {

        if (conn == null) {
            return 0;
        }
        int res = 0;

        try{
            String sql = "SELECT count(*) as NumOfBookedRooms from Room where roomNo not in (select roomNo from Reservation)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            res = rs.getInt("NumOfBookedRooms");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return res;
    }

    @Override
    public List<PointsOfInterests> managerGetPIs(Itinerary iT) {
        if (conn == null) {
            return new ArrayList<>();
        }


        List<PointsOfInterests> pOIs = new ArrayList<>();
        String iTid = iT.getItineraryID();
        try{
            String sql = "SELECT * FROM PointsOfInterest WHERE PID IN (SELECT PID FROM includes WHERE ItineraryID = ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, iTid);
            ResultSet rs = stmt.executeQuery();
            PointsOfInterests.PointsOfInterestsBuilder builder = new PointsOfInterests.PointsOfInterestsBuilder();

            while (rs.next()) {
                builder.setPID(rs.getString("PID"));
                builder.setpName(rs.getString("Name"));
                builder.setpDuration(rs.getInt("Duration"));
                builder.setpPrice(rs.getInt("Price"));

                pOIs.add(builder.build());
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }



        return pOIs;

    }


    @Override
    public void managerInsertIti(String iid, String itype, String isize) {


        try {
            String sql = "INSERT INTO Itinerary (ItineraryID,Category,PartySize) VALUES(?,?,?)";
            PreparedStatement stmt =  conn.prepareStatement(sql);
            stmt.setString(1,iid);
            stmt.setString(2,itype);
            stmt.setInt(3,Integer.valueOf(isize));
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void managerInsertCombo(String iid, String pids) {
        String[] pidString =pids.split("\\,");
        for(int i = 0;i<pidString.length;i++){

            try {
                String sql = "INSERT INTO Includes (ItineraryID,PID) VALUES(?,?)";
                PreparedStatement stmt =  conn.prepareStatement(sql);
                stmt.setString(1,iid);
                stmt.setString(2,pidString[i]);
                stmt.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public Set<Itinerary> managerGetAllIti() {
        if (conn == null) {
            return new HashSet<>();
        }

        Set<Itinerary> itis = new HashSet<>();
        try{
            String sql = "SELECT * FROM Itinerary";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Itinerary.ItineraryBuilder builder = new Itinerary.ItineraryBuilder();
            while (rs.next()) {

                builder.setItID(rs.getString("ItineraryID"));
                builder.setCategory(rs.getString("Category"));
                builder.setPartSize(rs.getInt("PartySize"));
                itis.add(builder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itis;
    }

    @Override
    public void managerDeleteIti(String iId){
        if(conn == null) return;
        try{
            String sql = "DELETE FROM Includes WHERE ItineraryID = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, iId);
            stmt.execute();


            sql = "DELETE FROM Itinerary WHERE ItineraryID = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, iId);
            stmt.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    @Override
    public List<PointsOfInterests> managerGetPIs(){
        if (conn == null) {
            return new ArrayList<>();
        }

        List<PointsOfInterests> pointOfInterests = new ArrayList<>();
        try{
            String sql = "SELECT * FROM PointsOfInterest";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            PointsOfInterests.PointsOfInterestsBuilder builder = new PointsOfInterests.PointsOfInterestsBuilder();

            while (rs.next()) {
                builder.setPID(rs.getString("PID"));
                builder.setpName(rs.getString("Name"));
                builder.setpDuration(rs.getInt("Duration"));
                builder.setpPrice(rs.getInt("Price"));

                pointOfInterests.add(builder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return pointOfInterests;

    }




    @Override
    public Set<User> managerGetAllUsers() {

        if (conn == null) {
            return new HashSet<>();
        }

        Set<User> users = new HashSet<>();
        try{
            String sql = "SELECT * FROM User";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            User.UserBuilder builder = new User.UserBuilder();

            while (rs.next()) {
                builder.setUserId(rs.getString("CustomerID"));
                builder.setUserName(rs.getString("FullName"));
                builder.setUserPassword(rs.getString("Password"));
                builder.setUserEmail(rs.getString("Email"));
                builder.setUserPhone(rs.getString("PhoneNumber"));
                builder.setAddress(rs.getString("Address"));
                builder.setPoints(rs.getInt("Points"));
                users.add(builder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }

    public Set<String> managerGetUsersBookAll() {
        if (conn == null) {
            return new HashSet<>();
        }

        Set<String> UsersBookAll = new HashSet<>();
        try{
            String sql2 = "select U.CustomerID as ID \n" +
                    "FROM User U\n" +
                    "WHERE NOT EXISTS\n" +
                    "(select * from Itinerary I\n" +
                    "where not exists\n" +
                    " (select R.ItineraryID \n" +
                    " from Reservation R\n" +
                    " where R.userId = U.CustomerID AND I.ItineraryID = R.ItineraryID));";
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            ResultSet rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                UsersBookAll.add(rs2.getString("ID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return UsersBookAll;

    }


    @Override
    public Set<Room> managerGetAllRooms() {
        if (conn == null) {
            return new HashSet<>();
        }

        Set<Room> rooms = new HashSet<>();
        try{
            String sql1 = "Drop View IF EXISTS User__Reservaton;";
            String sql2 = "Drop View IF EXISTS Room__Full;";
            String sql3 = "CREATE VIEW User__Reservaton AS SELECT roomNo, User.CustomerID AS CustomerID FROM User, Reservation WHERE Reservation.userId = User.CustomerID; ";
            String sql4 = "CREATE VIEW Room__Full AS SELECT  Room.roomNo, Classification, StaffID_SERVICE, CustomerID FROM Room LEFT OUTER JOIN User__Reservaton ON User__Reservaton.roomNo = Room.roomNo;";
            String sql5 = "SELECT  Room__Full.roomNo, Room__Full.Classification, Room__Full.StaffID_SERVICE, Room__Full.CustomerID, Price.Price FROM Room__Full, RoomType, Price WHERE Room__Full.Classification = RoomType.Classification AND RoomType.Amenities = Price.Amenities;";

                    ;
            PreparedStatement stmt = conn.prepareStatement(sql1);
            stmt.execute();
            stmt = conn.prepareStatement(sql2);
            stmt.execute();
            stmt = conn.prepareStatement(sql3);
            stmt.execute();
            stmt = conn.prepareStatement(sql4);
            stmt.execute();
            stmt = conn.prepareStatement(sql5);
            ResultSet rs = stmt.executeQuery();
            Room.RoomBuilder builder = new Room.RoomBuilder();
            while (rs.next()) {

                builder.setRoomId(rs.getString("roomNo"));
                builder.setRoomType(getRoomType(rs.getString("Classification")));
                builder.setStaffID(rs.getString("StaffID_SERVICE"));
                //builder.setStatus(rs.getBoolean("Status"));
                builder.setCustomerID(rs.getString("CustomerID"));
                builder.setPrice(rs.getInt("Price"));
                rooms.add(builder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public Type getRoomType(String s) {
        switch (s) {
            case "LUXURY1" :
                return Type.LUXURY1;
            case "LUXURY2" :
                return Type.LUXURY2;
            case "LUXURY3" :
                return Type.LUXURY3;
            case "LUXURY4" :
                return Type.LUXURY4;
            default :
                return Type.DEFAULT;
        }
    }


    @Override
    public Set<Manager> managerGetAllManagers() {
        if (conn == null) {
            return new HashSet<>();
        }

        Set<Manager> managers = new HashSet<>();
        try{
            String sql = "SELECT * FROM Manager";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Manager.StaffBuilder builder = new Manager.StaffBuilder();
            while (rs.next()) {

                builder.setStaffId(rs.getString("staffId"));
                builder.setstaffName(rs.getString("FullName"));
                builder.setstaffPassword(rs.getString("Password"));
                builder.setstaffPhone(rs.getString("PhoneNumber"));
                managers.add(builder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managers;
    }




    //our project end at here;
    @Override
    public void setFavoriteItems(String userId, List<String> itemIds) {
        if(conn == null) {
            return;
        }

        String query ="INSERT INTO history (user_id,item_id) VALUE (?,?)";
        try{
            PreparedStatement statement =conn.prepareStatement(query);
            for(String itemId : itemIds) {
                statement.setString(1,userId);
                statement.setString(2,itemId);
                statement.execute();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void unsetFavoriteItems(String userId, List<String> itemIds) {
        if(conn == null) {
            return;
        }

        String query ="DELETE FROM history WHERE user_id = ? and item_id = ?";
        try{
            PreparedStatement statement =conn.prepareStatement(query);
            for(String itemId : itemIds) {
                statement.setString(1,userId);
                statement.setString(2,itemId);
                statement.execute();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> getFavoriteItemIds(String userId) {
        if (conn == null) {
            return new HashSet<>();
        }

        Set<String> favoriteItemIds = new HashSet<>();

        try {
            String sql = "SELECT item_id from history where user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String itemId = rs.getString("item_id");
                favoriteItemIds.add(itemId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return favoriteItemIds;

    }

    @Override
    public Set<Item> getFavoriteItems(String userId) {
        if (conn == null) {
            return new HashSet<>();
        }

        Set<Item> favoriteItems = new HashSet<>();
        Set<String> itemIds = getFavoriteItemIds(userId);

        try {
            String sql = "SELECT * FROM items WHERE item_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (String itemId : itemIds) {
                stmt.setString(1, itemId);

                ResultSet rs = stmt.executeQuery();

                Item.ItemBuilder builder = new Item.ItemBuilder();

                while (rs.next()) {
                    builder.setItemId(rs.getString("item_id"));
                    builder.setName(rs.getString("name"));
                    builder.setAddress(rs.getString("address"));
                    builder.setImageUrl(rs.getString("image_url"));
                    builder.setUrl(rs.getString("url"));
                    builder.setCategories(getCategories(itemId));
                    builder.setDistance(rs.getDouble("distance"));
                    builder.setRating(rs.getDouble("rating"));

                    favoriteItems.add(builder.build());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteItems;
    }


    @Override
    public Set<String> getCategories(String itemId) {
        if (conn == null) {
            return null;
        }
        Set<String> categories = new HashSet<>();
        try {
            String sql = "SELECT category from categories WHERE item_id = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, itemId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }



    @Override
    public List<Item> searchItems(double lat, double lon, String term) {


//        TicketMasterAPI tmAPI = new TicketMasterAPI();
//        List<Item> items = tmAPI.search(lat, lon, term);
//        for (Item item : items) {
//            saveItem(item);
//        }
        return new ArrayList<>();
    }


    @Override
    public void saveItem(Item item) {
        if (conn == null) {
            return;
        }

        try {
            // SQL injection
            // Example:
            // SELECT * FROM users WHERE username = '<username>' AND password = '<password>';
            //
            // sql = "SELECT * FROM users WHERE username = '" + username + "'
            //       AND password = '" + password + "'"
            //
            // username: aoweifjoawefijwaoeifj
            // password: 123456' OR '1' = '1
            //
            // SELECT * FROM users WHERE username = 'aoweifjoawefijwaoeifj' AND password = '123456' OR '1' = '1'
            String sql = "INSERT IGNORE INTO items VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getItemId());
            stmt.setString(2, item.getName());
            stmt.setDouble(3, item.getRating());
            stmt.setString(4, item.getAddress());
            stmt.setString(5, item.getImageUrl());
            stmt.setString(6, item.getUrl());
            stmt.setDouble(7, item.getDistance());
            stmt.execute();

            sql = "INSERT IGNORE INTO categories VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            for (String category : item.getCategories()) {
                stmt.setString(1, item.getItemId());
                stmt.setString(2, category);
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getFullname(String CustomerId) {
        if (conn == null) {
            return null;
        }
        String name = "";
        try {
            String sql = "SELECT FullName from User WHERE CustomerID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, CustomerId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                name =  rs.getString("FullName");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public boolean verifyLogin(String userId, String password) {
        if (conn == null) {
            return false;
        }
        try {
            String sql = "SELECT CustomerID from User WHERE CustomerID = ? and Password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean register(String userId, String password, String name, String phoneNumber) {
        if (conn == null) {
            return false;
        }
        String query = "INSERT INTO User (CustomerID, Password, FullName,PhoneNumber,Email,address,Points,Paymentid,CreditCardNumber,BillingAddress) " +
                "VALUES (?, ?, ?, ?,'email','address',1,1000000,222,'address')";
        try {
            // check if user_id has been registered
            String sql = "SELECT CustomerID from User WHERE CustomerID = ? ";
            PreparedStatement statement1 = conn.prepareStatement(sql);
            statement1.setString(1, userId);

            ResultSet rs = statement1.executeQuery();
            String username = null;
            while (rs.next()) {
                username = rs.getString("CustomerID");
                if (username.equals(userId)) {
                    return false;
                }
            }
            // not registered
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, userId);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, phoneNumber);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean changePassword(String userId, String newPassword, String oldPassword) {
        if (conn == null) {
            return false;
        }
        try {
            // check if user_id has been registered
            String sql = "SELECT Password from User WHERE CustomerID = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);

            ResultSet rs = statement.executeQuery();
            String password = null;
            System.out.println(1);
            while (rs.next()) {
                password = rs.getString("Password");
            }
            if (password == null || !oldPassword.equals(password)) {
                return false;
            }
            System.out.println(2);
            String sql2 = "UPDATE User SET Password = ? WHERE CustomerID = ?";
            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setString(1, newPassword);
            statement2.setString(2, userId);

            statement2.execute();

            System.out.println(3);
            // not registered
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Set<Room> userGetAllRooms() {
        if (conn == null) {
            return new HashSet<>();
        }

        Set<Room> rooms = new HashSet<>();
        try{
            String sql1 = "Drop View IF EXISTS Reserved__Room;";
            String sql2 = "Drop View IF EXISTS Room__Full;";
            String sql3 = "Drop View IF EXISTS Final__Avail__Rooms;";
            String sql4 = "CREATE VIEW Reserved__Room AS SELECT roomNo, User.CustomerID AS CustomerID FROM User, Reservation WHERE Reservation.userId = User.CustomerID; ";
            String sql5 = "CREATE VIEW Room__Full AS SELECT Room.roomNo, Room.Classification, Reserved__Room.CustomerID FROM Room LEFT OUTER JOIN Reserved__Room ON Reserved__Room.roomNo = Room.roomNo WHERE Reserved__Room.CustomerID IS NULL;";
            String sql6 = "CREATE VIEW Final__Avail__Rooms AS SELECT Room__Full.roomNo, Room__Full.Classification, Price.Price FROM Room__Full, RoomType, Price WHERE Room__Full.Classification = RoomType.Classification AND RoomType.Amenities = Price.Amenities;";
            String sql7 = "SELECT * FROM Final__Avail__Rooms;";

            PreparedStatement stmt = conn.prepareStatement(sql1);
            stmt.execute();
            stmt = conn.prepareStatement(sql2);
            stmt.execute();
            stmt = conn.prepareStatement(sql3);
            stmt.execute();
            stmt = conn.prepareStatement(sql4);
            stmt.execute();
            stmt = conn.prepareStatement(sql5);
            stmt.execute();
            stmt = conn.prepareStatement(sql6);
            stmt.execute();
            stmt = conn.prepareStatement(sql7);
            ResultSet rs = stmt.executeQuery();
            Room.RoomBuilder builder = new Room.RoomBuilder();

            while (rs.next()) {
                builder.setRoomId(rs.getString("roomNo"));
                builder.setRoomType(getRoomType(rs.getString("Classification")));
                builder.setPrice(rs.getInt("Price"));
                rooms.add(builder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public List<Room> userGetRoomsPriceAsc() {
        if (conn == null) {
            return new ArrayList<>();
        }

        List<Room> rooms = new ArrayList<>();
        try{
            String sql1 = "Drop View IF EXISTS Vacant__Room;";
            String sql2 = "Drop View IF EXISTS Room__Full;";
            String sql3 = "Drop View IF EXISTS Rooms__Price__Asc;";
            String sql4 = "CREATE VIEW Vacant__Room AS SELECT roomNo, User.CustomerID AS CustomerID FROM User, Reservation WHERE Reservation.userId = User.CustomerID; ";
            String sql5 = "CREATE VIEW Room__Full AS SELECT Room.roomNo, Classification, CustomerID FROM Room LEFT OUTER JOIN Vacant__Room ON Vacant__Room.roomNo = Room.roomNo WHERE CustomerID IS NULL;";
            String sql6 = "CREATE VIEW Rooms__Price__Asc AS SELECT Room__Full.roomNo, Room__Full.Classification, Price.Price FROM Room__Full, RoomType, Price WHERE Room__Full.Classification = RoomType.Classification AND RoomType.Amenities = Price.Amenities ORDER BY Price.Price;";
            String sql7 = "SELECT * FROM Rooms__Price__Asc;";


            PreparedStatement stmt = conn.prepareStatement(sql1);
            stmt.execute();
            stmt = conn.prepareStatement(sql2);
            stmt.execute();
            stmt = conn.prepareStatement(sql3);
            stmt.execute();
            stmt = conn.prepareStatement(sql4);
            stmt.execute();
            stmt = conn.prepareStatement(sql5);
            stmt.execute();
            stmt = conn.prepareStatement(sql6);
            stmt.execute();
            stmt = conn.prepareStatement(sql7);
            ResultSet rs = stmt.executeQuery();
            Room.RoomBuilder builder = new Room.RoomBuilder();
            while (rs.next()) {

                builder.setRoomId(rs.getString("roomNo"));
                builder.setRoomType(getRoomType(rs.getString("Classification")));
                builder.setPrice(rs.getInt("Price"));
                rooms.add(builder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public List<Room> managerGetRoomsPriceAsc() {
        if (conn == null) {
            return new ArrayList<>();
        }

        List<Room> rooms = new ArrayList<>();
        try{
            String sql1 = "Drop View IF EXISTS User__Reservaton;";
            String sql2 = "Drop View IF EXISTS Room__Full;";
            String sql3 = "CREATE VIEW User__Reservaton AS SELECT roomNo, User.CustomerID AS CustomerID FROM User, Reservation WHERE Reservation.userId = User.CustomerID; ";
            String sql4 = "CREATE VIEW Room__Full AS SELECT  Room.roomNo, Classification, StaffID_SERVICE, CustomerID FROM Room LEFT OUTER JOIN User__Reservaton ON User__Reservaton.roomNo = Room.roomNo;";
            String sql5 = "SELECT  Room__Full.roomNo, Room__Full.Classification, Room__Full.StaffID_SERVICE, Room__Full.CustomerID, Price.Price FROM Room__Full, RoomType, Price WHERE Room__Full.Classification = RoomType.Classification AND RoomType.Amenities = Price.Amenities ORDER BY Price.Price;";

            PreparedStatement stmt = conn.prepareStatement(sql1);
            stmt.execute();
            stmt = conn.prepareStatement(sql2);
            stmt.execute();
            stmt = conn.prepareStatement(sql3);
            stmt.execute();
            stmt = conn.prepareStatement(sql4);
            stmt.execute();
            stmt = conn.prepareStatement(sql5);
            ResultSet rs = stmt.executeQuery();
            Room.RoomBuilder builder = new Room.RoomBuilder();
            while (rs.next()) {

                builder.setRoomId(rs.getString("roomNo"));
                builder.setRoomType(getRoomType(rs.getString("Classification")));
                builder.setStaffID(rs.getString("StaffID_SERVICE"));
                //builder.setStatus(rs.getBoolean("Status"));
                builder.setCustomerID(rs.getString("CustomerID"));
                builder.setPrice(rs.getInt("Price"));
                rooms.add(builder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public void userAddReservation(String userID, String roomID, String checkIn, String checkOut) {
        try {
            String sql = "INSERT INTO Reservation (reId, bookDate, checkInDate, checkOutDate, isCancelled, userId, roomNo, ItineraryID, staffID, BillingID, hasPaid, amount) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt =  conn.prepareStatement(sql);

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDate = formatter.format(date);
//            System.out.println(currentDate);

            stmt.setString(1,"10000967");
            stmt.setString(2, currentDate);
            stmt.setString(3, checkIn);
            stmt.setString(4, checkOut);
            stmt.setBoolean(5, FALSE);
            stmt.setString(6, userID);
            stmt.setString(7, roomID);
            stmt.setString(8, "4");
            stmt.setString(9, "1");
            stmt.setString(10, "1000999");
            stmt.setBoolean(11, TRUE);
            stmt.setInt(12, 275);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Reservation> userGetAllReservations(String userID) {
        if (conn == null) {
            return new HashSet<>();
        }

        Set<Reservation> reservations = new HashSet<>();
        try{
            String sql1 = "Drop View IF EXISTS Rooms__Reserved;";
            String sql2 = "Drop View IF EXISTS Rooms__Reserved__Test;";
            String sql3 = "CREATE VIEW Rooms__Reserved AS SELECT roomNo, checkInDate, checkOutDate FROM Reservation WHERE userId = ?;";
            String sql4 = "CREATE VIEW Rooms__Reserved__Test AS SELECT Rooms__Reserved.roomNo, Rooms__Reserved.checkInDate, Rooms__Reserved.checkOutDate, Room.Classification, Price.Price FROM Rooms__Reserved, Room, RoomType, Price WHERE Rooms__Reserved.roomNo = Room.roomNo AND Room.Classification = RoomType.Classification AND RoomType.Amenities = Price.Amenities;";
            String sql5 = "SELECT * FROM Rooms__Reserved__Test;";
//            String sql3 = "SELECT Reservation.roomNo, Room.Classification, Price.Price, Reservation.checkInDate, Reservation.checkOutDate FROM Reservation, Room, RoomType, Price WHERE Reservation.userId = ?; ";
//            String sql5 = "SELECT  Room__Full.roomNo, Room__Full.Classification, Room__Full.StaffID_SERVICE, Room__Full.CustomerID, Price.Price FROM Room__Full, RoomType, Price WHERE Room__Full.Classification = RoomType.Classification AND RoomType.Amenities = Price.Amenities;";

            PreparedStatement stmt = conn.prepareStatement(sql1);
            stmt.execute();
            stmt = conn.prepareStatement(sql2);
            stmt.execute();
            stmt = conn.prepareStatement(sql3);
            stmt.setString(1, userID);
            stmt.execute();
            stmt = conn.prepareStatement(sql4);
            stmt.execute();
            stmt = conn.prepareStatement(sql5);
            ResultSet rs = stmt.executeQuery();

            Reservation.ReservationBuilder builder = new Reservation.ReservationBuilder();
            while (rs.next()) {
                builder.setRoomNo(rs.getInt("roomNo"));
                builder.setRoomType(rs.getString("Classification"));
                builder.setRoomPrice(rs.getInt("Price"));
                builder.setCheckInDate(rs.getString("checkInDate"));
                builder.setCheckOutDate(rs.getString("checkOutDate"));
                reservations.add(builder.build());
                System.out.println("Reservation Object: Room #" + builder.getRoomNo() + " as " + builder.getRoomType() + " from "
                + builder.getCheckInDate() + " to " + builder.getCheckOutDate() + " at $" + builder.getRoomPrice());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public List<Itinerary> userGetAllItineraries() {
        if (conn == null) {
            return new ArrayList<>();
        }

        List<Itinerary> itis = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Itinerary";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Itinerary.ItineraryBuilder builder = new Itinerary.ItineraryBuilder();
            while (rs.next()) {

                builder.setItID(rs.getString("ItineraryID"));
                builder.setCategory(rs.getString("Category"));
                builder.setPartSize(rs.getInt("PartySize"));
                itis.add(builder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itis;
    }

    @Override
    public List<Itinerary> userSortItinerariesBySize() {
        if (conn == null) {
            return new ArrayList<>();
        }

        List<Itinerary> itis = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Itinerary ORDER BY PartySize";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Itinerary.ItineraryBuilder builder = new Itinerary.ItineraryBuilder();
            while (rs.next()) {

                builder.setItID(rs.getString("ItineraryID"));
                builder.setCategory(rs.getString("Category"));
                builder.setPartSize(rs.getInt("PartySize"));
                itis.add(builder.build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itis;
    }
}

















