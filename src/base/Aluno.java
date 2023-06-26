package base;
import java.io.Serializable;
public abstract class Aluno implements Serializable {
    protected Double matricula;
    protected String nome; 
    protected Curso curso;

    public Aluno(Double matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }

    public Aluno() {}

    public Double getMatricula() {
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


