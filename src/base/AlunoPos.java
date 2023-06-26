package base;

public class AlunoPos extends Aluno{

    public AlunoPos(Double matricula, String nome, Curso curso) {
        super(matricula, nome);
        //os valores de pos só podem ser "1" ou "2"
        super.curso = curso;
    }

}
