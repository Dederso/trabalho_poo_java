//import java.util.ArrayList;

import java.io.*;
import java.util.*;
//import java.util.List;

import javax.print.DocFlavor.STRING;

/*
• Cadastrar curso;
• Cadastrar disciplina;
• Cadastrar prova;
• Cadastrar aluno(a);
• Matricular aluno(a) em disciplina;
• Registrar nota de aluno em prova;
• Imprimir dados;
• Sair do programa.
*/

public class App {
    public static void main(String[] args) throws IOException {
        Programa programa = new Programa();
        int i = 1;
        Scanner sc = new Scanner(System.in);
        CursoPos mestrado = new CursoPos("Mestrado");
        CursoPos doutorado = new CursoPos("Doutorado");
        programa.adicionaCursoPos(1,mestrado);
        programa.adicionaCursoPos(2,doutorado);

        while (i != 0) {
            System.out.println("1 - Cadastrar curso");
            System.out.println("2 - Cadastrar disciplina");
            System.out.println("3 - Cadastrar avaliacao");
            System.out.println("4 - Cadastrar aluno(a)");
            System.out.println("5 - Matricular aluno(a) em disciplina");
            System.out.println("6 - Registrar nota de aluno(s)");
            System.out.println("7 - Imprimir dados");
            System.out.println("8 - Serializar arquivos");
            System.out.println("9 - Carregar arquivos serializados");
            System.out.println("10 - Relatório");
            System.out.println("0 - Sair do programa");
            i = Integer.parseInt(sc.nextLine());

            if (i == 1) {
                // Cadastrar curso

                System.out.println("Número do curso");
                int numCurso = Integer.parseInt(sc.nextLine());

                // verificação de cóodigo existente
                // for (int j = 0; j < args.length; j++) {
                // if (numcurso==) {

                // }
                // }

                System.out.println("Nome do curso");
                String nomeCurso = sc.nextLine();

                CursoGrad curso = new CursoGrad(numCurso, nomeCurso);
                programa.adicionaCurso(curso);
            }

            if (i == 2) {
                // Cadastrar disciplina

                System.out.println("Código da Disciplina");
                String codDis = sc.nextLine();

                // verificação de cóodigo existente
                // for (int j = 0; j < args.length; j++) {
                // if (numcurso==) {

                // }
                // }

                System.out.println("Nome da Disciplina");
                String nomeDis = sc.nextLine();

                Disciplina disciplina = new Disciplina(codDis, nomeDis);
                programa.adicionaDisciplina(disciplina);
            }

            if (i == 3) {
                // Cadastrar prova
                System.out.println("1 - Prova");
                System.out.println("2 - Trabalho");
                System.out.println("3 - Prova Final");
                int aux = Integer.parseInt(sc.nextLine());


                if (programa.getDisciplinas().isEmpty()) {
                    System.out.println("Sem disciplinas cadastradas, voltando ao menu.");
                    break;
                }

                    
                //Cadastrar Prova
                if (aux==1) {
                    System.out.println("Código da Disciplina");
                    String codDis = sc.nextLine();

                    Disciplina disciplina = programa.getDisciplinas().get(codDis);

                    System.out.println("Código da Prova");
                    String codProva = sc.nextLine();

                    System.out.println("Nome da Prova");
                    String nomeProva = sc.nextLine();

                    System.out.println("Peso da Prova");
                    float pesoProva = Float.parseFloat(sc.nextLine());

                    System.out.println("Data da Prova (Formato dd/mm/aaaa)");
                    String dataProva = sc.nextLine();

                    Prova prova = new Prova(disciplina, codProva, nomeProva, pesoProva, dataProva);
                    
                    programa.adicionaProva(prova);
                    disciplina.addProva(prova);
                }

                //Cadastrar Prova Final
                else if (aux==3) {
                    System.out.println("Código da Disciplina");
                    String codDis = sc.nextLine();

                    Disciplina disciplina = programa.getDisciplinas().get  (codDis);

                    System.out.println("Código da Prova");
                    String codProva = sc.nextLine();

                    System.out.println("Nome da Prova");
                    String nomeProva = sc.nextLine();

                    System.out.println("Data da Prova (Formato dd/mm/aaaa)");
                    String dataProva = sc.nextLine();

                    ProvaF provaf = new ProvaF(disciplina, codProva, nomeProva, dataProva);
                    
                    programa.adicionaProvaF(provaf);
                    disciplina.setProvaF(provaf);
                }

                //Cadastrar Trabalho
                else if (aux==2) {
                    System.out.println("Código da Disciplina");
                    String codDis = sc.nextLine();

                    Disciplina disciplina = programa.getDisciplinas().get  (codDis);

                    System.out.println("Código de Trabalho");
                    String codProva = sc.nextLine();

                    System.out.println("Nome de Trabalho");
                    String nomeProva = sc.nextLine();

                    System.out.println("Peso de Trabalho");
                    float pesoProva = Float.parseFloat(sc.nextLine());

                    System.out.println("Data de entrega do Trabalho (Formato dd/mm/aaaa)");
                    String dataProva = sc.nextLine();

                    System.out.println("Tamanho máximo do grupo");
                    int max = Integer.parseInt(sc.nextLine());

                    Trabalho trabalho = new Trabalho(disciplina, codProva, nomeProva, pesoProva, dataProva, max);
                    
                    programa.adicionaTrabalho(trabalho);
                    disciplina.addTrabalho(trabalho);
                }  
            }

            if (i == 4) {
                // Cadastrar aluno(a)
                System.out.println("Para cadastrar um aluno de graduação digite: 1, para cadastrar um aluno de pós-graduação digite: 2");
                int aux = Integer.parseInt(sc.nextLine());
                while(aux!=1&&aux!=2){
                    System.out.println("Valor digitado invalido, você digitou: "+aux+"\n"+"digite:1 para cadastrar um aluno de graduação ou digite:2 para cadastrar um aluno de pós-graduação");
                    aux = Integer.parseInt(sc.nextLine());
                }
                if(aux==1){
                    System.out.println("Código de Matrícula");
                    int codMat = Integer.parseInt(sc.nextLine());

                    // verificação de código existente
                    sc = new Scanner(System.in);
                    System.out.println("Nome do Aluno");
                    String nomeAluno = sc.nextLine();

                    System.out.println("Código do curso do Aluno");
                    int cursoAluno = Integer.parseInt(sc.nextLine());

                    Curso curso = programa.getCursos().get(cursoAluno);
                    // verificação de cóodigo existente

                    AlunoGrad alunograd = new AlunoGrad(codMat, nomeAluno, curso);
                    curso.addAluno(alunograd);
                    programa.adicionaAluno(alunograd);
                }
                if(aux==2){
                    System.out.println("Código de Matrícula");
                    int codMat = Integer.parseInt(sc.nextLine());

                    // verificação de código existente
                    sc = new Scanner(System.in);
                    System.out.println("Nome do Aluno");
                    String nomeAluno = sc.nextLine();

                    System.out.println("Digite:1 para Mestrado ou digite:2 para Doutorado");
                    int posgrad = Integer.parseInt(sc.nextLine());
                    while(posgrad!=1&&posgrad!=2){
                        System.out.println("Valor invalido, você digitou: "+posgrad+"\n"+"Digite:1 para Mestrado ou digite:2 para Doutorado");
                    }
                    if(posgrad==1){
                        AlunoPos alunopos = new AlunoPos(codMat, nomeAluno, mestrado);
                        programa.adicionaAluno(alunopos);
                        mestrado.addAluno(alunopos);
                    }
                    if(posgrad==2){
                        AlunoPos alunopos = new AlunoPos(codMat, nomeAluno, doutorado);
                        programa.adicionaAluno(alunopos);
                        doutorado.addAluno(alunopos);
                    }

                }
            }

            if (i == 5) {
                // Matricular aluno(a) em disciplina

                System.out.println("Aluno de Graduação ou Pós?");
                System.out.println("1- Graduação");
                System.out.println("2- Pos");
                int aux = Integer.parseInt(sc.nextLine());
                while(aux!=1&&aux!=2){
                    System.out.println("Valor digitado invalido, você digitou: "+aux+"\n"+"digite:1 para graduação ou digite:2 pós-graduação");
                    aux = Integer.parseInt(sc.nextLine());
                }
                if(aux==1){
                    System.out.println("Matricula do Aluno");

                    int matAluno = Integer.parseInt(sc.nextLine());
                    
                    Aluno aluno = programa.getAlunos().get(matAluno);

                    Curso curso;

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
                        System.out.println("Matricula do Aluno");

                        int matAluno = Integer.parseInt(sc.nextLine());
                        
                        Aluno aluno = programa.getAlunos().get(matAluno);
            
                        System.out.println("Disciplina a ser matriculado");
                        String codDis = sc.nextLine();


                        Disciplina disciplina = programa.getDisciplinas().get(codDis);
                        if(mestrado.getListDisciplinas().containsKey(disciplina.getCodigo())){
                        }
                        else{
                            mestrado.addDisciplina(disciplina);
                        }
                        programa.matricular(aluno, disciplina);
                    }
                    if(aux==2){
                        System.out.println("Matricula do Aluno");

                        int matAluno = Integer.parseInt(sc.nextLine());
                        
                        Aluno aluno = programa.getAlunos().get(matAluno);
            
                        System.out.println("Disciplina a ser matriculado");
                        String codDis = sc.nextLine();


                        Disciplina disciplina = programa.getDisciplinas().get(codDis);
                        if(doutorado.getListDisciplinas().containsKey(disciplina.getCodigo())){
                            
                        }
                        else{
                            doutorado.addDisciplina(disciplina);
                        }
                        
                        programa.matricular(aluno, disciplina);
                    }
                }
            }

            if (i == 6) {
                System.out.println("Qual nota deseja registrar?");
                System.out.println("1- Prova");
                System.out.println("2- Trabalho");
                System.out.println("3- Prova Final");
                int aux=Integer.parseInt(sc.nextLine());
               
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

                    Aluno aluno = programa.getAlunos().get(matAluno);
                    Prova prova = programa.getProvas().get(codProva);

                    programa.registraNotaP(aluno, prova, notaProva);
                }
                if(aux==2){
                    //Registrar nota de alunos em trabalho
                    System.out.println("Codigo do Trabalho");
                    String codTrabalho = sc.nextLine();
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

                    Aluno aluno = programa.getAlunos().get(matAluno);
                    ProvaF prova = programa.getProvasF().get(codProva);

                    programa.registraNotaPF(aluno, prova, notaProva);
                }
              
            }

            if (i == 7) {
                // Imprimir dados
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

                        System.out.println("\t" + "- " + aluno.getNome() + " " + "(curso)-parte funcional comentada" );
                       // System.out.println("\t" + "- " + aluno.getNome() + " " + "(" + aluno.getCurso().getNome() + ")");
                        

                    }
                    
                }

                // PROVAS
                System.out.println("Provas e notas recebidas:");
                // FOR PARA LISTA DAS PROVAS
                //me= prova da "lista" de provas, vai rodar cada prova por vez
                for (Map.Entry<String,Prova> me: programa.getProvas().entrySet()) {
                   
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
            if (i == 8) {
                System.out.println("Digite o nome do arquivo");
                String arqnome= sc.nextLine();
                arqnome=arqnome+".dat";
                FileOutputStream file= new FileOutputStream(arqnome);
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(programa);
                // out.writeObject(programa.getAlunos());
                // out.writeObject(programa.getCursos());
                // out.writeObject(programa.getDisciplinas());
                // out.writeObject(programa.getProvas());
                out.close();
                System.out.println("Arquivo serializado como: "+arqnome);
            }
            if(i == 9){
                System.out.println("Digite o nome do arquivo");
                String arqnome= sc.nextLine();
                File arq= new File(arqnome);
                if(arq.exists())
                    try{
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(arqnome));
                    programa = (Programa)in.readObject();
                    in.close();
                    }
                    catch(ClassNotFoundException e){
                        System.out.println("error: "+e.getMessage());
                        
                    }
                else{
                    System.out.println("Nome do arquivo inválido, arquivo não encontrado");
                }
            }

            if(i == 10){
                System.out.println("Qual relatório você gostaria de ver");
                System.out.println("1- Pauta final de disciplina");
                System.out.println("2- Estatísticas por disciplina");
                System.out.println("3- Estatísticas por avaliação");
                int aux = Integer.parseInt(sc.nextLine());
                Disciplina disciplina;
                CursoPos cursop;
                CursoGrad cursog;
                Prova prova;
                Trabalho trabalho;
                ProvaF provaF;
                float notap=0,notaf=0,media=0,peso=0,div=0;
                int passou=0,total=0;

                if (aux == 1) {
                    //pauta final
                    System.out.println("Digite o código da disciplina");
                    String codDis=sc.nextLine();
                    disciplina = programa.getDisciplinas().get(codDis);

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
                    //fim do loop com aluno
                    }

                }



                if (aux == 2) {
                    //estatisticas por disciplina
                    Aluno aluno;
                    //ENTRANDO E CAPTURANDO DISCIPLINA
                    for (Map.Entry<String, Disciplina> me1 : programa.getDisciplinas().entrySet()) {
                      disciplina=me1.getValue();
                      disciplina.calculaNotaF();
                  
                      System.out.println("- " + disciplina.getCodigo()+"- "+disciplina.getNome()+" Cursos: ");

                          //ENTRANDO EM CURSOS E PROCURANDO A DISCIPLINA ANTERIOR
                          for(Map.Entry<Integer,CursoGrad> me2 : programa.getCursos().entrySet()){
                            cursog=me2.getValue();
                            if(cursog.getListDisciplinas().containsKey(disciplina.getCodigo())){
                                for(Map.Entry<Integer, Aluno> me : disciplina.getListAluno().entrySet()){
                                    aluno=me.getValue();
                                    //PROCURANDO O ALUNO UM A UM DENTRO DO CURSO
                                    if(cursog.getListAlunos().containsKey(aluno.getMatricula())){
                                        total++;
                                        
                                        media+=disciplina.getNotaf(aluno.getMatricula());
                                        div++;
                                        if(disciplina.getNotaf(aluno.getMatricula())>=5.0){
                                        passou++;
                                        }
                                    }
                                }
                                media/=div;
                                
                                System.out.println("\t-"+cursog.getNome()+" media de notas finais: "+media+" ,percentual de alunos aprovados: "+((passou*100)/total)+"%;");
                                div=media=0;
                                total=passou=0;
                            }
                          }
                    
                    cursop=mestrado;
                    if(cursop.getListDisciplinas().containsKey(disciplina.getCodigo())){
                        for(Map.Entry<Integer, Aluno> me : disciplina.getListAluno().entrySet()){
                            aluno=me.getValue();
                            //PROCURANDO O ALUNO UM A UM DENTRO DO CURSO
                            if(cursop.getListAlunos().containsKey(aluno.getMatricula())){
                                total++;
                                media+=disciplina.getNotaf(aluno.getMatricula());
                                div++;
                                if(disciplina.getNotaf(aluno.getMatricula())>=5.0){
                                passou++;
                                }
                            }
                        }
                        media/=div;
                        
                        System.out.println("\t-"+cursop.getNome()+" media de notas finais: "+media+" ,percentual de alunos aprovados: "+((passou*100)/total)+"%;");
                    }
                    cursop=doutorado;
                    if(cursop.getListDisciplinas().containsKey(disciplina.getCodigo())){
                        for(Map.Entry<Integer, Aluno> me : disciplina.getListAluno().entrySet()){
                            aluno=me.getValue();

                            //PROCURANDO O ALUNO UM A UM DENTRO DO CURSO
                            if(cursop.getListAlunos().containsKey(aluno.getMatricula())){
                                total++;
                                media+=disciplina.getNotaf(aluno.getMatricula());
                                div++;
                                if(disciplina.getNotaf(aluno.getMatricula())>=5.0){
                                passou++;
                                }
                            }
                        }
                        media/=div;
                        
                        System.out.println("\t-"+cursop.getNome()+" media de notas finais: "+media+" ,percentual de alunos aprovados: "+((passou*100)/total)+"%;");
                    }

                    }
                }
                
                if (aux == 3) {
                    //estatisticas por avaliçao
                    
                    if(programa.getProvas().isEmpty()){
                        System.out.println("sem registro de provas");
                    }
                    else{
                        for(Map.Entry<String,Prova> me2 : programa.getProvas().entrySet()){
                            prova=me2.getValue();
                            for(Map.Entry<Integer,Float> notas: prova.getListNotas().entrySet()){
                                media+=notas.getValue();
                                div++;
                            }
                            media/=div;
                            System.out.println("Código da disciplina: "+prova.getDisciplina().getCodigo()+", código da avaliação: "+prova.getCodigo()+" ,nome: "+prova.getNome()+", data: "+ prova.getData()+", media das notas obtidas: "+ media);
                        }
                    }
                    if(programa.getTrabalhos().isEmpty()){
                        System.out.println("sem registro de trabalhos");
                    }
                    else{
                        for(Map.Entry<String,Trabalho> me2 : programa.getTrabalhos().entrySet()){
                            trabalho=me2.getValue();
                            for(Map.Entry<Integer,Float> notas: trabalho.getListNotas().entrySet()){
                                media+=notas.getValue();
                                div++;
                            }
                            media/=div;
                            System.out.println("Código da disciplina: "+trabalho.getDisciplina().getCodigo()+", código da avaliação: "+trabalho.getCodigo()+" ,nome: "+trabalho.getNome()+", data: "+ trabalho.getData()+", media das notas obtidas: "+ media);
                        
                        }
                    }
                    if(programa.getProvasF().isEmpty()){
                        System.out.println("sem registro de provas finais");
                    }
                    else{
                        for(Map.Entry<String,ProvaF> me2 : programa.getProvasF().entrySet()){
                            provaF=me2.getValue();
                            for(Map.Entry<Integer,Float> notas: provaF.getListNotas().entrySet()){
                                media+=notas.getValue();
                                div++;
                            }
                            media/=div;
                            System.out.println("Código da disciplina: "+provaF.getDisciplina().getCodigo()+", código da avaliação: "+provaF.getCodigo()+" ,nome: "+provaF.getNome()+", data: "+ provaF.getData()+", media das notas obtidas: "+ media);
                        }
                    }
                }
            }
            if (i == 0) {
                sc.close();
                System.out.println("Programa encerrado.");
            }

        }

    }
}


class Programa implements Serializable {

    private Map<Integer,Aluno> alunos = new HashMap<Integer,Aluno>();
    private Map<String,Prova> provas = new HashMap<String,Prova>();
    private Map<Integer,CursoGrad> cursos = new HashMap<Integer,CursoGrad>();
    private Map<String,Disciplina> disciplinas = new HashMap<String,Disciplina>();
    private Map<Integer,CursoPos> cursospos = new HashMap<Integer,CursoPos>();
    private Map<String,Trabalho> trabalhos = new HashMap<String,Trabalho>();
    private Map<String,ProvaF> provasf = new HashMap<String,ProvaF>();

    public Map<Integer,CursoGrad> getCursos() {
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
    void adicionaCurso(CursoGrad curso) {
        cursos.put(curso.getCodigo(),curso);
    }

    void adicionaCursoPos(int i,CursoPos curso){
        cursospos.put(i,curso);
    }
    void adicionaDisciplina(Disciplina disciplina) {
        disciplinas.put(disciplina.getCodigo(),disciplina);
    }
}

 // NAO UTILIZADOS POR ENQUANTO  
    /* 
    Disciplina searchD(String codigo) {
        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).verificaD(codigo)) {
                return disciplinas.get(i);
            }
        }
        return null;
    }
    
    Aluno searchA(int matricula) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).verificaA(matricula)) {
                return alunos.get(i);
            }
        }
        return null;
    }

    Prova searchP(String codigo) {
        for (int i = 0; i < provas.size(); i++) {
            if (provas.get(i).verificaP(codigo)) {
                return provas.get(i);
            }
        }
        return null;
    }

    Curso searchC(int codigo) {
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).verificaC(codigo)) {
                return cursos.get(i);
            }
        }
        return null;
    }
    */


