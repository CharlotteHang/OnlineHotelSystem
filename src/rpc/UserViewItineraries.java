package rpc;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Itinerary;
import entity.PointsOfInterests;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@WebServlet("/userViewItineraries")
public class UserViewItineraries extends HttpServlet{

    public UserViewItineraries(){super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray array =  new JSONArray();

        DBConnection conn = DBConnectionFactory.getDBConnection();
        Set<Itinerary> iTs = conn.managerGetAllIti();

        for (Itinerary iT : iTs) {

            List<PointsOfInterests> listOfPIs = conn.managerGetPIs(iT);

            List<Object> piObjs = new ArrayList<>();

            for (PointsOfInterests pi : listOfPIs) {
                JSONObject piObj = pi.toJSONObject();
                piObjs.add(piObj);
            }

            JSONObject obj = iT.toJSONObject();
            try {
                obj.put("pis", piObjs);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(obj);
        }
        RpcHelper.writeJsonArray(response,array);
    }

}
