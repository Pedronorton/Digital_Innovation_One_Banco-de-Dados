import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.List;

public class QueriesExcecution {

    public static void main(String[] args) throws IOException {
        AlunoDAO alunoDAO = new AlunoDAO();

        //consulta
//        List<Aluno> alunos = alunoDAO.list();
//        alunos.stream().forEach(System.out::println);

//        Aluno aluno = alunoDAO.getById(1);
//        System.out.println(aluno);

//        Aluno aluno = new Aluno("Tiemi", 23, "MG");
//        alunoDAO.create(aluno);

//        alunoDAO.deleteById(1);


        Aluno alunoParaAtualizar = alunoDAO.getById(3);
        alunoParaAtualizar.setNome("tESTANDO");
        alunoParaAtualizar.setIdade(100);
        alunoParaAtualizar.setEstado("SP");
        alunoDAO.update(alunoParaAtualizar);
    }
}
