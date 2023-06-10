import java.io.Serializable;
import java.util.*;



public abstract class Avaliacao implements Serializable{
    protected Disciplina disciplina;
    protected String Codigo;
    protected String nome;
    protected Date data;
    protected Map<Integer,Aluno> alunosp = new HashMap<Integer,Aluno>();
    protected Map<Integer,Float> notas = new HashMap<Integer,Float>();

    public Avaliacao(Disciplina disciplina, String codigo, String nome, Date data) {
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

    private float peso;
    

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

    public Prova(Disciplina disciplina, String codigo, String nome, float peso, Date data) {
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
/*/
    public void setPeso(float peso) {
        this.peso = peso;
    }
*/
    public Date getData() {
        return super.data;
    }
/*
    public void setData(Date data) {
        super.data = data;
    }
*/

}

class Trabalho extends Avaliacao{

    private int max;
    private float peso;
    
    public Trabalho(Disciplina disciplina, String codigo, String nome, float peso, Date data, int max) {
        super(disciplina, codigo, nome, data);
        this.peso=peso;
        this.max=max;
    }

    public int getMax() {
        return max;
    }
/*
    public void setMax(int max) {
        this.max = max;
    }
*/
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
    /*
    public void setPeso(float peso){
        this.peso=peso;
    }
    */

    public Disciplina getDisciplina() {
        return super.disciplina;
    }

    public String getCodigo() {
        return super.Codigo;
    }


    public String getNome() {
        return super.nome;
    }


    public Date getData() {
        return super.data;
    }
/*
    public void setData(Date data) {
        super.data = data;
    }
*/
}







class ProvaF extends Avaliacao{

    public ProvaF(Disciplina disciplina, String codigo, String nome, Date data) {
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

    public Date getData() {
        return super.data;
    }
/*
    public void setData(Date data) {
        super.data = data;
    }
*/
}