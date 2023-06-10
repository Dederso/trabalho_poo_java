
import java.io.Serializable;
import java.util.*;


public abstract class Curso implements Serializable {
    protected String Nome;
    
    protected int codigo;

    protected Map<Integer,Aluno> alunos = new HashMap<Integer,Aluno>();

    protected Map<String,Disciplina> disciplinas= new HashMap<String,Disciplina>();


    public int getCodigo() {
        return codigo;
    }

    public Map<Integer, Aluno> getListAlunos() {
        return alunos;
    }

    public Map<String,Disciplina> getListDisciplinas(){
        return disciplinas;
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
    public Curso(int codigo, String nome){
        this.Nome=nome;
        this.codigo=codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void addDisciplina(Disciplina disciplina){
        this.disciplinas.put(disciplina.getCodigo(),disciplina);
    }
    public Disciplina getDisciplina(String codigo){
        return this.disciplinas.get(codigo);
    }
}

class CursoGrad extends Curso{

    
    public CursoGrad( int codigo,String nome) {
        super(codigo,nome);
    
    }

   

}
class CursoPos extends Curso{

    public CursoPos(int codigo,String nome) {
        super(codigo,nome);
    }

  
}