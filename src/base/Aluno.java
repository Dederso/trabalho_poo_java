package base;
import java.io.Serializable;
public abstract class Aluno implements Serializable {
    protected int matricula;
    protected String nome; 
    protected Curso curso;

    public Aluno(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public Aluno() {}

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
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


