package br.ufg.jatai.ps.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = Aluno.OBTER_ALUNO_POR_EMAIL, query = "select a from Aluno a where a.email = :email")
public class Aluno implements Serializable {

    public static final String OBTER_ALUNO_POR_EMAIL = "obterAlunoPorEmail";

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Aluno() {
        this.id = null;
        this.nome = "";
        this.senha = "";
    }

    public boolean autenticar(String email, String senha) {
        if (email == null || senha == null) {
            return false;
        }
        return (this.email.equals(email)
                && this.senha.equals(senha));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Aluno:" + nome + ", Email: " + email;
    }
    
    
}
