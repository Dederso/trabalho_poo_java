package base;

public class AlunoGrad extends Aluno{
    
    

    public AlunoGrad(Double matricula,String nome,Curso curso) {
        super(matricula,nome);
        super.curso = curso;
    }


    
    
}