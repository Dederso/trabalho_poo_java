//import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

/*
• Disciplina: INF15933
• Código: ProgOO-P1
• Nome: Prova Parcial 1
• Peso: 1.0
• Data: 25/04/2023
*/

public abstract class Avaliacao implements Serializable{
    Disciplina disciplina;
    String Codigo;
    String nome;
    String data;
    Map<Integer,Aluno> alunosp = new HashMap<Integer,Aluno>();
    Map<Integer,Float> notas = new HashMap<Integer,Float>();

    public Avaliacao(Disciplina disciplina, String codigo, String nome, String data) {
        this.disciplina = disciplina;
        Codigo = codigo;
        this.nome = nome;
        this.data = data;
    }

    public Avaliacao(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}


 class Prova extends Avaliacao {

    float peso;
    

    public Aluno getAluno(int i) {
        Aluno aluno = alunosp.get(i);
        return aluno;
    }

    public Map<Integer,Aluno> getListAluno() {
        return alunosp;
    }

    public Float getNota(int i) {
        float nota = notas.get(i);
        return nota;
    }


    public Map<Integer,Float> getListNotas() {
        return notas;
    }

    public void addAluno(Aluno aluno) {
        alunosp.put(aluno.getMatricula(),aluno);
    }

    public void addNota(int matricula,float nota) {
        notas.put(matricula,nota);
    }

    public Prova(Disciplina disciplina, String codigo, String nome, float peso, String data) {
        super(disciplina, codigo, nome, data);
        this.peso = peso;
     
    }

    public Disciplina getDisciplina() {
        return super.disciplina;
    }

    public String getCodigo() {
        return super.Codigo;
    }


    public String getNome() {
        return super.nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getData() {
        return super.data;
    }

    public void setData(String data) {
        super.data = data;
    }


}

class Trabalho extends Avaliacao{

    int max;
    float peso;
    
    public Trabalho(Disciplina disciplina, String codigo, String nome, float peso, String data, int max) {
        super(disciplina, codigo, nome, data);
        this.peso=peso;
        this.max=max;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Aluno getAluno(int i) {
        Aluno aluno = alunosp.get(i);
        return aluno;
    }

    public Map<Integer,Aluno> getListAluno() {
        return alunosp;
    }

    public Float getNota(int i) {
        float nota = notas.get(i);
        return nota;
    }


    public Map<Integer,Float> getListNotas() {
        return notas;
    }

    public void addAluno(Aluno aluno) {
        this.alunosp.put(aluno.getMatricula(),aluno);
    }

    public void addNota(int matricula,float nota) {
        this.notas.put(matricula,nota);
    }

    public float getPeso(){
        return this.peso;
    }
    public void setPeso(float peso){
        this.peso=peso;
    }
    public Disciplina getDisciplina() {
        return super.disciplina;
    }

    public String getCodigo() {
        return super.Codigo;
    }


    public String getNome() {
        return super.nome;
    }


    public String getData() {
        return super.data;
    }

    public void setData(String data) {
        super.data = data;
    }
}







class ProvaF extends Avaliacao{

    public ProvaF(Disciplina disciplina, String codigo, String nome, String data) {
        super(disciplina, codigo, nome, data);
    }

    public Aluno getAluno(int i) {
        Aluno aluno = alunosp.get(i);
        return aluno;
    }

    public Map<Integer,Aluno> getListAluno() {
        return alunosp;
    }

    public Float getNota(int i) {
        float nota = notas.get(i);
        return nota;
    }


    public Map<Integer,Float> getListNotas() {
        return notas;
    }

    public void addAluno(Aluno aluno) {
        alunosp.put(aluno.getMatricula(),aluno);
    }

    public void addNota(int matricula,float nota) {
        notas.put(matricula,nota);
    }
    public Disciplina getDisciplina() {
        return super.disciplina;
    }

    public String getCodigo() {
        return super.Codigo;
    }


    public String getNome() {
        return super.nome;
    }

    public String getData() {
        return super.data;
    }

    public void setData(String data) {
        super.data = data;
    }

}