//import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

/*
 Codigo: INF15933
 Nome: Programacao Orientada a Objetos
*/

public class Disciplina implements Serializable {
    private String Codigo;
    private String Nome;
    private ProvaF provaF;

    private Map<Integer,Aluno> alunosd = new HashMap<Integer,Aluno>();
    private Map<String,Prova> provas= new HashMap<String,Prova>();
    //private Map<String,ProvaF> provasF= new HashMap<String,ProvaF>();
    private Map<String,Trabalho> trabalhos= new HashMap<String,Trabalho>();
    private Map<Integer,Float> notasf = new HashMap<Integer,Float>();

    public void addAluno(Aluno aluno) {
        this.alunosd.put(aluno.getMatricula(),aluno);
    }

    public Aluno getAluno(int i) {
        Aluno aluno = alunosd.get(i);
        return aluno;
    }

    public Map<Integer,Aluno> getListAluno() {
        return alunosd;
    }

    public void addProva(Prova prova){
        this.provas.put(prova.getCodigo(),prova);
    }
    
    public Prova getProva(String codigo) {
        Prova prova = provas.get(codigo);
        return prova;
    }

    public Map<String,Prova> getListProva() {
        return provas;
    }

    //nao utilizado
    /* 
    public void addProvaF(ProvaF prova){
        this.provasF.put(Codigo, prova);
    }

    public ProvaF getProvaF(String codigo){
        return provasF.get(codigo);
    }

    public Map<String,ProvaF> getListProvaF() {
        return provasF;
    }
    
    */
    public ProvaF getProvaF() {
        return provaF;
    }

    public void setProvaF(ProvaF provaF) {
        this.provaF = provaF;
    }

    public void addTrabalho(Trabalho trabalho){
        this.trabalhos.put(Codigo, trabalho);
    }

    public Trabalho getTrabalho(String codigo){
        return trabalhos.get(codigo);
    }

    public Map<String,Trabalho> getListTrabalho() {
        return trabalhos;
    }



    public Disciplina(String codigo, String nome) {
        Codigo = codigo;
        Nome = nome;
    }

    public String getCodigo() {
        return Codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void addNotaf(int mat,Float nota){
        notasf.put(mat,nota);
    }

    public float getNotaf(int mat){
        return notasf.get(mat);
    }

    public Map<Integer,Float> getListNotaf(){
        return notasf;
    }
    
    public void calculaNotaF() {
        float media=0,div=0,fim=0;
        for(Map.Entry<Integer,Aluno> meA: alunosd.entrySet()){
            Aluno aluno= meA.getValue();
            for(Map.Entry<String,Prova> meP: provas.entrySet()){
                Prova prova = meP.getValue();
                if(prova.getListAluno().containsKey(aluno.getMatricula())){
                    media+=(prova.getNota(aluno.getMatricula())*prova.getPeso());
                    div+=prova.getPeso();
                }
                else{
                    div+=prova.getPeso();
                }
            }
            for(Map.Entry<String,Trabalho> meT: trabalhos.entrySet()){
                Trabalho trabalho = meT.getValue();
                if(trabalho.getListAluno().containsKey(aluno.getMatricula())){
                    media+=(trabalho.getNota(aluno.getMatricula())*trabalho.getPeso());
                    div+=trabalho.getPeso();
                }
                else{
                    div+=trabalho.getPeso();
                }
            }
            fim=media/div;
            if(fim<7.0){
                if(provaF.getListAluno().containsKey(aluno.getMatricula())){
                    fim+=provaF.getNota(aluno.getMatricula());
                    fim/=2;
                }
                else{
                  fim/=2;
                  this.addNotaf(aluno.getMatricula(),fim);  
                }
            }
            else{
                this.addNotaf(aluno.getMatricula(),fim);
            }
        }
    }


}
