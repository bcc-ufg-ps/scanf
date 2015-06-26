package br.ufg.jatai.ps.modelo;

import br.ufg.jatai.ps.util.Mensagens;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javafx.scene.paint.Color;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@NamedQuery(name = Disciplina.OBTER_DISCIPLINAS_POR_ALUNO, query = "select d from Disciplina d where d.aluno.id = :idAluno order by d.nome asc")
public class Disciplina implements Serializable, Observado {

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
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private String professor;
    @Column(nullable = false)
    private int faltasPermitidas;
    private int faltasOcorridas;
    @Column(nullable = false)
    private double notaMinimaParaAprovacao;
    @Column(nullable = false)
    private double pontosObtidos;
    @Column(nullable = false)
    private double pontosDistribuidos;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimaAtualizacao;
    @ManyToOne
    private Aluno aluno;
    @Transient
    private List<String> mensagensAlerta;
    @Transient
    private List<String> mensagensApoio;
    @Transient
    private List<Observador> observadores;

    public Disciplina() {
        this.id = null;
        this.nome = "";
        this.professor = "";
        this.faltasPermitidas = FALTAS_PERMITIDAS_DEFAULT;
        this.notaMinimaParaAprovacao = NOTA_MINIMA_DEFAULT;
        this.faltasOcorridas = 0;
        this.pontosDistribuidos = 0.0;
        this.pontosObtidos = 0.0;
        this.dataUltimaAtualizacao = Calendar.getInstance().getTime();
        this.aluno = new Aluno();
        this.mensagensAlerta = new ArrayList<String>();
        this.mensagensApoio = new ArrayList<String>();
        this.observadores = new ArrayList<Observador>();
    }

    @Override
    public void adicionarObservador(Observador o) {
        this.observadores.add(o);
    }

    @Override
    public void notificar() {
        for (Observador o : observadores) {
            o.atualizar(this);
        }
    }

    public void adicionarMensagemAlerta(String msg) {
        this.mensagensAlerta.add(msg);
    }

    public void adicionarMensagemApoio(String msg) {
        this.mensagensApoio.add(msg);
    }

    public double getPorcentagemFaltasOcorridas() {
        if (faltasPermitidas == 0) {
            return 0;
        }
        if (faltasOcorridas > faltasPermitidas) {
            return 100;
        }
        return (((double) faltasOcorridas / (double) faltasPermitidas) * 100);
    }

    public double getPorcentagemPontosNecessarios() {
        if (NOTA_MAXIMA - pontosDistribuidos == 0) {
            return ((pontosObtidos / NOTA_MAXIMA) * 100);
        }
        if ((notaMinimaParaAprovacao - pontosObtidos) > (NOTA_MAXIMA - pontosDistribuidos)) {
            return 100;
        }
        return (((notaMinimaParaAprovacao - pontosObtidos) / (NOTA_MAXIMA - pontosDistribuidos)) * 100);
    }

    private Situacao getSituacao(double valor) {
        if (valor <= 50) {
            return Situacao.NORMAL;
        }
        if (valor <= 75) {
            return Situacao.ALERTA;
        }
        return Situacao.GRAVE;
    }

    public Situacao getSituacaoNotaAluno() {
        double ppn = getPorcentagemPontosNecessarios();
        return getSituacao(ppn);
    }

    public Situacao getSituacaoFrequenciaAluno() {
        double pfo = getPorcentagemFaltasOcorridas();
        return getSituacao(pfo);
    }

    public boolean getSituacaoNotaGrave() {
        return Situacao.GRAVE == getSituacao(getPorcentagemPontosNecessarios());
    }

    public boolean getSituacaoNotaNormal() {
        return Situacao.NORMAL == getSituacao(getPorcentagemPontosNecessarios());
    }

    public boolean getSituacaoNotaAlerta() {
        return Situacao.ALERTA == getSituacao(getPorcentagemPontosNecessarios());
    }

    public boolean getSituacaoFrequenciaGrave() {
        return Situacao.GRAVE == getSituacao(getPorcentagemFaltasOcorridas());
    }

    public boolean getSituacaoFrequenciaNormal() {
        return Situacao.NORMAL == getSituacao(getPorcentagemFaltasOcorridas());
    }

    public boolean getSituacaoFrequenciaAlerta() {
        return Situacao.ALERTA == getSituacao(getPorcentagemFaltasOcorridas());
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

    public int getFaltasPermitidas() {
        return faltasPermitidas;
    }

    public void setFaltasPermitidas(int nFaltasPermitidas) {
        this.faltasPermitidas = nFaltasPermitidas;
    }

    public int getFaltasOcorridas() {
        return faltasOcorridas;
    }

    public void setFaltasOcorridas(int nFaltasOcorridas) {
        this.faltasOcorridas = nFaltasOcorridas;
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

    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<String> getMensagensAlerta() {
        return mensagensAlerta;
    }

    public void setMensagensAlerta(List<String> mensagensAlerta) {
        this.mensagensAlerta = mensagensAlerta;
    }

    public List<String> getMensagensApoio() {
        return mensagensApoio;
    }

    public void setMensagensApoio(List<String> mensagensApoio) {
        this.mensagensApoio = mensagensApoio;
    }

    public List<Observador> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<Observador> observadores) {
        this.observadores = observadores;
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
