package configs;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import models.VWDistritoProvinciaDepartamento;
import org.javalite.activejdbc.Base;

public class Test {
  public static void main(String[] args) {
    Database db = new Database();
    String rpta = "";
    Base.open("org.sqlite.JDBC", "jdbc:sqlite:/home/pepe/Documentos/java/ajax/db/ubicaciones.db", "", "");
    try {
      List<JSONObject> rptaTemp = new ArrayList<JSONObject>();
      
      List<VWDistritoProvinciaDepartamento> rptaList = VWDistritoProvinciaDepartamento.where("nombre LIKE 'L%'").limit(10);
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
      System.out.println(rpta);
      Base.close();
    }
  }  
}
