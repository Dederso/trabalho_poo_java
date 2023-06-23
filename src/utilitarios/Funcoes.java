package utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import base.Aluno;
import base.AlunoGrad;
import base.AlunoPos;
import base.Avaliacao;
import base.Curso;
import base.CursoGrad;
import base.Disciplina;
import base.Programa;
import base.Prova;
import base.ProvaF;
import base.Trabalho;


public class Funcoes {
    



    public void cadastrarCurso(Programa programa,int numCurso,String nomeCurso){
        // Cadastrar curso
        
   
        try{
            

            CursoGrad curso = new CursoGrad(numCurso, nomeCurso);
            programa.adicionaCurso(curso);//adiciona o curso no mapa geral de cursos
        }
       
        catch(NumberFormatException e){
         System.out.println("Erro de formatação.");
            return;
        }
    }




















    public void cadastrarDisciplina(Programa programa,String codDis,String nomeDis){
        //cadastrar disciplina
                

                Disciplina disciplina = new Disciplina(codDis, nomeDis);//cria um objeto Disciplina e atribuia a uma variavel
                programa.adicionaDisciplina(disciplina);//adiciona a disciplina no mapa geral de disciplinas
    }










    public void cadastrarAvaliacao(Programa programa,String codDis,String nome, String codAva,String tipo,Date data,int peso,int max){
        // Cadastrar prova
        
            //Cadastrar Prova
            if (tipo.equals("P")) {
                
                    //sequencia de entradas do usuario e onde sao armazenadas

                    Disciplina disciplina = programa.getDisciplinas().get(codDis);//atribui a um objeto para melhor leitura

                    Prova prova = new Prova(disciplina, codAva, nome, peso, data);//controi um novo objeto prova
                    
                    programa.adicionaAvaliacoes(prova);//adiciona a prova no mapa geral de provas
                    disciplina.addProva(prova);// adiciona a prova na lista de provas dentro da disciplina
                
                
                
        }

        //Cadastrar Prova Final
        else if (tipo.equals("F")) {
            //mesmo procedimento para cadastrar provas, em caso de duvida, veja acima
            

                Disciplina disciplina = programa.getDisciplinas().get(codDis);
                ProvaF provaf = new ProvaF(disciplina, codAva, nome, data);
                
                programa.adicionaAvaliacoes(provaf);
                disciplina.setProvaF(provaf);
            
           
        }

        //Cadastrar Trabalho
        else if (tipo.equals("T")) {
            
            try{
                Disciplina disciplina = programa.getDisciplinas().get  (codDis);
                Trabalho trabalho = new Trabalho(disciplina, codAva, nome, peso, data, max);
                programa.adicionaAvaliacoes(trabalho);
                disciplina.addTrabalho(trabalho);
            }
            catch(NumberFormatException e){
                System.out.println("Erro de formatação.");
            }
           
        }  
    }












    public void cadastrarAluno(Programa programa,int codMat,String cursoAluno,String nome,String tipo){
        // Cadastrar aluno(a)
        Curso curso;
        
        if(tipo.equals("G")){
            int intCurso=Integer.parseInt(cursoAluno);
            
            
            curso=programa.getCursos().get(intCurso);

            Aluno aluno= new AlunoGrad(codMat,nome,curso);
            
            programa.adicionaAluno(aluno);
            curso.addAluno(aluno);
        }

        else if(tipo.equals("P")){
            if(cursoAluno.equals("D")){


                    curso=programa.getCursos().get(programa.numDoutorado);
                    Aluno aluno= new AlunoPos(codMat,nome,curso);
                    
                    programa.adicionaAluno(aluno);
                    curso.addAluno(aluno);
            }
            else if(cursoAluno.equals("M")){

                    curso=programa.getCursos().get(programa.numMestrado);
                    Aluno aluno= new AlunoPos(codMat,nome,curso);
                
                    programa.adicionaAluno(aluno);
                    curso.addAluno(aluno);
            }
        }
                   
                       
    }
















    //inutilizado pela automatizacao
    public void matricularAlunoDisciplina(Programa programa, Scanner sc){
        // Matricular aluno(a) em disciplina

        System.out.println("Aluno de Graduação ou Pós?");
        System.out.println("1- Graduação");
        System.out.println("2- Pos");
        int aux = Integer.parseInt(sc.nextLine());
        Curso curso;
        while(aux!=1&&aux!=2){
            System.out.println("Valor digitado invalido, você digitou: "+aux+"\n"+"digite:1 para graduação ou digite:2 pós-graduação");
            aux = Integer.parseInt(sc.nextLine());
        }
        if(aux==1){
            System.out.println("Matricula do Aluno");

            int matAluno = Integer.parseInt(sc.nextLine());
            
            Aluno aluno = programa.getAlunos().get(matAluno);


            curso=((AlunoGrad)aluno).getCurso();

            System.out.println("Disciplina a ser matriculado");
            String codDis = sc.nextLine();
            Disciplina disciplina = programa.getDisciplinas().get(codDis);
            if(!((CursoGrad)curso).getListDisciplinas().containsKey(disciplina.getCodigo())){
                ((CursoGrad)curso).addDisciplina(disciplina);
                System.out.println("grad");
            }
            programa.matricular(aluno, disciplina);
        }
        if(aux==2){
            aux=0;
            System.out.println("Mestrado ou Doutorado");
            System.out.println("1- mestrado");
            System.out.println("2- doutorado");
            aux = Integer.parseInt(sc.nextLine());
            while(aux!=1&&aux!=2){
                System.out.println("Valor digitado invalido, você digitou: "+aux+"\n"+"digite:1 para Mestrado ou digite:2 Doutorado");
                aux = Integer.parseInt(sc.nextLine());
            }
            if(aux==1){
                System.out.println("Matrícula do Aluno");

                int matAluno = Integer.parseInt(sc.nextLine());
                
                Aluno aluno = programa.getAlunos().get(matAluno);
    
                System.out.println("Disciplina a ser matriculado");
                String codDis = sc.nextLine();


                Disciplina disciplina = programa.getDisciplinas().get(codDis);

                curso=programa.getCursos().get(1);

                if(curso.getListDisciplinas().containsKey(disciplina.getCodigo())){
                }
                else{
                    curso.addDisciplina(disciplina);
                }
                programa.matricular(aluno, disciplina);
            }
            if(aux==2){
                System.out.println("Matricula do Aluno");

                int matAluno = Integer.parseInt(sc.nextLine());
                
                Aluno aluno = programa.getAlunos().get(matAluno);
    
                System.out.println("Disciplina a ser matriculado");
                String codDis = sc.nextLine();

                curso=programa.getCursos().get(2);

                Disciplina disciplina = programa.getDisciplinas().get(codDis);
                if(curso.getListDisciplinas().containsKey(disciplina.getCodigo())){
                    
                }
                else{
                    curso.addDisciplina(disciplina);
                }
                
                programa.matricular(aluno, disciplina);
            }
        }
    }




















/*
    public void registraNotaAluno(Programa programa, Scanner sc){
        try{ 
            System.out.println("Qual nota deseja registrar?");
            System.out.println("1- Prova");
            System.out.println("2- Trabalho");
            System.out.println("3- Prova Final");
            int aux=Integer.parseInt(sc.nextLine());
            int aux1=0;
            if(aux!=1 && aux!=2 && aux!=3){
                while(aux!=1 && aux!=2 && aux!=3){
                    System.out.println("Opção inválida, você digitou: "+aux);
                    System.out.println("Qual nota deseja registrar?");
                    System.out.println("1- Prova");
                    System.out.println("2- Trabalho");
                    System.out.println("3- Prova Final");
                    aux=Integer.parseInt(sc.nextLine());
                }
            }
            if(aux==1){
                // Registrar nota de aluno em prova

                System.out.println("Codigo da prova");
                String codProva = sc.nextLine();


                System.out.println("Matricula");
                int matAluno = Integer.parseInt(sc.nextLine());


                System.out.println("Nota da prova");
                float notaProva = Float.parseFloat(sc.nextLine());

                if(!programa.getAlunos().containsKey(matAluno)){// verifica se o aluno esta na lista de alunos cadastrados
                    aux1=1;//auxiliar que ajuda no loop
                    while(aux1==1){
                        System.out.println("Matrícula de aluno não definida usada na planilha de notas, associada à avaliação "+codProva+": "+matAluno);
                        System.out.println("Tente novamente.");
                        matAluno = Integer.parseInt(sc.nextLine());//entrada do usuario de uma matricula nova
                        if(programa.getAlunos().containsKey(matAluno)){//verifica matricula valida
                            aux1=0;//zera o auxiliar, encerrando o loop
                        }
                    }

                }
                if(!programa.getProvas().containsKey(codProva)){
                   aux1=1;
                   while(aux1==1){
                        System.out.println("Código de avaliação não definido usado na planilha de notas, associado ao(s) aluno(s) "+matAluno+": "+codProva+".");
                        System.out.println("Tente novamente. ");
                        codProva=sc.nextLine();
                        if(programa.getProvas().containsKey(codProva)){
                            aux1=0;
                        }
                    }
                }

                Aluno aluno = programa.getAlunos().get(matAluno);
                Prova prova = programa.getProvas().get(codProva);

                programa.registraNotaP(aluno, prova, notaProva);
            }
            if(aux==2){
                //Registrar nota de alunos em trabalho
                System.out.println("Codigo do Trabalho");
                String codTrabalho = sc.nextLine();
                if(!programa.getTrabalhos().containsKey(codTrabalho)){
                    aux1=1;
                    while(aux1==1){
                         System.out.println("Código de avaliação não definido usado na planilha de notas: "+codTrabalho+".");
                         System.out.println("Tente novamente. ");
                         codTrabalho=sc.nextLine();
                         if(programa.getTrabalhos().containsKey(codTrabalho)){
                             aux1=0;
                         }
                     }
                }
                Trabalho trabalho = programa.getTrabalhos().get(codTrabalho);

                System.out.println("Quantidade de alunos");
                int quantidade=Integer.parseInt(sc.nextLine());
                //Impede o registro de alunos além do maximo permitido por trabalho
                if(trabalho.getMax()<quantidade){
                    while(trabalho.getMax()<quantidade){
                        System.out.println("Entrada inválida, a quantidade máxima de alunos nesse trabalho é: "+trabalho.getMax()+"\nVocê digitou: "+quantidade);
                        System.out.println("Digite a quantidade de alunos");
                        quantidade=Integer.parseInt(sc.nextLine());
                    }
                }
                //pega a nota do trabalho
                System.out.println("Nota do trabalho");
                float nota=Float.parseFloat(sc.nextLine());


                Aluno aluno;
                int alunomat;
                //loop que registra a nota de cada aluno individualmente
                for (int j=0; j<quantidade;j++) {
                    System.out.println("matricula do aluno "+(j+1));
                    alunomat=Integer.parseInt(sc.nextLine());

                    if(!programa.getAlunos().containsKey(alunomat)){// verifica se o aluno esta na lista de alunos cadastrados
                        aux1=1;//auxiliar que ajuda no loop
                        while(aux1==1){
                            System.out.println("Matrícula de aluno não definida usada na planilha de notas, associada à avaliação "+codTrabalho+": "+alunomat);
                            System.out.println("Tente novamente.");
                            alunomat = Integer.parseInt(sc.nextLine());//entrada do usuario de uma matricula nova
                            if(programa.getAlunos().containsKey(alunomat)){//verifica matricula valida
                                aux1=0;//zera o auxiliar, encerrando o loop
                            }
                        }

                    }
                    aluno = programa.getAlunos().get(alunomat);

                    programa.registraTrabalho(aluno, trabalho, nota);
                }

            }
            if(aux==3){
                //registra nota de aluno em prova final
                System.out.println("Codigo da prova final");
                String codProva = sc.nextLine();

                System.out.println("Matricula");
                int matAluno = Integer.parseInt(sc.nextLine());


                System.out.println("Nota da prova");
                float notaProva = Float.parseFloat(sc.nextLine());

                if(!programa.getAlunos().containsKey(matAluno)){// verifica se o aluno esta na lista de alunos cadastrados
                    aux1=1;//auxiliar que ajuda no loop
                    while(aux1==1){
                        System.out.println("Matrícula de aluno não definida usada na planilha de notas, associada à avaliação "+codProva+": "+matAluno);
                        System.out.println("Tente novamente.");
                        matAluno = Integer.parseInt(sc.nextLine());//entrada do usuario de uma matricula nova
                        if(programa.getAlunos().containsKey(matAluno)){//verifica matricula valida
                            aux1=0;//zera o auxiliar, encerrando o loop
                        }
                    }

                }
                if(!programa.getProvas().containsKey(codProva)){
                   aux1=1;
                   while(aux1==1){
                        System.out.println("Código de avaliação não definido usado na planilha de notas, associado ao(s) aluno(s) "+matAluno+": "+codProva+".");
                        System.out.println("Tente novamente. ");
                        codProva=sc.nextLine();
                        if(programa.getProvas().containsKey(codProva)){
                            aux1=0;
                        }
                    }
                }
                Aluno aluno = programa.getAlunos().get(matAluno);
                ProvaF prova = programa.getProvasF().get(codProva);

                programa.registraNotaPF(aluno, prova, notaProva);
            }
        }
        catch(NumberFormatException e){
            System.out.println("Erro de formatação.");
        }
    }


*/













    public void imprimirDados(Programa programa){
        System.out.println("Disciplinas e alunos matriculados:");
                // FOR PARA LISTA DAS DISCIPLINAS

                //me= disciplina da "lista" de disciplinas, vai rodar cada disciplina por vez
                for (Map.Entry<String, Disciplina> me : programa.getDisciplinas().entrySet()) {
                    
                    //me.getValue()= pega o conteudo do mapa, ou seja, a propria disciplina
                    System.out.println("- " + me.getValue().getNome());

                    // for PARA LISTA DE ALUNOS

                    //mapaAluno= "lista" de alunos presente na disciplina "me"
                    for(Map.Entry<Integer, Aluno> mapaAluno : me.getValue().getListAluno().entrySet()){
                        //atribui o Aluno da "lista" da disciplina a variavel aluno
                        Aluno aluno = mapaAluno.getValue();

                        System.out.println("\t" + "- " + aluno.getNome() + " " + "(" + aluno.getCurso().getNome() + ")");
                        

                    }
                    
                }

                // PROVAS
                System.out.println("Provas e notas recebidas:");
                // FOR PARA LISTA DAS PROVAS
                //me= prova da "lista" de provas, vai rodar cada prova por vez
                for (Map.Entry<String,Avaliacao> me: programa.getAvaliacoes().entrySet()) {
                   
                    System.out.println("- " + me.getValue().getCodigo());
                     // FOR PARA LISTA DE ALUNOS

                     //mapaAluno= "lista" de alunos presente na prova "me"
                    for(Map.Entry<Integer, Aluno> mapaAluno : me.getValue().getListAluno().entrySet()){
                        //atribui o Aluno da "lista" da prova a variavel aluno
                        Aluno aluno =mapaAluno.getValue();
                        //como a nota tem como chave de busca a matricula do aluno, usa o aluno que foi armazenado para procurar pela nota e armazena numa variavel
                        Float nota = me.getValue().getNota(aluno.getMatricula());

                        System.out.println("\t" + "- " + aluno.getNome() + " " + ":" + nota);

                    }
                }

    }




    public void serializarDados(Programa programa) throws IOException{
       
                String arqnome= "dados.dat";
                FileOutputStream file= new FileOutputStream(arqnome);
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(programa);
                out.close();
                System.out.println("Arquivo serializado como: "+arqnome);
    }








    public Programa carregarArquivos(){
                Programa programa=null;
                String arqnome= "dados.dat";
                File arq= new File(arqnome);
                if(arq.exists())
                    try{
                        ObjectInputStream in;
                        try {
                            in = new ObjectInputStream(new FileInputStream(arqnome));
                            programa = (Programa)in.readObject();
                            in.close();
                        }
                        catch(ClassNotFoundException e){
                            System.out.println("error: "+e.getMessage());
                            
                        }
                    } catch (IOException e) {
                       System.out.println("Erro de I/O.");;
                    }
                else{
                    System.out.println("Nome do arquivo inválido, arquivo não encontrado");
                }
        return programa;
    }
















    public void relatorioF(Programa programa){//relatorio final
        Disciplina disciplina=null; 
        Prova prova;
        Trabalho trabalho;
        ProvaF provaF;
        float notap=0,notaf=0,media=0,peso=0,div=0;
               
        for (Map.Entry<String, Disciplina> dis : programa.getDisciplinas().entrySet()){
                        disciplina = dis.getValue();

                        System.out.println("Disciplina: "+disciplina.getNome());
                        System.out.println("Alunos:");

                        for (Map.Entry<Integer, Aluno> me : disciplina.getListAluno().entrySet()) {
                            Aluno aluno=me.getValue();
                            System.out.println("Matrícula: "+aluno.getMatricula()+"; Nome: "+aluno.getNome()+"; Notas das provas: ");
                            notap=notaf=media=peso=div=0;
                            for(Map.Entry<String,Prova> pro: disciplina.getListProva().entrySet()){
                                prova=pro.getValue();
                                if(prova.getListAluno().containsKey(aluno.getMatricula())){
                                    System.out.println("\t-"+prova.getNome()+", peso: "+prova.getPeso()+" Nota: "+prova.getNota(aluno.getMatricula()));
                                    peso=prova.getPeso();
                                    media+=(peso*prova.getNota(aluno.getMatricula()));
                                    div+=peso;
                                }
                                else{
                                    System.out.println("\t-"+prova.getNome()+", peso: "+prova.getPeso()+" Nota: "+"não fez");
                                    peso=prova.getPeso();
                                    media+=0;
                                    div+=peso;
                                }

                            }
                            
                            for(Map.Entry<String,Trabalho> trab: disciplina.getListTrabalho().entrySet()){
                                trabalho=trab.getValue();
                                if(trabalho.getListAluno().containsKey(aluno.getMatricula())){
                                    System.out.println("\t-"+trabalho.getNome()+", peso: "+trabalho.getPeso()+" Nota: "+trabalho.getNota(aluno.getMatricula()));
                                    peso=trabalho.getPeso();
                                    media+=(peso*trabalho.getNota(aluno.getMatricula()));
                                    div+=peso;
                                }
                                else{
                                    System.out.println("\t-"+trabalho.getNome()+", peso: "+trabalho.getPeso()+" Nota: "+"não fez");
                                    peso=trabalho.getPeso();
                                    media+=0;
                                    div+=peso;
                                }

                            }
                            notap=media/div;
                            System.out.println("\t-A media parcial é: "+notap);

                            if(notap<7.0){
                                provaF=disciplina.getProvaF();
                                if(provaF.getListAluno().containsKey(aluno.getMatricula())){
                                    System.out.println("\t-"+provaF.getNome()+", Nota: "+provaF.getNota(aluno.getMatricula()));
                                    notaf=(notap/2)+(provaF.getNota(aluno.getMatricula())/2);
                                    System.out.println("\t-Nota final: "+notaf);
                                }
                                else{
                                    System.out.println("\t-"+provaF.getNome()+", Nota: "+"não fez");
                                    notaf=(notap/2);
                                    System.out.println("\t-Nota final: "+notaf);
                                }
                            }
                        
                        }//fim do loop com aluno
        }
    }



    public void relatorioD(Programa programa){//relatorio das disciplinas
        Disciplina disciplina=null;
        Curso cursog;
        float media=0,div=0;
        int passou=0,total=0;
        
        //estatisticas por disciplina
        Aluno aluno;
        //ENTRANDO E CAPTURANDO DISCIPLINA
        for (Map.Entry<String, Disciplina> me1 : programa.getDisciplinas().entrySet()) {
            disciplina=me1.getValue();
            disciplina.calculaNotaF();//adiciona a nota final dos alunos nessa disciplina
                  
            System.out.println("- " + disciplina.getCodigo()+"- "+disciplina.getNome()+" Cursos: ");
            //ENTRANDO EM CURSOS E PROCURANDO A DISCIPLINA 
            for(Map.Entry<Integer,Curso> me2 : programa.getCursos().entrySet()){
                cursog=me2.getValue();
                if(cursog.getListDisciplinas().containsKey(disciplina.getCodigo())){//verifica se a disciplina está presente nesse curso
                    for(Map.Entry<Integer, Aluno> me : disciplina.getListAluno().entrySet()){
                        aluno=me.getValue();
                        //PROCURANDO O ALUNO UM A UM DENTRO DO CURSO
                        if(cursog.getListAlunos().containsKey(aluno.getMatricula())){//verifica se o aluno faz parte do curso
                            total++;//total de alunos do curso na disciplina
                                        
                            media+=disciplina.getNotaf(aluno.getMatricula());//pega a media do aluno
                            div++;//auxiliar de divisao
                            if(disciplina.getNotaf(aluno.getMatricula())>=5.0){//verifica quem teve uma nota final maior que 5
                                passou++;//total de alunos que passaram
                            }
                        }
                    }
                    media/=div;//dividindo a soma de medias e transformando em media de medias
                                
                    System.out.println("\t-"+cursog.getNome()+" media de notas finais: "+media+" ,percentual de alunos aprovados: "+((passou*100)/total)+"%;");//imprime a porcentagem de pessoas que passaram por curso
                    //zera variaveis
                    div=media=0;
                    total=passou=0;
                }
            }
                    

        }
    }
    










    public void relatorioA(Programa programa){//relatoria de avaliacao
        Avaliacao ava;
        for(Map.Entry<String,Avaliacao> me2 : programa.getAvaliacoes().entrySet()){
                            ava=me2.getValue();//atribui avaliação
                            
                            System.out.println("Código da disciplina: "+ava.getDisciplina().getCodigo()+", código da avaliação: "+ava.getCodigo()+" ,nome: "+ava.getNome()+", data: "+ ava.getData()+", media das notas obtidas: "+ ava.mediaAvaliativa());// imprime o relatorio da avaliação
                        }
    }








    public void relatorio(Programa programa){//chama todos os relatorios de uma vez
        
        this.relatorioF(programa);
        this.relatorioD(programa);
        this.relatorioA(programa);      

            
    }
}
            
    












