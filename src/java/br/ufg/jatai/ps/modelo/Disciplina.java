package br.ufg.jatai.ps.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({            
    @NamedQuery(name = Disciplina.OBTER_DISCIPLINAS_POR_ALUNO, query = "select d from Disciplina d where d.aluno.id = :idAluno order by d.dataCadastramento desc"),
    @NamedQuery(name = Disciplina.OBTER_DISCIPLINAS_POR_NOME_OU_PROF, query = "select d from Disciplina d where d.aluno.id = :idAluno and (d.nome like :texto or d.professor like :texto) order by d.dataCadastramento desc")
})
public class Disciplina implements Serializable {

    public static enum Situacao {

        NORMAL, ALERTA, GRAVE
    }
    public static final double NOTA_MAXIMA = 10.0;
    public static final int FALTAS_PERMITIDAS_DEFAULT = 8;
    public static final double NOTA_MINIMA_DEFAULT = 6.0;
    public static final String OBTER_DISCIPLINAS_POR_ALUNO = "obterDisciplinasPorAluno";
    public static final String OBTER_DISCIPLINAS_POR_NOME_OU_PROF = "obterDisciplinasPorNomeOuProf";

    // Campos persistentes
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String professor;
    private int nFaltasPermitidas;
    private int nFaltasOcorridas;
    private double notaMinimaParaAprovacao;
    private double pontosObtidos;
    private double pontosDistribuidos;
    @Temporal(TemporalType.DATE)
    private Date dataCadastramento;
    @ManyToOne
    private Aluno aluno;

    public Disciplina() {
        this.id = null;
        this.nome = "";
        this.professor = "";
        this.nFaltasPermitidas = FALTAS_PERMITIDAS_DEFAULT;
        this.notaMinimaParaAprovacao = NOTA_MINIMA_DEFAULT;
        this.nFaltasOcorridas = 0;
        this.pontosDistribuidos = 0.0;
        this.pontosObtidos = 0.0;
        this.dataCadastramento = Calendar.getInstance().getTime();
        this.aluno = new Aluno();
    }

    public double getPorcentagemFaltasOcorridas() {
        if (nFaltasPermitidas == 0) {
            return 0;
        }
        if (nFaltasOcorridas > nFaltasPermitidas) {
            return 100;
        }
        return (((double) nFaltasOcorridas / (double) nFaltasPermitidas) * 100);
    }

    public double getPorcentagemPontosNecessarios() {
        if (NOTA_MAXIMA - pontosDistribuidos == 0) {
            return ((pontosObtidos / 10) * 100);
        }
        if (notaMinimaParaAprovacao - pontosObtidos > NOTA_MAXIMA - pontosObtidos) {
            return 100;
        }
        return (((notaMinimaParaAprovacao - pontosObtidos) / (NOTA_MAXIMA - pontosDistribuidos)) * 100);
    }

    private Situacao getSituacao(double valor) {
        if (valor <= 0.5) {
            return Situacao.NORMAL;
        }
        if (valor <= 0.75) {
            return Situacao.ALERTA;
        }
        return Situacao.GRAVE;
    }

    public Situacao getSituacaoFrequenciaAluno() {
        // pfo = porcentagem de faltas ocorridas.
        double pfo = getPorcentagemFaltasOcorridas();
        return getSituacao(pfo);
    }

    public Situacao getSituacaoNotaAluno() {
        // ppn = porcentagem de pontos necessários.
        double ppn = getPorcentagemPontosNecessarios();
        return getSituacao(ppn);
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getnFaltasPermitidas() {
        return nFaltasPermitidas;
    }

    public void setnFaltasPermitidas(int nFaltasPermitidas) {
        this.nFaltasPermitidas = nFaltasPermitidas;
    }

    public int getnFaltasOcorridas() {
        return nFaltasOcorridas;
    }

    public void setnFaltasOcorridas(int nFaltasOcorridas) {
        this.nFaltasOcorridas = nFaltasOcorridas;
    }

    public double getNotaMinimaParaAprovacao() {
        return notaMinimaParaAprovacao;
    }

    public void setNotaMinimaParaAprovacao(double notaMinimaParaAprovacao) {
        this.notaMinimaParaAprovacao = notaMinimaParaAprovacao;
    }

    public double getPontosObtidos() {
        return pontosObtidos;
    }

    public void setPontosObtidos(double pontosObtidos) {
        this.pontosObtidos = pontosObtidos;
    }

    public double getPontosDistribuidos() {
        return pontosDistribuidos;
    }

    public void setPontosDistribuidos(double pontosDistribuidos) {
        this.pontosDistribuidos = pontosDistribuidos;
    }

    public Date getDataCadastramento() {
        return dataCadastramento;
    }

    public void setDataCadastramento(Date dataCadastramento) {
        this.dataCadastramento = dataCadastramento;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Disciplina: ").append(this.nome).append("\n")
                .append("Professor: ").append(this.professor).append("\n")
                .append("% Faltas ocorridas: ").append(this.getPorcentagemFaltasOcorridas()).append("\n")
                .append("% Pontos necessários: ").append(this.getPorcentagemPontosNecessarios()).append("\n");
        return str.toString();
    }

}
