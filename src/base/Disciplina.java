package base;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Disciplina implements Serializable {
    private String codigo;
    private String nome;
    private ProvaF provaF;

    private Map<Double,Aluno> alunosd = new HashMap<>();
    private Map<String,Avaliacao> avaliacoes= new HashMap<>();
    private Map<Double,Float> notasf = new HashMap<>();
    private Map<Double,Float> notasp = new HashMap<>();

    public void addAluno(Aluno aluno) {
        this.alunosd.put(aluno.getMatricula(),aluno);
    }

    public Aluno getAluno(Double i) {
        Aluno aluno = alunosd.get(i);
        return aluno;
    }

    public Map<Double,Aluno> getListAluno() {
        return alunosd;
    }

    public void addAvaliacao(Avaliacao prova){
        this.avaliacoes.put(prova.getCodigo(),prova);
    }
    
    public Avaliacao getAvaliacao(String codigo) {
        Avaliacao ava = avaliacoes.get(codigo);
        return ava;
    }

    public Map<String,Avaliacao> getListAvaliacoes() {
        return avaliacoes;
    }

   
    public ProvaF getProvaF() {
        return provaF;
    }

    public void setProvaF(ProvaF provaF) {
        this.provaF = provaF;
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

    public void addNotaf(Double mat,Float nota){
        notasf.put(mat,nota);
    }

    public float getNotaf(Double mat){
        return notasf.get(mat);
    }

    public Map<Double,Float> getListNotaf(){
        return notasf;
    }

    public boolean temFinal(){
        if(this.provaF==null){
            return false;
        }
        else
        return true;
    }
    public void addNotaP(Double mat,float nota){
        notasp.put(mat,nota);
    }
    public Map<Double,Float> getListNotap(){
        return notasp;
    }

    public float getNotap(Double mat){
        return notasp.get(mat);
    }

   
    public void calculaNotaF() {
        float media=0,div=0,fim=0;
        for(Map.Entry<Double,Aluno> meA: alunosd.entrySet()){//pega cada aluno da lista, presente nessa disciplina
            media=0;div=0;fim=0;//zera as variaveis para que nao interfira com a nota de outro aluno
            Aluno aluno= meA.getValue();//atribui o aluno a uma variavel de objeto aluno
            for(Map.Entry<String,Avaliacao> meP: avaliacoes.entrySet()){//pega cada prova da lista, presente nessa disciplina
                Avaliacao prova = meP.getValue();//atribui
                if(prova.getListAluno().containsKey(aluno.getMatricula())){//verifica se o aluno fez a prova
                    media+=(prova.getNota(aluno.getMatricula())*prova.getPeso());//adiciona a nota do aluno
                    div+=prova.getPeso();//adiciona o peso da prova para divisão
                }
                else{
                    div+=prova.getPeso();//adiciona o peso da prova para a divisão mesmo o aluno não participando da prova, sua nota será igual a 0
                }
            }
            fim=media/div; //media parcial
            this.addNotaP(aluno.getMatricula(),fim);
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
