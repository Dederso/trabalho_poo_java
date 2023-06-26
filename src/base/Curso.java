package base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public abstract class Curso implements Serializable {
    protected String nome;
    
    protected int codigo;

    protected Map<Double,Aluno> alunos = new HashMap<>();

    protected Map<String,Disciplina> disciplinas= new HashMap<>();

    
    public int getCodigo() {
        return codigo;
    }

    public Map<Double, Aluno> getListAlunos() {
        return alunos;
    }

    public Map<String,Disciplina> getListDisciplinas(){
        return disciplinas;
    }

    public void addAluno(Aluno aluno){
        alunos.put(aluno.getMatricula(), aluno);
    }

    public Aluno getAluno(Double mat){
        return alunos.get(mat);
    }

    public Curso() {
    }

    public Curso(String nome) {
        this.nome = nome;
    }
    public Curso(int codigo, String nome){
        this.nome=nome;
        this.codigo=codigo;
    }

    public String getNome() {
        return nome;
    }

    public void addDisciplina(Disciplina disciplina){
        this.disciplinas.put(disciplina.getCodigo(),disciplina);
    }
    public Disciplina getDisciplina(String codigo){
        return this.disciplinas.get(codigo);
    }
}


