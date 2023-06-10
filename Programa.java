import java.io.*;
import java.util.*;




public class Programa implements Serializable  {
    //lista de mapas com todas as informacoes guardadas

    private Map<String,Disciplina> disciplinas = new HashMap<String,Disciplina>();
    private Map<Integer,Curso> cursos = new HashMap<Integer,Curso>();
    private Map<String,Trabalho> trabalhos = new HashMap<String,Trabalho>();
    private Map<String,ProvaF> provasf = new HashMap<String,ProvaF>();
    private Map<Integer,Aluno> alunos = new HashMap<Integer,Aluno>();
    private Map<String,Prova> provas = new HashMap<String,Prova>();
   
    // lista de funcoes genericas de manipulacao dos mapas
    public Map<Integer,Curso> getCursos() {
        return cursos;
    }

    public Map<Integer,Aluno> getAlunos() {
        return alunos;
    }

    public Map<String,Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Map<String,Prova> getProvas() {
        return provas;
    }
    public Map<String,ProvaF> getProvasF() {
        return provasf;
    }
    public Map<String,Trabalho> getTrabalhos(){
        return trabalhos;
    }

    public void registraNotaP(Aluno aluno, Prova prova, float nota) {
        prova.addAluno(aluno);
        prova.addNota(aluno.getMatricula(),nota);
    }

    public void registraNotaPF(Aluno aluno, ProvaF prova, float nota) {
        prova.addAluno(aluno);
        prova.addNota(aluno.getMatricula(),nota);
    }

    public void registraTrabalho(Aluno aluno, Trabalho trabalho, float nota) {
        trabalho.addAluno(aluno);
        trabalho.addNota(aluno.getMatricula(),nota);
    }


    void matricular(Aluno aluno, Disciplina disciplina) {
        disciplina.addAluno(aluno);
    }

    void adicionaAluno(Aluno aluno) {
        alunos.put(aluno.getMatricula(),aluno);
    }

    void adicionaProva(Prova prova) {
        provas.put(prova.getCodigo(),prova);
    }
    void adicionaProvaF(ProvaF prova) {
        provasf.put(prova.getCodigo(),prova);
    }
    void adicionaTrabalho(Trabalho trabalho){
        trabalhos.put(trabalho.getCodigo(),trabalho);
    }
    void adicionaCurso(Curso curso) {
        cursos.put(curso.getCodigo(),curso);
    }

    void adicionaDisciplina(Disciplina disciplina) {
        disciplinas.put(disciplina.getCodigo(),disciplina);
    }


}
