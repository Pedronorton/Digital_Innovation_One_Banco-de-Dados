package part3;

import classes.Aluno;
import classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExecutionPart3 {
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

        String nome = "Pedro";
        //utilizando sql nativo-----------------------------------
        String sql = "SELECT * FROM aluno";

        List<Aluno> listaAlunos =  entityManager
                .createNativeQuery(sql, Aluno.class)
                .getResultList();

//        listaAlunos.stream().forEach(aluno -> System.out.println("Aluno: "+aluno));

        //==============================================================================================================================
        // UTILIZANDO JPQL

        String jpql = "select a from Aluno a where a.nome = :nome";
        Aluno alunoJpql = entityManager
                .createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();
//        System.out.println(alunoJpql);

//        String jpqlList = "select a from Aluno a";
//        List<Aluno> listAlunoJpql = entityManager
//                .createQuery(jpqlList, Aluno.class)
//                .getResultList();
//
//        listAlunoJpql.stream().forEach(aluno -> System.out.println("Aluno: "+aluno));
        String estadoNome = "Espirito Santo";

        String jpqlList = "select a from Aluno a WHERE a.estado.nome = :estadoNome";
        List<Aluno> listAlunoJpql = entityManager
                .createQuery(jpqlList, Aluno.class)
                .setParameter("estadoNome", estadoNome)
                .getResultList();

        listAlunoJpql.stream().forEach(aluno -> System.out.println("Aluno: "+aluno));

        //JPA Criteria API + JPA Metamodel
//        CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        //Falo qual tipo de dado irá retornar
        CriteriaQuery<Aluno> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        Root<Aluno> rootAluno = criteriaQuery.from(Aluno.class);
//        CriteriaBuilder.In<String> inClause = entityManager.getCriteriaBuilder().in(rootAluno.get(Aluno_));
    }
}
