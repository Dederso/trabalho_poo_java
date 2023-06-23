package base;

public class AlunoGrad extends Aluno{
    
    

    public AlunoGrad(int matricula,String nome,Curso curso) {
        super(matricula,nome);
        super.curso = curso;
    }


    
    
}