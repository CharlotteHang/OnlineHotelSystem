
package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQLTableCreation {
    // Run this as Java application to reset db schema.
    public static void main(String[] args) {
        try {
            Connection conn = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(MySQLDBUtil.URL);
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (conn == null) {
                return;
            }



            Statement stmt = conn.createStatement();
            stmt = conn.createStatement();
            String sql = "DROP TABLE IF EXISTS Includes;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS PointsOfInterest;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Reservation;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Room;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS RoomType;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Price;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Itinerary;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Category_BasePrice;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Service;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Manager;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS User;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS Points_Tier;";
            stmt.executeUpdate(sql);
            sql = "DROP TABLE IF EXISTS users;";
            stmt.executeUpdate(sql);


            // Step 3 Create new tables
            sql = "CREATE TABLE Points_Tier(" +
                    "Points INTEGER PRIMARY KEY, " +
                    "Tier INTEGER);"

            ;
            stmt.execute(sql);

            //step 4 insert some tuples;


            sql="INSERT INTO Points_Tier (" +
                    "Points, Tier) " +
                    "VALUES\n" +
                    "(1, 1);";
            stmt.execute(sql);

            sql= "INSERT INTO Points_Tier (" +
                    "Points, Tier) " +
                    "VALUES\n" +
                    "(2, 2);";

            stmt.execute(sql);

            sql= "INSERT INTO Points_Tier (" +
                    "Points, Tier) " +
                    "VALUES\n" +
                    "(3, 3);";

            stmt.execute(sql);

            sql= "INSERT INTO Points_Tier (" +
                    "Points, Tier) " +
                    "VALUES\n" +
                    "(4, 4);";

            stmt.execute(sql);

            sql= "INSERT INTO Points_Tier (" +
                    "Points, Tier) " +
                    "VALUES\n" +
                    "(5, 5);";

            stmt.execute(sql);




            // Step 2 Drop tables in case they exist.
            stmt = conn.createStatement();
            // Step 3 Create new tables
            sql = "CREATE TABLE User(" +
                    "CustomerID CHAR(20) PRIMARY KEY, " +
                    "FullName CHAR(20), " +
                    "Password CHAR(20),"+
                    "Email CHAR(40),\n" +
                    "PhoneNumber CHAR(20), " +
                    "address CHAR(60), " +
                    "Points INTEGER," +
                    "PaymentId CHAR(20)," +
                    "CreditCardNumber CHAR(20)," +
                    "BillingAddress CHAR(80)" +
                    ",FOREIGN KEY (Points) REFERENCES Points_Tier(Points) ON DELETE CASCADE ON UPDATE CASCADE);";

            stmt.execute(sql);

            //step 4 insert some tuples;


            sql="INSERT INTO User (" +
                    "CustomerID, FullName,Password, Email, PhoneNumber, address, Points, PaymentId, CreditCardNumber, BillingAddress) " +
                    "VALUES\n" +
                    "('1', 'Rabbin','Rabbin', 'RabbinDioquino@gmail.com', '882-882-4444', '3899 Mt. Lehman, Abbotsford, BC, Canada', " +
                    "1, '100000001', '3201908463748923', '3899 Mt. Lehman, Abbotsford, BC, Canada');";
            stmt.execute(sql);

            sql= "INSERT INTO User (" +
                    "CustomerID, FullName,Password, Email, PhoneNumber, address, Points, PaymentId, CreditCardNumber, BillingAddress) " +
                    "VALUES\n" +
                    "('2', 'Lydia','Lydia', 'LydiaChow@gmail.com', '235-356-2674', 'Room 210 - 2386 East Mall, Vancouver, " +
                    "BC, Canada', 3, '100000022', '7847698309831564', 'Room 210 - 2386 East Mall, Vancouver, BC, Canada');";

            stmt.execute(sql);

            sql= "INSERT INTO User (" +
                    "CustomerID, FullName,Password, Email, PhoneNumber, address, Points, PaymentId, CreditCardNumber, BillingAddress) " +
                    "VALUES\n" +
                    "('3', 'Lydia','Lydia', 'LydiaChow@gmail.com', '335-3666-2674', '250 - 777 Dunsmuir Street, Vancouver, BC, Canada" +
                    " ', 3, '100000333', '9583798309831564', '250 - 777 Dunsmuir Street, Vancouver, BC, Canada');";

            stmt.execute(sql);

            sql= "INSERT INTO User (" +
                    "CustomerID, FullName,Password, Email, PhoneNumber, address, Points, PaymentId, CreditCardNumber, BillingAddress) " +
                    "VALUES\n" +
                    "('4', 'Chris','Chris', 'Chris@gmail.com', '764-356-2342', '5403-1st Ave S, Lethbridge, AB, Canada" +
                    " ', 5, '100004444', '0192398309831564', '5403-1st Ave S, Lethbridge, AB, Canada');";

            stmt.execute(sql);

            sql= "INSERT INTO User (" +
                    "CustomerID, FullName,Password, Email, PhoneNumber, address, Points, PaymentId, CreditCardNumber, BillingAddress) " +
                    "VALUES\n" +
                    "('5', 'Daria','Daria', 'Daria@gmail.com', '123-356-3634', '1200 - 555 West Hastings Street, Vancouver, BC, Canada," +
                    " ', 2, '100055555', '8573998309831564', '1200 - 555 West Hastings Street, Vancouver, BC, Canada');";

            stmt.execute(sql);




            // Step 2 Drop tables in case they exist.
            stmt = conn.createStatement();
            // Step 3 Create new tables
            sql = "CREATE TABLE Manager(" +
                    "staffId CHAR(20) PRIMARY KEY, " +
                    "FullName CHAR(20), " +
                    "Password CHAR(20),"+
                    "PhoneNumber CHAR(20));"

            ;
            stmt.execute(sql);

            //step 4 insert some tuples;


            sql="INSERT INTO Manager (" +
                    "staffId, FullName,Password, PhoneNumber) " +
                    "VALUES\n" +
                    "('1', 'Jack', 'Jack', '7798837584');";
            stmt.execute(sql);

            sql= "INSERT INTO Manager (" +
                    "staffId, FullName,Password, PhoneNumber) " +
                    "VALUES\n" +
                    "('2', 'Thomas', 'Thomas', '83749582093');";

            stmt.execute(sql);

            sql="INSERT INTO Manager (" +
                    "staffId, FullName,Password, PhoneNumber) " +
                    "VALUES\n" +
                    "('3', 'Maria', 'Maria', '6385937584');";
            stmt.execute(sql);

            sql="INSERT INTO Manager (" +
                    "staffId, FullName,Password, PhoneNumber) " +
                    "VALUES\n" +
                    "('4', 'Sue', 'Sue', '0192837584');";
            stmt.execute(sql);

            sql="INSERT INTO Manager (" +
                    "staffId, FullName,Password, PhoneNumber) " +
                    "VALUES\n" +
                    "('5', 'Judy', 'Judy', '6273837584');";
            stmt.execute(sql);



            stmt = conn.createStatement();
            // Step 3 Create new tables
            sql = "CREATE TABLE Service(" +
                    "staffId CHAR(20) PRIMARY KEY, " +
                    "FullName CHAR(20));"

            ;
            stmt.execute(sql);

            //step 4 insert some tuples;


            sql="INSERT INTO Service (" +
                    "staffId, FullName) " +
                    "VALUES\n" +
                    "('11', 'Noah');";
            stmt.execute(sql);

            sql= "INSERT INTO Service (" +
                    "staffId, FullName) " +
                    "VALUES\n" +
                    "('12', 'Liam');";

            stmt.execute(sql);

            sql="INSERT INTO Service (" +
                    "staffId, FullName) " +
                    "VALUES\n" +
                    "('13', 'Mason');";
            stmt.execute(sql);

            sql="INSERT INTO Service (" +
                    "staffId, FullName) " +
                    "VALUES\n" +
                    "('14', 'William');";
            stmt.execute(sql);

            sql="INSERT INTO Service (" +
                    "staffId, FullName) " +
                    "VALUES\n" +
                    "('15', 'Ethan');";
            stmt.execute(sql);



            stmt = conn.createStatement();
            // Step 3 Create new tables
            sql = "CREATE TABLE Category_BasePrice(" +
                    "Category CHAR(20) PRIMARY KEY, " +
                    "BasePrice INTEGER); "
            ;
            stmt.execute(sql);

            //step 4 insert some tuples;


            sql="INSERT INTO Category_BasePrice (" +
                    "Category, BasePrice) " +
                    "VALUES\n" +
                    "('LUXURY1', 1000);";
            stmt.execute(sql);

            sql= "INSERT INTO Category_BasePrice (" +
                    "Category, BasePrice) " +
                    "VALUES\n" +
                    "('LUXURY2', 1000);";

            stmt.execute(sql);

            sql= "INSERT INTO Category_BasePrice (" +
                    "Category, BasePrice) " +
                    "VALUES\n" +
                    "('DEFAULT1', 500);";

            stmt.execute(sql);

            sql= "INSERT INTO Category_BasePrice (" +
                    "Category, BasePrice) " +
                    "VALUES\n" +
                    "('DEFAULT2', 500);";

            stmt.execute(sql);

            sql= "INSERT INTO Category_BasePrice (" +
                    "Category, BasePrice) " +
                    "VALUES\n" +
                    "('DEFAULT3', 500);";






            stmt.execute(sql);
            // Step 3 Create new tables
            sql = "CREATE TABLE Itinerary(" +
                    "ItineraryID CHAR(20) PRIMARY KEY, " +
                    "Category CHAR(20), " +
                    "PartySize INTEGER," +
                    "FOREIGN KEY (Category) REFERENCES Category_BasePrice (Category) ON DELETE SET NULL ON UPDATE CASCADE); "
            ;
            stmt.execute(sql);

            //step 4 insert some tuples;


            sql="INSERT INTO Itinerary (" +
                    "ItineraryID, Category, PartySize) " +
                    "VALUES\n" +
                    "('1', 'LUXURY1', 5);";
            stmt.execute(sql);

            sql= "INSERT INTO Itinerary (" +
                    "ItineraryID, Category, PartySize) " +
                    "VALUES\n" +
                    "('2', 'LUXURY2',15);";

            stmt.execute(sql);

            sql= "INSERT INTO Itinerary (" +
                    "ItineraryID, Category, PartySize) " +
                    "VALUES\n" +
                    "('3', 'DEFAULT1', 20);";

            stmt.execute(sql);

            sql= "INSERT INTO Itinerary (" +
                    "ItineraryID, Category, PartySize) " +
                    "VALUES\n" +
                    "('4', 'DEFAULT2', 25);";

            stmt.execute(sql);

            sql= "INSERT INTO Itinerary (" +
                    "ItineraryID, Category, PartySize) " +
                    "VALUES\n" +
                    "('5', 'DEFAULT3', 30);";

            stmt.execute(sql);



            stmt = conn.createStatement();
            // Step 3 Create new tables
            sql = "CREATE TABLE Price(" +
                    "Amenities CHAR(10), BreakfastIncluded BOOLEAN, Price INTEGER, " +
                    "PRIMARY KEY(Amenities, BreakfastIncluded));"
            ;
            stmt.execute(sql);

            //step 4 insert some tuples;

            sql="INSERT INTO Price (" +
                    "Amenities, BreakfastIncluded, Price) " +
                    "VALUES\n" +
                    "('LEVEL1', TRUE, 300);";
            stmt.execute(sql);

            sql="INSERT INTO Price (" +
                    "Amenities, BreakfastIncluded, Price) " +
                    "VALUES\n" +
                    "('LEVEL2', TRUE, 250);";
            stmt.execute(sql);

            sql="INSERT INTO Price (" +
                    "Amenities, BreakfastIncluded, Price) " +
                    "VALUES\n" +
                    "('LEVEL3', FALSE, 200);";
            stmt.execute(sql);

            sql="INSERT INTO Price (" +
                    "Amenities, BreakfastIncluded, Price) " +
                    "VALUES\n" +
                    "('LEVEL4', FALSE, 150);";
            stmt.execute(sql);

            sql="INSERT INTO Price (" +
                    "Amenities, BreakfastIncluded, Price) " +
                    "VALUES\n" +
                    "('DEFAULT', FALSE, 100);";
            stmt.execute(sql);




            stmt = conn.createStatement();
            // Step 3 Create new tables
            sql = "CREATE TABLE RoomType(" +
                    "Classification CHAR(20) PRIMARY KEY, " +
                    "Amenities CHAR(10), BreakfastIncluded BOOLEAN, PetFriendly BOOLEAN, NonSmoking BOOLEAN, " +
                    "FOREIGN KEY (Amenities, BreakfastIncluded) REFERENCES Price (Amenities,BreakfastIncluded) " +
                    "ON DELETE CASCADE ON UPDATE CASCADE);";
            stmt.execute(sql);

            //step 4 insert some tuples;

            sql="INSERT INTO RoomType (" +
                    "Classification, Amenities, BreakfastIncluded, PetFriendly, NonSmoking) " +
                    "VALUES\n" +
                    "('LUXURY1', 'LEVEL1', TRUE, TRUE, TRUE);";
            stmt.execute(sql);

            sql= "INSERT INTO RoomType (" +
                    "Classification, Amenities, BreakfastIncluded, PetFriendly, NonSmoking) " +
                    "VALUES\n" +
                    "('LUXURY2', 'LEVEL1', TRUE, TRUE, TRUE);";
            stmt.execute(sql);

            sql= "INSERT INTO RoomType (" +
                    "Classification, Amenities, BreakfastIncluded, PetFriendly, NonSmoking) " +
                    "VALUES\n" +
                    "('LUXURY3', 'LEVEL2', TRUE, TRUE, TRUE);";
            stmt.execute(sql);

            sql= "INSERT INTO RoomType (" +
                    "Classification, Amenities, BreakfastIncluded, PetFriendly, NonSmoking) " +
                    "VALUES\n" +
                    "('LUXURY4', 'LEVEL2', TRUE, TRUE, TRUE);";
            stmt.execute(sql);

            sql= "INSERT INTO RoomType (" +
                    "Classification, Amenities, BreakfastIncluded, PetFriendly, NonSmoking) " +
                    "VALUES\n" +
                    "('DEFAULT', 'DEFAULT', FALSE, FALSE, FALSE);";
            stmt.execute(sql);





            stmt = conn.createStatement();
            // Step 3 Create new tables
            sql = "CREATE TABLE Room(" +
                    "roomNo INTEGER PRIMARY KEY, Classification CHAR(20), StaffID_SERVICE CHAR(20), " +
                    "FOREIGN KEY (Classification) REFERENCES RoomType (Classification) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "FOREIGN KEY (StaffID_SERVICE) REFERENCES Service (staffId) ON DELETE CASCADE ON UPDATE CASCADE);"
            ;
            stmt.execute(sql);

            //step 4 insert some tuples;

            sql="INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(304, 'LUXURY1', 11);";
            stmt.execute(sql);

            sql= "INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(305, 'LUXURY2', 11);";

            stmt.execute(sql);

            sql= "INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(306, 'LUXURY3', 12);";

            stmt.execute(sql);

            sql= "INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(307, 'LUXURY4', 12);";

            stmt.execute(sql);

            sql= "INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(308, 'DEFAULT', 12);";

            stmt.execute(sql);

            sql= "INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(309, 'DEFAULT', 12);";

            stmt.execute(sql);

            sql= "INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(310, 'LUXURY4', 12);";

            stmt.execute(sql);

            sql= "INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(311, 'LUXURY3', 11);";
            stmt.execute(sql);

            sql= "INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(312, 'LUXURY2', 12);";
            stmt.execute(sql);

            sql="INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(405, 'LUXURY1', 11);";
            stmt.execute(sql);

            sql="INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(406, 'LUXURY2', 11);";
            stmt.execute(sql);

            sql="INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(407, 'LUXURY1', 11);";
            stmt.execute(sql);

            sql="INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(408, 'LUXURY3', 11);";
            stmt.execute(sql);

            sql="INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(409, 'LUXURY4', 11);";
            stmt.execute(sql);

            sql="INSERT INTO Room (" +
                    "roomNo, Classification, StaffID_SERVICE) " +
                    "VALUES\n" +
                    "(410, 'DEFAULT', 11);";
            stmt.execute(sql);








            stmt = conn.createStatement();
            // Step 3 Create new tables
            sql = "CREATE TABLE Reservation(" +
                    "reId CHAR(20), " +
                    "bookDate CHAR(20), " +
                    "checkInDate CHAR(20), " +
                    "checkOutDate CHAR(20), " +
                    "isCancelled boolean, " +
                    "userId CHAR(20), " +
                    "roomNo INTEGER, " +
                    "ItineraryID CHAR(20), " +
                    "staffID CHAR(20), " +
                    "BillingID CHAR(20), " +
                    "hasPaid boolean, " +
                    "amount INTEGER," +
                    "FOREIGN KEY (userId) REFERENCES User (CustomerID) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (roomNo) REFERENCES Room (roomNo) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (ItineraryID) REFERENCES Itinerary (ItineraryID) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (staffID) REFERENCES Manager (staffId) ON DELETE CASCADE ON UPDATE CASCADE);"

            ;
            stmt.execute(sql);

            //step 4 insert some tuples;


            sql="INSERT INTO Reservation (" +
                    "reId, bookDate, checkInDate, checkOutDate, isCancelled, userId, roomNo, ItineraryID, staffID, BillingID, hasPaid, amount) " +
                    "VALUES\n" +
                    "('10000045', '2018-01-11', '2018-05-10', '2018-05-11', False, '1', 304, '1', '1', '1000789', False, 100);";
            stmt.execute(sql);

            sql= "INSERT INTO Reservation (" +
                    "reId, bookDate, checkInDate, checkOutDate, isCancelled, userId, roomNo, ItineraryID, staffID, BillingID, hasPaid, amount) " +
                    "VALUES\n" +
                    "('10000567', '2018-02-13', '2018-06-18', '2018-06-06', False, '2', 305, '2', '1', '1000889', True, 500);";

            stmt.execute(sql);

            sql= "INSERT INTO Reservation (" +
                    "reId, bookDate, checkInDate, checkOutDate, isCancelled, userId, roomNo, ItineraryID, staffID, BillingID, hasPaid, amount) " +
                    "VALUES\n" +
                    "('10000667', '2018-03-13', '2018-03-06', '2018-05-11', False, '3', 306, '2', '2', '1000989', True, 500);";

            stmt.execute(sql);

            sql= "INSERT INTO Reservation (" +
                    "reId, bookDate, checkInDate, checkOutDate, isCancelled, userId, roomNo, ItineraryID, staffID, BillingID, hasPaid, amount) " +
                    "VALUES\n" +
                    "('10000867', '2018-05-13', '2018-05-06', '2018-07-11', False, '4', 307, '1', '3', '1001089', True, 500);";

            stmt.execute(sql);

            sql= "INSERT INTO Reservation (" +
                    "reId, bookDate, checkInDate, checkOutDate, isCancelled, userId, roomNo, ItineraryID, staffID, BillingID, hasPaid, amount) " +
                    "VALUES\n" +
                    "('10000967', '2018-07-13', '2018-07-06', '2018-09-11', False, '5', 308, '1', '2', '1001189', True, 500);";

            stmt.execute(sql);

            sql= "INSERT INTO Reservation (" +
                    "reId, bookDate, checkInDate, checkOutDate, isCancelled, userId, roomNo, ItineraryID, staffID, BillingID, hasPaid, amount) " +
                    "VALUES\n" +
                    "('10000968', '2018-07-13', '2018-07-06', '2018-09-11', False, '5', 309, '2', '2', '1001189', True, 500);";

            stmt.execute(sql);

            sql= "INSERT INTO Reservation (" +
                    "reId, bookDate, checkInDate, checkOutDate, isCancelled, userId, roomNo, ItineraryID, staffID, BillingID, hasPaid, amount) " +
                    "VALUES\n" +
                    "('10000969', '2018-07-13', '2018-07-06', '2018-09-11', False, '5', 310, '3', '2', '1001189', True, 500);";

            stmt.execute(sql);

            sql= "INSERT INTO Reservation (" +
                    "reId, bookDate, checkInDate, checkOutDate, isCancelled, userId, roomNo, ItineraryID, staffID, BillingID, hasPaid, amount) " +
                    "VALUES\n" +
                    "('10000970', '2018-07-13', '2018-07-06', '2018-09-11', False, '5', 311, '4', '2', '1001189', True, 500);";

            stmt.execute(sql);

            sql= "INSERT INTO Reservation (" +
                    "reId, bookDate, checkInDate, checkOutDate, isCancelled, userId, roomNo, ItineraryID, staffID, BillingID, hasPaid, amount) " +
                    "VALUES\n" +
                    "('10000971', '2018-07-13', '2018-07-06', '2018-09-11', False, '5', 312, '5', '2', '1001189', True, 500);";

            stmt.execute(sql);








//            stmt = conn.createStatement();
//            sql = "DROP TABLE IF EXISTS Itinerary_Requested;";
//            stmt.executeUpdate(sql);
//
//
//            // Step 3 Create new tables
//            sql = "CREATE TABLE Itinerary_Requested(" +
//                    "ItineraryID CHAR(20), " +
//                    "Category CHAR(20), " +
//                    "PartySize INTEGER); "
//            ;
//            stmt.execute(sql);

            //step 4 insert some tuples;










            stmt = conn.createStatement();
            // Step 3 Create new tables
            sql = "CREATE TABLE PointsOfInterest(" +
                    "PID CHAR(20) PRIMARY KEY, Name CHAR(60), Duration INTEGER, Price INTEGER," +
                    "BasePrice INTEGER); "
            ;
            stmt.execute(sql);

            //step 4 insert some tuples;


            sql="INSERT INTO PointsOfInterest (" +
                    "PID, Name, Duration, Price) " +
                    "VALUES\n" +
                    "('1', 'Whistler', 2, 300);";
            stmt.execute(sql);

            sql= "INSERT INTO PointsOfInterest (" +
                    "PID, Name, Duration, Price) " +
                    "VALUES\n" +
                    "('2', 'Okanagan Valley', 2, 200);";

            stmt.execute(sql);

            sql= "INSERT INTO PointsOfInterest (" +
                    "PID, Name, Duration, Price) " +
                    "VALUES\n" +
                    "('3', 'Vancouver Island', 3, 500);";

            stmt.execute(sql);

            sql= "INSERT INTO PointsOfInterest (" +
                    "PID, Name, Duration, Price) " +
                    "VALUES\n" +
                    "('4', 'Butchart Gardens',3, 700);";

            stmt.execute(sql);

            sql= "INSERT INTO PointsOfInterest (" +
                    "PID, Name, Duration, Price) " +
                    "VALUES\n" +
                    "('5', 'Queen Charlotte Islands', 2, 800);";

            stmt.execute(sql);



            stmt = conn.createStatement();
            // Step 3 Create new tables
            sql = "CREATE TABLE Includes (ItineraryID CHAR(20), PID CHAR(20),PRIMARY KEY(ItineraryID, PID), " +
                    "FOREIGN KEY (ItineraryID) REFERENCES Itinerary (ItineraryID) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "FOREIGN KEY (PID) REFERENCES PointsOfInterest (PID) ON DELETE CASCADE ON UPDATE CASCADE);"
            ;
            stmt.execute(sql);

            //step 4 insert some tuples;

            sql="INSERT INTO Includes (" +
                    "ItineraryID, PID) " +
                    "VALUES\n" +
                    "('1', '1');";
            stmt.execute(sql);

            sql= "INSERT INTO Includes (" +
                    "ItineraryID, PID) " +
                    "VALUES\n" +
                    "('2', '2');";

            stmt.execute(sql);

            sql= "INSERT INTO Includes (" +
                    "ItineraryID, PID) " +
                    "VALUES\n" +
                    "('3', '3');";

            stmt.execute(sql);

            sql= "INSERT INTO Includes (" +
                    "ItineraryID, PID) " +
                    "VALUES\n" +
                    "('4', '4');";

            stmt.execute(sql);

            sql= "INSERT INTO Includes (" +
                    "ItineraryID, PID) " +
                    "VALUES\n" +
                    "('5', '5');";

            stmt.execute(sql);







//            sql = "CREATE TABLE items ("
//                    + "item_id VARCHAR(255) NOT NULL,"
//                    + "name VARCHAR(255),"
//                    + "rating FLOAT,"
//                    + "address VARCHAR(255),"
//                    + "image_url VARCHAR(255),"
//                    + "url VARCHAR(255),"
//                    + "distance FLOAT,"
//                    + "PRIMARY KEY (item_id))";
//            stmt.executeUpdate(sql);
//
//            sql = "CREATE TABLE categories ("
//                    + "item_id VARCHAR(255) NOT NULL,"
//                    + "category VARCHAR(255) NOT NULL,"
//                    + "PRIMARY KEY (item_id, category),"
//                    + "FOREIGN KEY (item_id) REFERENCES items(item_id))";
//            stmt.executeUpdate(sql);
//
            sql = "CREATE TABLE users ("
                    + "user_id VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "first_name VARCHAR(255),"
                    + "last_name VARCHAR(255),"
                    + "PRIMARY KEY (user_id))";
            stmt.executeUpdate(sql);
//
//            sql = "CREATE TABLE history ("
//                    + "user_id VARCHAR(255) NOT NULL,"
//                    + "item_id VARCHAR(255) NOT NULL,"
//                    + "last_favor_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
//                    + "PRIMARY KEY (user_id, item_id),"
//                    + "FOREIGN KEY (item_id) REFERENCES items(item_id),"
//                    + "FOREIGN KEY (user_id) REFERENCES users(user_id))";
//            stmt.executeUpdate(sql);


            System.out.println("Import is done successfully.");

            System.out.println("Import is done successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
