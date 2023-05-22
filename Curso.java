/*
• Código: 11
• Nome: Ciência da Computação
*/

import java.io.Serializable;
import java.util.*;


public abstract class Curso implements Serializable {
    private String Nome;
    
    private Map<Integer,Aluno> alunos = new HashMap<Integer,Aluno>();


    public Map<Integer, Aluno> getListAlunos() {
        return alunos;
    }

    public void addAluno(Aluno aluno){
        alunos.put(aluno.getMatricula(), aluno);
    }

    public Aluno getAluno(int mat){
        return alunos.get(mat);
    }

    public Curso() {
    }

    public Curso(String nome) {
        this.Nome = nome;
    }

    public String getNome() {
        return Nome;
    }
}

class CursoGrad extends Curso{

    private Map<String,Disciplina> disciplinas= new HashMap<String,Disciplina>();

    public CursoGrad( int codigo,String nome) {
        super(nome);
        this.codigo = codigo;
    }

    private int codigo;

    public int getCodigo() {
        return codigo;
    }
    public void addDisciplina(Disciplina disciplina){
        this.disciplinas.put(disciplina.getCodigo(),disciplina);
    }
    public Disciplina getDisciplina(String codigo){
        return this.disciplinas.get(codigo);
    }
    public Map<String,Disciplina> getListDisciplinas(){
        return disciplinas;
    }
}
class CursoPos extends Curso{
    private Map<String,Disciplina> disciplinas= new HashMap<String,Disciplina>();

    public CursoPos(String nome) {
        super(nome);
    }

    public void addDisciplina(Disciplina disciplina){
        this.disciplinas.put(disciplina.getCodigo(),disciplina);
    }
    public Disciplina getDisciplina(String codigo){
        return this.disciplinas.get(codigo);
    }
    public Map<String,Disciplina> getListDisciplinas(){
        return disciplinas;
    }
}