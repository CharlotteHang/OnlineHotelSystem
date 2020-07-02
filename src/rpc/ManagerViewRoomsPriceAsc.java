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
import java.util.List;

@WebServlet("/managerViewRoomsPriceAsc")
public class ManagerViewRoomsPriceAsc extends HttpServlet {

    public ManagerViewRoomsPriceAsc(){super();};

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        String userId = request.getParameter("user_id");
        JSONArray array =  new JSONArray();

        DBConnection conn = DBConnectionFactory.getDBConnection();

        List<Room> Rooms = conn.managerGetRoomsPriceAsc();

        for (Room r : Rooms) {
            System.out.println("Room #" + r.getRoomId() + " as " + r.getRoomType() + " at " + r.getPrice());
            JSONObject obj = r.toJSONObject();
            array.put(obj);

        }

        RpcHelper.writeJsonArray(response,array);
    }

}
