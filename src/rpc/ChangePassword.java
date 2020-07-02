package rpc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.DBConnection;
import db.DBConnectionFactory;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBConnection conn = DBConnectionFactory.getDBConnection();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = request.getParameter("user_id");
            String newPwd = request.getParameter("old_password");
            String oldPwd = request.getParameter("new_password");
            if (conn.changePassword(userId, newPwd, oldPwd)) {
                RpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"));
            } else {
                response.setStatus(401);
            }
            System.out.println(5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
