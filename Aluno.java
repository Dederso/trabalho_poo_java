/* 
• Matrícula: 2000000005
• Nome: Arya Stark
• Curso: 11
*/

import java.io.*;
import java.util.*;

abstract class Aluno implements Serializable {
    private int Matricula;
    private String Nome; 

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

    public void setNome(String nome) {
        Nome = nome;
    }

    // public boolean verificaA(int matricula) {
    //     if (this.Matricula == matricula) {
    //         return true;
    //     } else
    //         return false;
    // }

}

class AlunoGrad extends Aluno{
    private Curso curso;
    

    public AlunoGrad(int matricula,String nome,Curso curso) {
        super(matricula,nome);
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    
    
}
class AlunoPos extends Aluno{
    CursoPos curso;

    public AlunoPos(int matricula, String nome, CursoPos curso) {
        super(matricula, nome);
        //os valores de pos só podem ser "1" ou "2"
        this.curso = curso;
    }

    public CursoPos getCurso() {
        return curso;
    }

    public void setCurso(CursoPos curso) {
        this.curso = curso;
    }





}
