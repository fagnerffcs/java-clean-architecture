package br.com.ffcs.javacleanarchitecture.infra.aluno;

import br.com.ffcs.javacleanarchitecture.dominio.aluno.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeAlunosComJDBC implements RepositorioDeAlunos {

    private final Connection connection;

    public RepositorioDeAlunosComJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void matricular(Aluno aluno) {
        try {
            String sql = "INSERT INTO ALUNO VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, aluno.getCpf().getNumero());
            ps.setString(2, aluno.getNome());
            ps.setString(3, aluno.getEmail().getEndereco());
            ps.execute();

            String sqlTelefones = "INSERT INTO TELEFONE VALUES (?, ?)";
            ps = connection.prepareStatement(sqlTelefones);
            for (Telefone t: aluno.getTelefones()) {
                ps.setString(1, t.getDdd());
                ps.setString(2, t.getNumero());
                ps.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Aluno buscarPorCPF(CPF cpf) throws AlunoNaoEncontradoException {
        Aluno alunoRecuperado = null;
        try {
            String sql = "SELECT id, nome, email FROM ALUNO WHERE cpf = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpf.getNumero());
            ResultSet rs = ps.executeQuery();
            boolean encontrou = rs.next();
            if(!encontrou){
                throw new AlunoNaoEncontradoException(cpf);
            }
            String nome = rs.getString("nome");
            Email email = Email.builder().endereco(rs.getString("email")).build();
            alunoRecuperado = Aluno.builder()
                                   .nome(nome)
                                   .cpf(cpf)
                                   .email(email)
                                   .build();
            recuperarTelefonesAluno(alunoRecuperado, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alunoRecuperado;
    }

    private void recuperarTelefonesAluno(Aluno alunoRecuperado, ResultSet rs) throws SQLException {
        PreparedStatement ps;
        Long id = rs.getLong("id");
        String sqlTelefones = "SELECT ddd, numero FROM TELEFONE WHERE aluno_id = ?";
        ps = connection.prepareStatement(sqlTelefones);
        ps.setLong(1, id);
        rs = ps.executeQuery();
        while (rs.next()){
            String numero = rs.getString("numero");
            String ddd = rs.getString("ddd");
            alunoRecuperado.adicionarTelefone(ddd, numero);
        }
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT id, cpf, nome FROM ALUNO";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                CPF cpf = CPF.builder().numero(rs.getString("cpf")).build();
                String nome = rs.getString("nome");
                Email email = Email.builder().endereco(rs.getString("email")).build();
                Aluno recuperado = Aluno.builder()
                                        .nome(nome)
                                        .cpf(cpf)
                                        .email(email)
                                        .build();
                recuperarTelefonesAluno(recuperado, rs);
                alunos.add(recuperado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alunos;
    }
}