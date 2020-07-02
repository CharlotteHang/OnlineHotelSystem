package rpc;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Room;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/userViewROOMs")
public class UserViewRooms extends HttpServlet {

    public UserViewRooms(){super();};

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        String userId = request.getParameter("user_id");
        JSONArray array =  new JSONArray();

        DBConnection conn = DBConnectionFactory.getDBConnection();

        Set<Room> Rooms = conn.userGetAllRooms();

        for (Room r : Rooms) {
            JSONObject obj = r.toJSONObject();
            array.put(obj);

        }

        RpcHelper.writeJsonArray(response,array);
    }

}
