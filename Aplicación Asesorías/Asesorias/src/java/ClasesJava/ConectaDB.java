package ClasesJava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author itzee
 */
public class ConectaDB {
    public static Connection con;
    private static final String BD="asesorias";
    public static String usuario="root";
    public static String passw="";
    public static String url="jdbc:mysql://localhost:3306/"+BD;
    
    public static Connection obtenConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url,usuario,passw);
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Error en la conexion...");
        }
        return con;
    }
    
    public static void cerrarConexion(){
        try{
            if(con!=null){
                con.close();
            }
        } catch(SQLException e){
            System.out.println("Error: no se logro cerrar la conexion:\n"+e);
        }
    }
}
