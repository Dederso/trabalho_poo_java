import java.io.*;
import java.util.*;

abstract class Aluno implements Serializable {
    protected int Matricula;
    protected String Nome; 
    protected Curso curso;

    public Aluno(int matricula, String nome) {
        Matricula = matricula;
        Nome = nome;
    }

    public Aluno() {}

    public int getMatricula() {
        return Matricula;
    }

    public String getNome() {
        return Nome;
    }

    /*
    public void setNome(String nome) {
        Nome = nome;
    }
*/
    public Curso getCurso() {
        return curso;
    }

}

class AlunoGrad extends Aluno{
    
    

    public AlunoGrad(int matricula,String nome,Curso curso) {
        super(matricula,nome);
        super.curso = curso;
    }

/*    
    public void setCurso(Curso curso) {
        super.curso = curso;
    }
*/
    
    
}
class AlunoPos extends Aluno{

    public AlunoPos(int matricula, String nome, Curso curso) {
        super(matricula, nome);
        //os valores de pos só podem ser "1" ou "2"
        super.curso = curso;
    }

    @Override
    public Curso getCurso() {
        return curso;
    }
/*
    public void setCurso(CursoPos curso) {
        this.curso = curso;
    }
*/




}
