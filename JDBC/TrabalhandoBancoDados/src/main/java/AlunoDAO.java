import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//é aqui que é acessado o banco
public class AlunoDAO {

    public List<Aluno> list() throws IOException {
        List<Aluno> alunos = new ArrayList<Aluno>();

        try (Connection conn =  ConnectionFactory.getConnection()){

            PreparedStatement prst = conn.prepareStatement("SELECT * FROM aluno");
            ResultSet rs = prst.executeQuery();
            //cursor está antes do registro, por isso precisamos dar um nex
//            rs.next();

            while(rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("estado"));
                alunos.add(aluno);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("aqui");
        }
        return alunos;
    }

    public Aluno getById(int id) throws IOException {
       Aluno aluno = new Aluno();

        try (Connection conn =  ConnectionFactory.getConnection()){

            String sql = "SELECT * FROM aluno WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            //cursor está antes do registro, por isso precisamos dar um nex
//            rs.next();
            if(rs.next()){
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
            }else{
                System.out.println("Objeto não encontrado");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return aluno;
    }

    public void create(Aluno aluno){
        try (Connection conn =  ConnectionFactory.getConnection()){

            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES (?, ?, ?)" ;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());
            int rowsAfected = stmt.executeUpdate();

            //cursor está antes do registro, por isso precisamos dar um nex
//            rs.next();
            System.out.println("INSERÇÃO BEM SUCEDIDA");

        }catch (SQLException | IOException e){
            System.out.println(e.getMessage());
            System.out.println("FALHA NA INSERÇÃO");
        }

    }

    public void deleteById(int id){
        try (Connection conn =  ConnectionFactory.getConnection()){

            String sql = "DELETE FROM aluno WHERE id = ?" ;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsAfected = stmt.executeUpdate();

            //cursor está antes do registro, por isso precisamos dar um nex
//            rs.next();
            System.out.println("DELETE BEM SUCEDIDO "+ rowsAfected+" na linha");

        }catch (SQLException | IOException e){
            System.out.println(e.getMessage());
            System.out.println("FALHA NO DELETE");
        }

    }

    public void update(Aluno newAluno){
        try (Connection conn =  ConnectionFactory.getConnection()){

            String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?" ;
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, newAluno.getNome());
            stmt.setInt(2, newAluno.getIdade());
            stmt.setString(3, newAluno.getEstado());
            stmt.setInt(4, newAluno.getId());
            int rowsAfected = stmt.executeUpdate();

            //cursor está antes do registro, por isso precisamos dar um nex
//            rs.next();
            System.out.println("UPDATE BEM SUCEDIDO "+ rowsAfected+" na linha");

        }catch (SQLException | IOException e){
            System.out.println(e.getMessage());
            System.out.println("FALHA NO UPDATE");
        }

    }

}
