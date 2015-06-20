package br.ufg.jatai.ps.modelo;

import java.io.Serializable;
import javax.persistence.Column;
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
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;

    public Aluno() {
        this.id = null;
        this.nome = "";
        this.senha = "";
    }

    public boolean autenticar(Aluno a) {
        String aEmail = a.getEmail();
        String aSenha = a.getSenha();
        if (aEmail == null || aSenha == null) {
            return false;
        }
        return (this.email.equals(aEmail)
                && this.senha.equals(aSenha));
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
