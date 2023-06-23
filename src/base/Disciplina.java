package base;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Disciplina implements Serializable {
    private String codigo;
    private String nome;
    private ProvaF provaF;

    private Map<Integer,Aluno> alunosd = new HashMap<>();
    private Map<String,Prova> provas= new HashMap<>();
    private Map<String,Trabalho> trabalhos= new HashMap<>();
    private Map<Integer,Float> notasf = new HashMap<>();

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

   
    public ProvaF getProvaF() {
        return provaF;
    }

    public void setProvaF(ProvaF provaF) {
        this.provaF = provaF;
    }

    public void addTrabalho(Trabalho trabalho){
        this.trabalhos.put(trabalho.getCodigo(), trabalho);
    }

    public Trabalho getTrabalho(String codigo){
        return trabalhos.get(codigo);
    }

    public Map<String,Trabalho> getListTrabalho() {
        return trabalhos;
    }



    public Disciplina(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
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

    public boolean temFinal(){
        if(this.provaF==null){
            return false;
        }
        else
        return true;
    }


    public void calculaNotaF() {
        float media=0,div=0,fim=0;
        for(Map.Entry<Integer,Aluno> meA: alunosd.entrySet()){//pega cada aluno da lista, presente nessa disciplina
            media=0;div=0;fim=0;//zera as variaveis para que nao interfira com a nota de outro aluno
            Aluno aluno= meA.getValue();//atribui o aluno a uma variavel de objeto aluno
            for(Map.Entry<String,Prova> meP: provas.entrySet()){//pega cada prova da lista, presente nessa disciplina
                Prova prova = meP.getValue();//atribui
                if(prova.getListAluno().containsKey(aluno.getMatricula())){//verifica se o aluno fez a prova
                    media+=(prova.getNota(aluno.getMatricula())*prova.getPeso());//adiciona a nota do aluno
                    div+=prova.getPeso();//adiciona o peso da prova para divisão
                }
                else{
                    div+=prova.getPeso();//adiciona o peso da prova para a divisão mesmo o aluno não participando da prova, sua nota será igual a 0
                }
            }
            for(Map.Entry<String,Trabalho> meT: trabalhos.entrySet()){//pega cada trabalho da lista, presente nessa disciplina
                Trabalho trabalho = meT.getValue();//atribui
                if(trabalho.getListAluno().containsKey(aluno.getMatricula())){//verifica se o aluno fez o trabalho
                    media+=(trabalho.getNota(aluno.getMatricula())*trabalho.getPeso());//adiciona a nota do aluno na media
                    div+=trabalho.getPeso();//adiciona o peso da trabalho para a divisão 
                }
                else{
                    div+=trabalho.getPeso();//adiciona o peso da trabalho para a divisão mesmo o aluno não participando da prova, sua nota será igual a 0
                }
            }
            fim=media/div; //media parcial
            if(fim<7.0){//verifica se passou direto
                if(provaF.getListAluno().containsKey(aluno.getMatricula())){//verifica se fez a prova final
                    fim+=provaF.getNota(aluno.getMatricula());//adiciona a nota do aluno
                    fim/=2;//divide a soma das notas por 2
                    this.addNotaf(aluno.getMatricula(),fim);//adiciona a nota do aluno
                }
                else{
                  fim/=2;//caso nao tenha feito a prova final, é considerado como 0
                  this.addNotaf(aluno.getMatricula(),fim);// adiciona a nota do aluno
                }
            }
            else{
                this.addNotaf(aluno.getMatricula(),fim);//adiciona a nota, sem necessidade de prova final
            }
        }
    }


}
