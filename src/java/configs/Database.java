package configs;

import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.DBException;

public class Database{
  private DB db;
	
  public Database(){
    this.db = new DB();
  }

  public DB getDb(){
    return this.db; 
  }

  public void open() throws DBException{
    String driver = "org.sqlite.JDBC";
    String url = "jdbc:sqlite:/home/pepe/Documentos/java/ajax/db/ubicaciones.db";
    String user = "";
    String password = "";
	  this.db.open(driver, url, user, password);
  }

  public void close(){
    this.db.close();
  }
}