import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private ConnectionFactory(){
        throw new UnsupportedOperationException();
    }

    public static Connection getConnection() throws IOException {
        Connection conn = null;

        try(InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")){
            Properties props = new Properties();
            props.load(input);

            String driver = props.getProperty("jdbc.driver");
            String dataBaseAdress = props.getProperty("db.address");
            String dataBaseName = props.getProperty("db.name");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            StringBuilder sb = new StringBuilder("jdbc:").append(driver).append("://")
                                                        .append(dataBaseAdress).append("/")
                                                        .append(dataBaseName);

            String connectionUrl = sb.toString();
            String urlConnection = "jdbc:mysql://localhost/digital_innovation_one";
            try {
//                conn = DriverManager.getConnection(connectionUrl, user, pass);
                conn = DriverManager.getConnection(urlConnection ,"root", "");

                System.out.println("Conexão feita com sucesso");
            }catch (SQLException e){
                System.out.println("FALHA NA CONEXÃO");
                throw new RuntimeException();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
