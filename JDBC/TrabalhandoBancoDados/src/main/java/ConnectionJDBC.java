import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {

    public static void main (String [] args) throws SQLException {
        String urlConnection = "jdbc:mysql://localhost/digital_innovation_one";

        //CRIAR UMA CONEXÃO
        try (Connection conn = DriverManager.getConnection(urlConnection ,"root", "")){
            System.out.println("Sucesso !");
        }catch (Exception e){
            System.out.println("FALHOU !");
        }

    }
}
