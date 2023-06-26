package br.com.ffcs.javacleanarchitecture.infra.aluno;

import br.com.ffcs.javacleanarchitecture.dominio.aluno.Aluno;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.AlunoNaoEncontradoException;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.CPF;
import br.com.ffcs.javacleanarchitecture.dominio.aluno.RepositorioDeAlunos;
import jakarta.persistence.*;

import java.util.List;

public class RepositorioDeAlunosComJPA implements RepositorioDeAlunos {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public RepositorioDeAlunosComJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("escolaPU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void matricular(Aluno aluno) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(aluno);

        transaction.commit();
    }

    @Override
    public Aluno buscarPorCPF(CPF cpf) throws AlunoNaoEncontradoException {
        Query query = entityManager.createQuery("SELECT a FROM AlunoEntity a WHERE a.cpf = :cpf");
        query.setParameter("cpf", cpf);

        List<Aluno> result = query.getResultList();

        if (result.isEmpty()) {
            throw new AlunoNaoEncontradoException(cpf);
        }

        return result.get(0);
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        Query query = entityManager.createQuery("SELECT a FROM AlunoEntity a");

        return query.getResultList();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
