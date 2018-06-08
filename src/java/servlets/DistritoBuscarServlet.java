package servlets;

import configs.Database;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.VWDistritoProvinciaDepartamento;
import org.json.JSONObject;

public class DistritoBuscarServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    String rpta = "";
    Database db = new Database();
    try {
      List<JSONObject> rptaTemp = new ArrayList<JSONObject>();
      db.open();
      String query = "nombre LIKE '" + request.getParameter("nombre")+ "%'";
      List<VWDistritoProvinciaDepartamento> rptaList = VWDistritoProvinciaDepartamento.where(query).limit(10);
      for (VWDistritoProvinciaDepartamento distrito : rptaList) {
        JSONObject obj = new JSONObject();
        obj.put("id", distrito.get("id"));
        obj.put("nombre", distrito.get("nombre"));
        rptaTemp.add(obj);
      }
      rpta = rptaTemp.toString();
    }catch (Exception e) {
      String[] error = {"Se ha producido un error en  listar la búsqueda", e.toString()};
      JSONObject rptaTry = new JSONObject();
      rptaTry.put("tipo_mensaje", "error");
      rptaTry.put("mensaje", error);
      rpta = rptaTry.toString();
    } finally {
      response.setContentType("text/html;charset=UTF-8");
      response.getWriter().write(rpta);
      db.close();
    }
  }
}
