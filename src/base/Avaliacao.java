package base;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public abstract class Avaliacao implements Serializable{
    protected Disciplina disciplina;
    protected String codigo;
    protected String nome;
    protected Date data;
    protected Map<Integer,Aluno> alunosp = new HashMap<>();
    protected Map<Integer,Float> notas = new HashMap<>();

    public Avaliacao(Disciplina disciplina, String codigo, String nome, Date data) {
        this.disciplina = disciplina;
        this.codigo = codigo;
        this.nome = nome;
        this.data = data;
    }

    public Avaliacao(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public String getCodigo() {
        return codigo;
    }


    public String getNome() {
        return nome;
    }

    public Date getData() {
        return data;
    }
    
    public Aluno getAluno(int i) {
        Aluno aluno = alunosp.get(i);
        return aluno;
    }

    public Map<Integer,Aluno> getListAluno() {
        return alunosp;
    }

    public Float getNota(int i) {
        float nota = notas.get(i);
        return nota;
    }


    public Map<Integer,Float> getListNotas() {
        return notas;
    }

    public void addAluno(Aluno aluno) {
        alunosp.put(aluno.getMatricula(),aluno);
    }

    public void addNota(int matricula,float nota) {
        notas.put(matricula,nota);
    }


    public float mediaAvaliativa(){
       int i=0;
       float media=0;
       if(!this.getListNotas().isEmpty()){
            for(Map.Entry<Integer,Float> me: notas.entrySet()){
                    media+=me.getValue();
                    i++;
                }
                media/=i;
        }
        else{
            media=0;
        }
        return  media;
    }
}


 







