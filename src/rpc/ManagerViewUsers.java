package rpc;
import db.DBConnection;
import db.DBConnectionFactory;
import entity.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/managerViewUsers")
public class ManagerViewUsers extends HttpServlet {

    public ManagerViewUsers(){super();};

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        String userId = request.getParameter("user_id");
        JSONArray array =  new JSONArray();

        DBConnection conn = DBConnectionFactory.getDBConnection();

        Set<User> Users = conn.managerGetAllUsers();
        Set<String> UsersBookAll = conn.managerGetUsersBookAll();


        for (User user : Users) {
            JSONObject obj = user.toJSONObject();
            String s;
            if (UsersBookAll.contains(user.getUserId())) {
                s = "true";
            } else {
                s = "false";
            }
            try {
                obj.put("hasBookAllPlans", s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            array.put(obj);

        }

        RpcHelper.writeJsonArray(response,array);
    }

}
