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

@WebServlet("/managerAddPlan")
public class ManagerAddPlan extends HttpServlet {

    public ManagerAddPlan(){super();}



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






}
