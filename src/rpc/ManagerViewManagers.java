package rpc;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Manager;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/managerViewMANAGERs")
public class ManagerViewManagers extends HttpServlet {

    public ManagerViewManagers(){super();};

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        String userId = request.getParameter("user_id");
        JSONArray array =  new JSONArray();

        DBConnection conn = DBConnectionFactory.getDBConnection();

        Set<Manager> managers = conn.managerGetAllManagers();

        for (Manager m : managers) {
            JSONObject obj = m.toJSONObject();
            array.put(obj);

        }

        RpcHelper.writeJsonArray(response,array);
    }

}
