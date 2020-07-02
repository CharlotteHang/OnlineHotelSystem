package rpc;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Reservation;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/userViewReservations")
public class UserViewReservations extends HttpServlet {

    public UserViewReservations(){super();};

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String userID = request.getParameter("user_id");
        JSONArray array =  new JSONArray();

        DBConnection conn = DBConnectionFactory.getDBConnection();

        Set<Reservation> Reservations = conn.userGetAllReservations(userID);

        for (Reservation r : Reservations) {
            JSONObject obj = r.toJSONObject();
//            System.out.println("JSON Res Object: Room #" + obj.getString("roomNo") + " as " + obj.getString("roomType")
//                    + " from " + obj.getString("checkInDate") + " to " + obj.getString("checkOutDate") + " at " + obj.getString("roomPrice"));
            array.put(obj);
        }

        RpcHelper.writeJsonArray(response, array);
    }

}
