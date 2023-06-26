package inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import base.Aluno;
import base.Curso;
import base.Disciplina;
import base.Programa;
import base.Trabalho;
import utilitarios.Funcoes;

public class Leitura {


    public void lerCurso(String path,Programa programa,Funcoes funcoes)throws IOException,Exception,NumberFormatException{
        FileReader fl= new FileReader(path);
        BufferedReader bf= new BufferedReader(fl);
        DecimalFormat df= new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        try{ 
            String linha=bf.readLine();
                
                //primeira linha vai ser o cabeçalho
                linha=bf.readLine();
            
                while(linha!=null){

                    String[] vetor = linha.split(";");//pega a linha toda e separa por ";"
                    //cada vetor vira uma string diferente

                    //tira espaços em branco
                    vetor[0]=vetor[0].trim();
                    vetor[1]=vetor[1].trim();

                    int numCurso= Integer.parseInt(vetor[0]);

                    if (programa.getCursos().containsKey(numCurso)) {
                        throw new Exception("Código repetido para curso: " + numCurso + ".");
                    }

                    String nomeCurso = (vetor[1]);
                   
                   
                    funcoes.cadastrarCurso(programa,numCurso,nomeCurso);
                    linha=bf.readLine();
                }
        }
        finally{
            fl.close();
            bf.close();
        }
    
    }   //CONTROLE FEITO



    public void lerDisciplinas(String path, Programa programa, Funcoes funcoes) throws Exception,IOException{
        FileReader fl= new FileReader(path);
        BufferedReader bf= new BufferedReader(fl);
        DecimalFormat df= new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
         try{
            String linha=bf.readLine();
              
                //primeira linha vai ser o cabeçalho
                linha=bf.readLine();
            
                while(linha!=null){
                    String[] vetor = linha.split(";");//pega a linha toda e separa por ";"
                   //tira espaços em branco
                    vetor[0]=vetor[0].trim();
                    vetor[1]=vetor[1].trim();
                    //cada vetor vira uma string diferente
                    String codDis = vetor[0];
                    
                    String nomeDis = vetor[1];
                    

                    if(programa.getDisciplinas().containsKey(codDis)){
                         throw new Exception("Código repetido para disciplina: "+codDis+".");
                    }


                    funcoes.cadastrarDisciplina(programa, codDis, nomeDis);
                    
                    linha=bf.readLine();
                    
                }
            }
            finally{
                fl.close();
                bf.close();
            }
        }
        
    






    public void lerAlunos(String path, Programa programa, Funcoes funcoes) throws Exception, IOException{
        Disciplina disciplina=null;
        Curso curso=null;
        FileReader fl= new FileReader(path);
        BufferedReader bf= new BufferedReader(fl);
        DecimalFormat df= new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        try{
            String linha=bf.readLine();
           
                //primeira linha vai ser o cabeçalho
                linha=bf.readLine();
            
                while(linha!=null){
                   
                    String[] vetor = linha.split(";");//pega a linha toda e separa por ";"
                  
                    //tira espaços em branco
                    for(int i=0;i<vetor.length;i++){
                        vetor[i]=vetor[i].trim();
                    }

                    //cada vetor vira uma string diferente
                    Double codMat = Double.parseDouble(vetor[0]);
                    String nome = vetor[1];
                    

                    String codDis[] = vetor[2].split(",");//separa as disciplinas
                    

                    String tipo = vetor[3];
                    String cursoAluno = vetor[4];
                    
                    //verificando se o curso especificado para o aluno foi defido na planilha de curso  
                    if(tipo.equals("G")){
                        if (!programa.getCursos().containsKey(Integer.parseInt(cursoAluno))) {
                            throw new Exception("Código de curso não definido usado no aluno " + df.format(codMat) + ": " + cursoAluno+".");
                        }
                    }

                    //checa se o tipo do aluno é G ou P
                    if (!tipo.equals("G") && !tipo.equals("P")) {
                        throw new Exception("Tipo de aluno desconhecido para " + df.format(codMat) + ": " + tipo + ".");
                    }
                    
                    //verificando se o aluno de pos possui tipo compativel
                    if(tipo.equals("P")){
                        if(!cursoAluno.equals("M")&&!cursoAluno.equals("D")){
                            throw new Exception("Tipo de aluno de pós-graduação desconhecido para " + df.format(codMat) + ": " + cursoAluno+".");
                        }
                    }
                    //checa se aluno existe
                    if (programa.getAlunos().containsKey(codMat)) {
                        throw new Exception("Matrícula repetida para aluno: " + df.format(codMat) + ".");
                    }
                    //checa se a disciplina é valida
                   

                    funcoes.cadastrarAluno(programa, codMat, cursoAluno, nome, tipo);//cadastra o aluno

                    Aluno aluno = programa.getAlunos().get(codMat);//atribui o aluno a uma variavel

                    int aux=codDis.length;//pega o tamanho vetor que contem as disciplinas
                        while(aux>0){
                            codDis[aux-1]=codDis[aux-1].trim();
                            disciplina=programa.getDisciplinas().get(codDis[aux-1]);//atribui a disciplina a uma variavel

                             if (!programa.getDisciplinas().containsKey(disciplina.getCodigo())) {
                                 throw new Exception("Código de disciplina não definido usado na planilha " + df.format(codMat) + ": " + disciplina.getCodigo() + ".");
                             }

                            curso=aluno.getCurso();//atribui o curso do aluno a uma variavel
                            programa.matricular(aluno,disciplina);//matricula o aluno na disciplina
                            
                            if(!curso.getListDisciplinas().containsKey(disciplina.getCodigo())){//verifica se a disciplina nao esta presente na lista de disciplinas do curso
                                curso.addDisciplina(disciplina);//adiciona a disciplina na lista dentro do curso, caso ja nao esteja la
                            }
                            aux--;
                        }

                    linha=bf.readLine();
                }
        }
        finally{
         fl.close();
         bf.close();
        }
        }
    




    public void lerAvaliacoes(String path,Programa programa,Funcoes funcoes,SimpleDateFormat sdf)throws IOException,Exception,ParseException{
        FileReader fl= new FileReader(path);
        BufferedReader bf= new BufferedReader(fl);
        DecimalFormat df= new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        try{
            String linha=bf.readLine();
                int max=0;
                //primeira linha vai ser o cabeçalho
                linha=bf.readLine();
            
                while(linha!=null){
                    String[] vetor = linha.split(";");//pega a linha toda e separa por ";"
                    
                    //tira espaços em branco
                    for(int i=0;i<vetor.length;i++){
                        vetor[i]=vetor[i].trim();
                    }
                   
                    //cada vetor vira uma string diferente
                    String codDis = vetor[0];

                    String codAva = vetor[1];
                    String nome = vetor[2];
                    int peso = Integer.parseInt(vetor[3]);

                    //verificando se o peso da avaliação é negativo ou zero
                    if (peso<=0) {
                        throw new Exception("Peso de avaliação inválido para "+codAva+": "+peso+".");
                    }


                    //verificando existencia de disciplina na planilha de disciplinas
                    if (!programa.getDisciplinas().containsKey(codDis)) {
                        throw new Exception("Código de disciplina não definido usado na avaliação " + codAva + ": " + codDis + ".");
                    }

                    String tipo= vetor[4];

                    //verificando se o tipo da avaliação é condizente
                    if (!tipo.equals("T")&&!tipo.equals("P")&&!tipo.equals("F")) {
                        throw new Exception("Tipo de avaliação desconhecido para " + codAva + ": " + tipo + ".");
                    }

                    Date dataProva = sdf.parse(vetor[5]);
                    
                    if(tipo.equals("T")){
                        max=Integer.parseInt(vetor[6]);
                        
                        //verificando se o tamanho do grupo é valido
                        if (max<=0) {
                            throw new Exception ("Tamanho máximo de grupo inválido para trabalho "+codAva+": "+max+".");
                        }

                    }

                    if(!tipo.equals("T")){
                        if(vetor.length>6)
                            //confirmando que tamanho max foi setado
                            throw new Exception ("Tamanho máximo de grupo especificado para a prova "+codAva+": "+vetor[6]+".");

                    }

                    funcoes.cadastrarAvaliacao(programa, codDis, nome, codAva, tipo, dataProva, peso, max);

                    linha=bf.readLine();
                }
        } 
        finally{
            fl.close();
            bf.close();
        } 
        for(Map.Entry<String,Disciplina> me:programa.getDisciplinas().entrySet()){
           Disciplina d=me.getValue();
            if(d.getListTrabalho().isEmpty()&&d.getListProva().isEmpty()&&!d.temFinal()){
                throw new Exception("A disciplina "+d.getCodigo()+" não possui nenhuma avaliação cadastrada.");
            }
        }
        
       
    }
    
    public void lerNotas (String path,Programa programa,Funcoes funcoes)throws IOException,Exception{
        DecimalFormat df= new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        FileReader fl= new FileReader(path);
        BufferedReader bf= new BufferedReader(fl);
        try{    
            String linha=bf.readLine();
                //primeira linha vai ser o cabeçalho
                linha=bf.readLine();
            
                while(linha!=null){
                    String[] vetor = linha.split(";");//pega a linha toda e separa por ";"
                    
                    //tira espaços em branco
                    for(int i=0;i<vetor.length;i++){
                        vetor[i]=vetor[i].trim();
                    }
                    //cada vetor vira uma string diferente
                    String codAva = vetor[0];
                    String matriculas= vetor[1];
                    String notaS = vetor[2];
                   
                    Float nota=Float.parseFloat(notaS.replace(",","."));
                   
                    
                    String matDiv[]=matriculas.split(",");//separa as matriculas

                    int aux=matDiv.length;
                    for(int i=0;i<aux;i++){
                        matDiv[i]=matDiv[i].trim();//tira os espaços em branco
                    }

                    if(programa.getAvaliacoes().get(codAva)instanceof Trabalho){
                                Trabalho tra=(Trabalho)programa.getAvaliacoes().get(codAva);
                                tra.addnotasT(nota);
                    }

                    if(!programa.getAvaliacoes().containsKey(codAva)){
                        String string="Código de avaliação não definido usado na planilha de notas, associado ao(s) aluno(s)";

                        while(aux>0){
                        
                            int matAluno = Integer.parseInt(matDiv[aux-1]);
                            string+=" "+matAluno;
                            
                            aux--;
                        }
                        string+=": "+codAva+".";
                        throw new Exception(string);
                    }

                    if(nota>10||nota<0){
                        String string="Nota inválida para avaliação "+codAva+" do(s) aluno(s)";

                        while(aux>0){
                        
                            int matAluno = Integer.parseInt(matDiv[aux-1]);
                            string+=" "+df.format(matAluno);
                            
                            aux--;
                        }
                        string+=": "+String.format("%.2f", nota)+".";
                        throw new Exception(string);
                    }
                    

                    

                    
                    
                    while(aux>0){
                        Double matAluno = Double.parseDouble(matDiv[aux-1]);
                        

                        //excecoes
                        if (!programa.getAlunos().containsKey(matAluno)) {
                            throw new Exception("Matrícula de aluno não definida usada na planilha de notas, associada à avaliação " + codAva + ": " + df.format(matAluno) + ".");
                        }

                        if (!programa.getAvaliacoes().get(codAva).getDisciplina().getListAluno().containsKey(matAluno)) {
                            throw new Exception("O aluno " + df.format(matAluno) + " possui nota na avaliação " + codAva +" da disciplina "+programa.getAvaliacoes().get(codAva).getDisciplina().getCodigo()+", porém não encontra-se matriculado nesta disciplina.");
                        }

                        if(programa.getAvaliacoes().get(codAva).getListAluno().containsKey(matAluno)){
                            throw new Exception("O aluno "+ df.format(matAluno)+" foi registrado em mais de um grupo para a avaliação "+codAva+".");
                        }
                        
                        

                        
                        programa.registraNota(programa.getAlunos().get(matAluno),programa.getAvaliacoes().get(codAva),nota);
                        
                        aux--;
                    }
                    linha=bf.readLine();
                }
        }
        finally{
         fl.close();
         bf.close();
        }
    }
     
}





