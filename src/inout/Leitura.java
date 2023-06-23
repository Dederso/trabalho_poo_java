package inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import base.Aluno;
import base.Curso;
import base.Disciplina;
import base.Programa;
import base.Trabalho;
import utilitarios.Funcoes;

public class Leitura {


    public void lerCurso(String path,Programa programa,Funcoes funcoes){
        try(BufferedReader bf= new BufferedReader(new FileReader(path))){
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
                    String nomeCurso = (vetor[1]);
                   
                   
                    funcoes.cadastrarCurso(programa,numCurso,nomeCurso);
                    linha=bf.readLine();
                }

        }
        catch(IOException e){
            System.out.println("Erro na abertura de arquivo");
        }
        catch (Exception NumberFormatException) {
            System.out.println("Erro de formatação.");
        }
    }   //CONTROLE FEITO



    public void lerDisciplinas(String path, Programa programa, Funcoes funcoes){

        try(BufferedReader bf= new BufferedReader(new FileReader(path))){
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

                    //checar se a disciplina existe

                    String nomeDis = vetor[1];
                   
                    funcoes.cadastrarDisciplina(programa, codDis, nomeDis);
                    linha=bf.readLine();
                }

        }
        catch(IOException e){
                System.out.println("Erro na abertura de arquivo");
        }
        catch (Exception NumberFormatException) {
            System.out.println("Erro de formatação.");
        }
    }






    public void lerAlunos(String path, Programa programa, Funcoes funcoes){
       Disciplina disciplina=null;
       Curso curso=null;
        try(BufferedReader bf= new BufferedReader(new FileReader(path))){
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
                    int codMat = Integer.parseInt(vetor[0]);
                    String nome = vetor[1];
                    

                    String codDis[] = vetor[2].split(",");//separa as disciplinas
                    

                    String tipo = vetor[3];
                    String cursoAluno = vetor[4];


                    funcoes.cadastrarAluno(programa, codMat, cursoAluno, nome, tipo);//cadastra o aluno

                    Aluno aluno = programa.getAlunos().get(codMat);//atribui o aluno a uma variavel

                    int aux=codDis.length;//pega o tamanho vetor que contem as disciplinas
                        while(aux>0){
                            codDis[aux-1]=codDis[aux-1].trim();
                            disciplina=programa.getDisciplinas().get(codDis[aux-1]);//atribui a disciplina a uma variavel
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
        catch(IOException e){
                System.out.println("Erro na abertura de arquivo");
        }
    }




    public void lerAvaliacoes(String path,Programa programa,Funcoes funcoes,SimpleDateFormat sdf){
        try(BufferedReader bf= new BufferedReader(new FileReader(path))){
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

                    String tipo= vetor[4];

                   
                    Date dataProva = sdf.parse(vetor[5]);
                    
                    if(tipo.equals("T")){
                        max=Integer.parseInt(vetor[6]);
                    }

                    funcoes.cadastrarAvaliacao(programa, codDis, nome, codAva, tipo, dataProva, peso, max);

                    linha=bf.readLine();
                }

        }
        catch(IOException e){
          System.out.println("Erro na abertura de arquivo");
        }
        catch (ParseException e) {
          e.printStackTrace();
        }
    }
    
    public void lerNotas (String path,Programa programa,Funcoes funcoes){
        try(BufferedReader bf= new BufferedReader(new FileReader(path))){
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
                    while(aux>0){
                        
                        int matAluno = Integer.parseInt(matDiv[aux-1]);
                        programa.registraNota(programa.getAlunos().get(matAluno),programa.getAvaliacoes().get(codAva),nota);
                        
                        aux--;
                    }
                    linha=bf.readLine();
                }

        }
        catch(IOException e){
          System.out.println("Erro na abertura de arquivo");System.out.println(e.getMessage());
        }
        
        


    }
    
}





