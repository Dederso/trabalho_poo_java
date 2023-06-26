package base;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;




public class Programa implements Serializable  {
    //lista de mapas com todas as informacoes guardadas

    private Map<String,Disciplina> disciplinas = new HashMap<>();
    private Map<Integer,Curso> cursos = new HashMap<>();  
    private Map<Double,Aluno> alunos = new HashMap<>();
    private Map<String,Avaliacao> avaliacoes = new HashMap<>();
   
    public  final int numMestrado = 800;
    public  final int numDoutorado = 801;

    // lista de funcoes genericas de manipulacao dos mapas
    public Map<Integer,Curso> getCursos() {
        return cursos;
    }

    public Map<Double,Aluno> getAlunos() {
        return alunos;
    }

    public Map<String,Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Map<String,Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
   

    public void registraNota(Aluno aluno, Avaliacao prova, float nota) {
        prova.addAluno(aluno);
        prova.addNota(aluno.getMatricula(),nota);
    }

   

    public void matricular(Aluno aluno, Disciplina disciplina) {
        disciplina.addAluno(aluno);
    }

    public void adicionaAluno(Aluno aluno) {
        alunos.put(aluno.getMatricula(),aluno);
    }

    public void adicionaAvaliacoes(Avaliacao prova) {
        avaliacoes.put(prova.getCodigo(),prova);
    }
   
    public void adicionaCurso(Curso curso) {
        cursos.put(curso.getCodigo(),curso);
    }

    public void adicionaDisciplina(Disciplina disciplina) {
        disciplinas.put(disciplina.getCodigo(),disciplina);
    }


}
