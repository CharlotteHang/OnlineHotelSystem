package rpc;

import db.DBConnection;
import db.DBConnectionFactory;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userAddReservation")
public class UserAddReservation extends HttpServlet {

    public UserAddReservation(){super();}



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userID = request.getParameter("user_id") ;
        String roomID = request.getParameter("room_id");
        String checkInDate = request.getParameter("checkin_date");
        String checkOutDate = request.getParameter("checkout_date");

//        System.out.println(checkInDate + " wowoowwow " + checkOutDate);

        DBConnection conn = DBConnectionFactory.getDBConnection();
        if(userID != null & roomID !=null){
            conn.userAddReservation(userID, roomID, checkInDate, checkOutDate);

        } else return;

//        conn.managerInsertCombo(Iid, pids);

        try {
            RpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }






}
