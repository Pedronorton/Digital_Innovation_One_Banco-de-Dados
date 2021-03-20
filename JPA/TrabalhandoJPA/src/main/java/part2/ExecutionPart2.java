package part2;

import classes.Aluno;
import classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ExecutionPart2 {
    public static void main(String[] args) {

        //JPA fornece todas as interfaces para comunicar com o banco, porem ele
        //próprio não faz nada, terceiriza tudo
        // Ele necessita de uma implementação existem o Hibernate e o EclipseLink

        //gerenciador de entidades com o banco especificado no persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Estado estadoParaAdicionar = new Estado("Espirito Santo", "ES");
        Aluno alunoParaAdicionar = new Aluno("Jose", 30, estadoParaAdicionar);
        Aluno alunoParaAdicionar2 = new Aluno("Pedro", 20, estadoParaAdicionar);

        //obrigatório fazer isso em qualquer alteração no banco
        // abrir transaçao
        entityManager.getTransaction().begin();

        //inserindo os dados
        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(alunoParaAdicionar);
        entityManager.persist(alunoParaAdicionar2);
        //obrigatório fazer isso em qualquer consulta no banco fecha transação
        entityManager.getTransaction().commit();

        //Resgatando os dados
        Estado estadoEncontrado = entityManager.find(Estado.class, 1);
        Aluno alunoEncontrado = entityManager.find(Aluno.class, 1);

        System.out.println(estadoEncontrado);
        System.out.println(alunoEncontrado);

        //alterar um aluno
        entityManager.getTransaction().begin();

        Aluno alunoAtualizado = entityManager.find(Aluno.class, 1);
        alunoAtualizado.setIdade(40);
        alunoAtualizado.setNome("Maria");

        entityManager.persist(alunoAtualizado);

        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        entityManager.remove(alunoAtualizado);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();



    }
}
