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
import java.util.Map;
import java.util.Set;


@WebServlet("/managerViewPlan")
public class ManagerViewPlan extends HttpServlet{

    public ManagerViewPlan(){super();}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray array =  new JSONArray();

        DBConnection conn = DBConnectionFactory.getDBConnection();
        Set<Itinerary> iTs = conn.managerGetAllIti();
        Map<String,Integer> numOfUser = conn.managerGetNumOfUserForIt();

        for (Itinerary iT : iTs) {

            List<PointsOfInterests> listOfPIs = conn.managerGetPIs(iT);


            List<Object> piObjs = new ArrayList<>();

            for (PointsOfInterests pi : listOfPIs) {
//                System.out.println(pi.getpName() + " for $" + pi.getpPrice());
//                System.out.println("PI Object: " + pi);
                JSONObject piObj = pi.toJSONObject();
                piObjs.add(piObj);
            }

//            System.out.println("Number of PoIs: "+listOfPIs.size());
//            ArrayList<PointsOfInterests> arrayOfPIs = new ArrayList<>(setOfPIs);
            JSONObject obj = iT.toJSONObject();
            try {
                obj.put("pis", piObjs);
                obj.put("NumOfBookedUser",numOfUser.get(iT.getItineraryID()) == null ? 0 : numOfUser.get(iT.getItineraryID()));
//                obj.put("pis", listOfPIs);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            System.out.println(obj);
            array.put(obj);
        }
        RpcHelper.writeJsonArray(response,array);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String Iid = request.getParameter("plan_id") ;
        String Itype = request.getParameter("plan_category");
        String Isize =request.getParameter("plan_size");
        String pids = request.getParameter("plan_Poi");
        DBConnection conn = DBConnectionFactory.getDBConnection();
        if(Iid != null & Itype !=null & Isize != null & pids !=null){
            conn.managerInsertIti(Iid,Itype,Isize);

        } else return;



        conn.managerInsertCombo(Iid, pids);

        try {
            RpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String Iid =  request.getParameter("plan_id");
        System.out.println(Iid);

        DBConnection conn = DBConnectionFactory.getDBConnection();
        if(Iid != null) {
            conn.managerDeleteIti(Iid);
        }

        try {
            RpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




}
