package base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public abstract class Curso implements Serializable {
    protected String nome;
    
    protected int codigo;

    protected Map<Double,Aluno> alunos = new HashMap<>();

    protected Map<String,Disciplina> disciplinas= new HashMap<>();
    protected Map<String,Float> medias= new HashMap<>();
    protected Map<String,Float> passou= new HashMap<>();
    
    
    public int getCodigo() {
        return codigo;
    }

    public Map<Double, Aluno> getListAlunos() {
        return alunos;
    }

    public Map<String,Disciplina> getListDisciplinas(){
        return disciplinas;
    }

    public void addAluno(Aluno aluno){
        alunos.put(aluno.getMatricula(), aluno);
    }

    public Aluno getAluno(Double mat){
        return alunos.get(mat);
    }

    public Curso() {
    }

    public Curso(String nome) {
        this.nome = nome;
    }
    public Curso(int codigo, String nome){
        this.nome=nome;
        this.codigo=codigo;
    }

    public String getNome() {
        return nome;
    }

    public void addDisciplina(Disciplina disciplina){
        this.disciplinas.put(disciplina.getCodigo(),disciplina);
    }
    public Disciplina getDisciplina(String codigo){
        return this.disciplinas.get(codigo);
    }

    public void addMedia(String codDis,Float nota){
        this.medias.put(codDis, nota);
    }
    public void addPassou(String codDis,Float passou){
        this.passou.put(codDis, passou);
    }

    public Map<String,Float> getListMedias(){
        return this.medias;
    }
    public Map<String,Float> getListPassou(){
        return this.passou;
    } 

    public float getMedia(String codDis){
        return this.medias.get(codDis);
    }
    public float getPassou(String codDis){
        return this.passou.get(codDis);
    }


    public void calculaMediasCurso(String codDis){
        Aluno aluno=null;
        float total,media,div,passou;
        Disciplina disciplina = getDisciplina(codDis);
        total=media=div=passou=0;
        for (Map.Entry<Double, Aluno> me : getListAlunos().entrySet()) {// pega os alunos da disciplina

            aluno = me.getValue();// atribui a uma variavel
            // PROCURANDO O ALUNO UM A UM DENTRO DO CURSO
            if (disciplina.getListAluno().containsKey(aluno.getMatricula())) {// verifica se o aluno faz parte do curso
                total++;// total de alunos do curso na disciplina

                media += disciplina.getNotaf(aluno.getMatricula());// pega a media do aluno
                div++;// auxiliar de divisao
                if (disciplina.getNotaf(aluno.getMatricula()) >= 5.0) {// verifica quem teve uma nota final maior que 5
                    passou++;// total de alunos que passaram
                }
            }
        }
        media /= div;// dividindo a soma de medias e transformando em media de medias
        passou = (passou * 100) / total;
        this.addMedia(disciplina.getCodigo(),media);
        this.addPassou(disciplina.getCodigo(), passou);
    }
}


