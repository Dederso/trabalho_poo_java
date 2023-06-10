import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;



public class Funcoes {
    public void cadastrarCurso(Programa programa,Scanner sc){
        // Cadastrar curso
        int aux=0;//auxiliar caso tenha um código repetido
        int numCurso;
        System.out.println("Número do curso");
        
        try{
            numCurso = Integer.parseInt(sc.nextLine());//pega o numero do curso
            if(programa.getCursos().containsKey(numCurso)){//verifica se já existe um curso com esse codigo
                aux=1;//variável que ajuda no loop
                while(aux==1){//loop
                    try{
                        System.out.println("Código repetido para curso: "+numCurso+".");
                        System.out.println("tente novamente.");
                        numCurso = Integer.parseInt(sc.nextLine());//pede entrada do usuario ate conseguir uma entrada valida
                        if(!programa.getCursos().containsKey(numCurso)){//verifica se o curso nao existe no mapa geral de cursos
                            aux=0;//zera a variavel, encerrando o loop
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Erro de formatação.");//erro de formatacao no numero do curso, entrada esperada: inteiro
                    }
                }
            }

            System.out.println("Nome do curso");
            String nomeCurso = sc.nextLine();//pega e armazena o nome do curso

            CursoGrad curso = new CursoGrad(numCurso, nomeCurso);
            programa.adicionaCurso(curso);//adiciona o curso no mapa geral de cursos
        }
       
        catch(NumberFormatException e){
         System.out.println("Erro de formatação.");
            return;
        }
    }

    public void cadastrarDisciplina(Programa programa, Scanner sc){
        //cadastrar disciplina
        int aux=0;//auxiliar que ajuda na verificacao de erro
                
                System.out.println("Código da Disciplina");
                String codDis = sc.nextLine();//entrada do usuario para o codigo da disciplina
               
                if(programa.getDisciplinas().containsKey(codDis)){//verifica se o codigo da disciplina ja existe no mapa geral de disciplinas
                    aux=1;//auxilia no loop
                    while(aux==1){//loop
                        System.out.println("Código repetido para disciplina: "+codDis);
                        System.out.println("Tente novamente.");
                        codDis = sc.nextLine();//pede uma nova entrada para o codigo de disciplinas
                        if(!programa.getDisciplinas().containsKey(codDis)){//verifica se a disciplina nao esta no mapa geral de disciplinas
                            aux=0;//zera o auxiliar, encerrando o loop
                        }
                    }
                }
            

                System.out.println("Nome da Disciplina");
                String nomeDis = sc.nextLine();//entrada do usuario para o nome da disciplina

                Disciplina disciplina = new Disciplina(codDis, nomeDis);//cria um objeto Disciplina e atribuia a uma variavel
                programa.adicionaDisciplina(disciplina);//adiciona a disciplina no mapa geral de disciplinas
    }

    public void cadastrarAvaliacao(Programa programa,Scanner sc,SimpleDateFormat sdf){
        // Cadastrar prova
        if (programa.getDisciplinas().isEmpty()) {//verifica se nao há nenhuma disciplina para ser feito o cadastro de avaliacoes
            System.out.println("Sem disciplinas cadastradas, voltando ao menu.");
        }
        else
            System.out.println("1 - Prova");
            System.out.println("2 - Trabalho");
            System.out.println("3 - Prova Final");
            int aux = Integer.parseInt(sc.nextLine());//entrada do usuario que diz qual avaliacao sera cadastrada
            int aux1=0;//auxilia no caso de erros
            
            //Cadastrar Prova
            if (aux==1) {
                try{    //TIVE QUE TROCAR A ORDEM PROVA DISCIPLINA POIS ESTAVA DANDO ERRO, OUTRA SOLUCAO É SALIENTAR O ERRO DPS DA ENTRADA DOS DOIS INTENS
                    System.out.println("Código da Prova");
                    String codProva = sc.nextLine();

                    //sequencia de entradas do usuario e onde sao armazenadas
                    System.out.println("Código da Disciplina");
                    String codDis = sc.nextLine();

                    Disciplina disciplina = programa.getDisciplinas().get(codDis);//atribui a um objeto para melhor leitura

                    if(!programa.getDisciplinas().containsKey(codDis)){//verifica se o codigo de disciplina esta no mapa de disciplinas cadastradas
                        aux1=1;// auxiliar que ajuda no loop
                        while(aux1==1){
                            System.out.println("Código de disciplina não definido usado na avaliação "+ codProva+": "+codDis);
                            System.out.println("Tente novamente.");
                            codDis = sc.nextLine();// chama outra entrada do usuario
                            if(programa.getDisciplinas().containsKey(codDis)){//verifica se essa disciplina é valida
                                aux1=0;//zera o auxiliar, encerrando o loop
                            }
                        }
                    }

                    System.out.println("Nome da Prova");
                    String nomeProva = sc.nextLine();

                    System.out.println("Peso da Prova");
                    float pesoProva = Float.parseFloat(sc.nextLine());

                    if(pesoProva<=0){//verifica se o peso é válido (maior que 0)
                        aux1=1;//auxiliar que ajuda no loop
                        while(aux1==1){
                            try{ 
                                System.out.println("Peso inválido para avaliação "+ codProva+": "+pesoProva+".");
                                System.out.println("Tente novamente");
                                pesoProva=Float.parseFloat(sc.nextLine());//entrada do usuario de um novo peso para a prova
                                if(pesoProva>0){
                                    aux1=0;//zera o auxiliar, encerrando o loop
                                }
                            }
                            catch(NumberFormatException e){
                                System.out.println("Erro de formatação.");
                            }
                        }
                    }

                    System.out.println("Data da Prova (Formato dd/mm/aaaa)");
                    Date dataProva = sdf.parse(sc.nextLine());

                    Prova prova = new Prova(disciplina, codProva, nomeProva, pesoProva, dataProva);//constroi um novo objeto prova
                    
                    programa.adicionaProva(prova);//adiciona a prova no mapa geral de provas
                    disciplina.addProva(prova);// adiciona a prova na lista de provas dentro da disciplina
                }
                catch(java.text.ParseException e){
                    System.out.println("Erro de formatação.");//erro de formatação na data "dd/MM/aaaa"
                }
                catch(NumberFormatException e){
                    System.out.println("Erro de formatação.");//erro de formatação no float "peso"
                }
        }

        //Cadastrar Prova Final
        else if (aux==3) {
            //mesmo procedimento para cadastrar provas, em caso de duvida, veja acima
            try{
                System.out.println("Código da Disciplina");
                String codDis = sc.nextLine();

                System.out.println("Código da Prova");
                String codProva = sc.nextLine();

                System.out.println("Nome da Prova");
                String nomeProva = sc.nextLine();

                System.out.println("Data da Prova (Formato dd/mm/aaaa)");
                Date dataProva = sdf.parse(sc.nextLine());



                if(!programa.getDisciplinas().containsKey(codDis)){
                    aux1=1;
                    while(aux1==1){
                        System.out.println("Código de disciplina não definido usado na avaliação "+codProva+": "+codDis);
                        System.out.println("Tente novamente.");
                        codDis = sc.nextLine();
                        if(programa.getDisciplinas().containsKey(codDis)){
                            aux1=0;
                        }
                    }
                }


                Disciplina disciplina = programa.getDisciplinas().get(codDis);
                ProvaF provaf = new ProvaF(disciplina, codProva, nomeProva, dataProva);
                
                programa.adicionaProvaF(provaf);
                disciplina.setProvaF(provaf);
            }
            catch(java.text.ParseException e){
                System.out.println("Erro de formatação.");
            }
        }

        //Cadastrar Trabalho
        else if (aux==2) {
            //mesmo procedimento para cadastrar provas, porém com adicao de tamanho maximo de grupo, em caso de duvida, veja acima
            
            try{
                System.out.println("Código da Disciplina");
                String codDis = sc.nextLine();

                System.out.println("Código de Trabalho");
                String codProva = sc.nextLine();

                System.out.println("Nome de Trabalho");
                String nomeProva = sc.nextLine();

                System.out.println("Peso de Trabalho");
                float pesoProva = Float.parseFloat(sc.nextLine());

                System.out.println("Data de entrega do Trabalho (Formato dd/mm/aaaa)");
                Date dataProva =sdf.parse(sc.nextLine());

                System.out.println("Tamanho máximo do grupo");
                int max = Integer.parseInt(sc.nextLine());//entrada que define o tamanho do grupo

                if(max<=0){//verifica se o maximo é válido (maior que 0)
                    aux1=1;//auxiliar que ajuda no loop
                    while(aux1==1){
                        try{ 
                            System.out.println("Tamanho máximo do grupo inválido para avaliação "+ codProva+": "+max+".");
                            System.out.println("Tente novamente");
                            max=Integer.parseInt(sc.nextLine());//entrada do usuario de um novo maximo para a prova
                            if(max>0){
                                aux1=0;//zera o auxiliar, encerrando o loop
                            }
                        }
                        catch(NumberFormatException e){
                            System.out.println("Erro de formatação.");
                        }
                    }
                }

                if(pesoProva<=0){//verifica se o peso é válido (maior que 0)
                    aux1=1;//auxiliar que ajuda no loop
                    while(aux1==1){
                        try{ 
                            System.out.println("Peso inválido para avaliação "+ codProva+": "+pesoProva+".");
                            System.out.println("Tente novamente");
                            pesoProva=Float.parseFloat(sc.nextLine());//entrada do usuario de um novo peso para a prova
                            if(pesoProva>0){
                                aux1=0;//zera o auxiliar, encerrando o loop
                            }
                        }
                        catch(NumberFormatException e){
                            System.out.println("Erro de formatação.");
                        }
                    }
                }

                if(!programa.getDisciplinas().containsKey(codDis)){
                    aux1=1;
                    while(aux1==1){
                        System.out.println("Código de disciplina não definido usado na avaliação "+codProva+": "+codDis);
                        System.out.println("Tente novamente.");
                        codDis = sc.nextLine();
                        if(programa.getDisciplinas().containsKey(codDis)){
                            aux1=0;
                        }
                    }
                }


                Disciplina disciplina = programa.getDisciplinas().get  (codDis);
                Trabalho trabalho = new Trabalho(disciplina, codProva, nomeProva, pesoProva, dataProva, max);
                
                programa.adicionaTrabalho(trabalho);
                disciplina.addTrabalho(trabalho);
            }
            catch(NumberFormatException e){
                System.out.println("Erro de formatação.");
            }
            catch(java.text.ParseException e){
                System.out.println("Erro de formatação.");
            }
        }  
    }

    public void cadastrarAluno(Programa programa,Scanner sc){
        // Cadastrar aluno(a)
        Curso curso;
        System.out.println("Para cadastrar um aluno de graduação digite: 1, para cadastrar um aluno de pós-graduação digite: 2");
        int aux1=0;//auxiliar que ajuda na verificacao de erros
        try{
            int aux = Integer.parseInt(sc.nextLine());//entrada do usuario que dita se o cadastro e de aluno de graduacao ou pos graduacao
            while(aux!=1&&aux!=2){//verifica se o usuario nao colocou uma entrada diferente de "1" ou "2" e coloca um loop ate que a entrada seja valida
               try{
                System.out.println("Valor digitado invalido, você digitou: "+aux+"\n"+"digite:1 para cadastrar um aluno de graduação ou digite:2 para cadastrar um aluno de pós-graduação");
                aux = Integer.parseInt(sc.nextLine());//nova entrada do usuario 
               }
               catch(NumberFormatException e){
                System.out.println("Erro de formatação.");
               }
            }
            if(aux==1){
                if(programa.getCursos().isEmpty()){//verifica se há cursos 
                    System.out.println("Sem cursos cadastrados, voltando ao menu");
                }
                else
                    try{
                        System.out.println("Código de Matrícula");
                        int codMat = Integer.parseInt(sc.nextLine());//entrada do usuario para a matricula do aluno
                    
                        while(codMat==1||codMat==2){
                            System.out.println("cursos 1 e 2 reservados para pos graduação, tente outro numero");
                            codMat= Integer.parseInt(sc.nextLine());
                        }
                        // verificação de código existente
                        if(programa.getAlunos().containsKey(codMat)){//verifica se o mapa de alunos ja possui essa matricula.
                            aux1=1;//garante o loop enquanto a matrícula nao for válida
                            while(aux1==1){
                                try{
                                    System.out.println("Matrícula repetida para aluno: "+codMat+".");
                                    System.out.println("Tente novamente");
                                    codMat = Integer.parseInt(sc.nextLine());//pede a entrada de uma nova matricula
                                    if(!programa.getAlunos().containsKey(codMat)){//caso a matrícula seja valida, entra nesse caso e zera o auxiliar, liberando o loop
                                        aux=0;
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Erro de formatação.");
                                }
                            }
                        }

                        //entrada do usuario para o resto das informaçoes
                        System.out.println("Nome do Aluno");
                        String nomeAluno = sc.nextLine();

                        System.out.println("Código do curso do Aluno");
                        int cursoAluno = Integer.parseInt(sc.nextLine());
                        if(!programa.getCursos().containsKey(cursoAluno)){//verifica se não existe um curso com esse codigo
                            aux1=1;//variável que ajuda no loop
                            while(aux1==1){//loop
                                try{
                                    System.out.println("Código de curso não definido usado no aluno: "+codMat+": "+cursoAluno+".");
                                    System.out.println("tente novamente.");
                                    cursoAluno = Integer.parseInt(sc.nextLine());//pede entrada do usuario ate conseguir uma entrada valida
                                    if(programa.getCursos().containsKey(cursoAluno)){//verifica se o curso existe no mapa geral de cursos
                                        aux1=0;//zera a variavel, encerrando o loop
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Erro de formatação.");//erro de formatacao no numero do curso, entrada esperada: inteiro
                                }
                            }
                        }
                        curso = programa.getCursos().get(cursoAluno);//atribui o curso a uma variavel

                        AlunoGrad alunograd = new AlunoGrad(codMat, nomeAluno, curso);//cria um objeto aluno de graduacao
                        curso.addAluno(alunograd);//adiciona o aluno na lista de de alunos do curso
                        programa.adicionaAluno(alunograd);//adiciona o aluno na lista de alunos do programa
                    }
                    catch(NumberFormatException e){
                        System.out.println("Erro de formatação.");
                    }              
            }

            if(aux==2){
                System.out.println("Código de Matrícula");
                int codMat = Integer.parseInt(sc.nextLine());

                // verificação de código existente
                if(programa.getAlunos().containsKey(codMat)){//verifica se o mapa de alunos ja possui essa matricula.
                    aux1=1;//garante o loop enquanto a matrícula nao for válida
                    while(aux1==1){
                        try{
                            System.out.println("Matrícula repetida para aluno: "+codMat+".");
                            System.out.println("Tente novamente");
                            codMat = Integer.parseInt(sc.nextLine());//pede a entrada de uma nova matricula
                            if(!programa.getAlunos().containsKey(codMat)){//caso a matrícula seja valida, entra nesse caso e zera o auxiliar, liberando o loop
                                aux=0;
                            }
                        }
                        catch(NumberFormatException e){
                            System.out.println("Erro de formatação.");
                        }
                    }
                }
                sc = new Scanner(System.in);
                System.out.println("Nome do Aluno");
                String nomeAluno = sc.nextLine();

                System.out.println("Digite:1 para Mestrado ou digite:2 para Doutorado");
                int posgrad = Integer.parseInt(sc.nextLine());
                while(posgrad!=1&&posgrad!=2){
                    System.out.println("Valor invalido, você digitou: "+posgrad+"\n"+"Digite:1 para Mestrado ou digite:2 para Doutorado");
                    posgrad = Integer.parseInt(sc.nextLine());
                }
                if(posgrad==1){
                    curso=programa.getCursos().get(1);
                    AlunoPos alunopos = new AlunoPos(codMat, nomeAluno,curso);
                    programa.adicionaAluno(alunopos);
                    curso.addAluno(alunopos);
                }
                if(posgrad==2){
                    curso=programa.getCursos().get(2);
                    AlunoPos alunopos = new AlunoPos(codMat, nomeAluno, curso);
                    programa.adicionaAluno(alunopos);
                    curso.addAluno(alunopos);
                }

            }
        }
        catch(NumberFormatException e){
            System.out.println("Erro de formatação");
        }  
    }

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

            int codMat = Integer.parseInt(sc.nextLine());
            
            // verificação de código existente
            int aux1=0;
            while(!programa.getAlunos().containsKey(codMat)){//verifica se o mapa de alunos ja possui essa matricula.
                aux1=1;//garante o loop enquanto a matrícula nao for válida
                while(aux1==1){
                    try{
                        System.out.println("Matrícula não existente: "+codMat+".");
                        System.out.println("Tente novamente");
                        codMat = Integer.parseInt(sc.nextLine());//pede a entrada de uma nova matricula
                        if(programa.getAlunos().containsKey(codMat)){//caso a matrícula seja valida, entra nesse caso e zera o auxiliar, liberando o loop
                            aux=0;
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("Erro de formatação.");
                    }
                }
            }

            Aluno aluno = programa.getAlunos().get(codMat);
            curso=((AlunoGrad)aluno).getCurso();

            System.out.println("Disciplina a ser matriculado");
            String codDis = sc.nextLine();//entrada do usuario para o codigo da disciplina
               
            if(programa.getDisciplinas().containsKey(codDis)){//verifica se o codigo da disciplina ja existe no mapa geral de disciplinas
                aux=1;//auxilia no loop
                while(aux==1){//loop
                    System.out.println("Código repetido para disciplina: "+codDis);
                    System.out.println("Tente novamente.");
                    codDis = sc.nextLine();//pede uma nova entrada para o codigo de disciplinas
                    if(!programa.getDisciplinas().containsKey(codDis)){//verifica se a disciplina nao esta no mapa geral de disciplinas
                        aux=0;//zera o auxiliar, encerrando o loop
                    }
                }
            }
            
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

                int codMat = Integer.parseInt(sc.nextLine());
                // verificação de código existente
                int aux1=0;
                while(!programa.getAlunos().containsKey(codMat)){//verifica se o mapa de alunos ja possui essa matricula.
                aux1=1;//garante o loop enquanto a matrícula nao for válida
                    while(aux1==1){
                        try{
                            System.out.println("Matrícula não existente: "+codMat+".");
                            System.out.println("Tente novamente");
                            codMat = Integer.parseInt(sc.nextLine());//pede a entrada de uma nova matricula
                            if(programa.getAlunos().containsKey(codMat)){//caso a matrícula seja valida, entra nesse caso e zera o auxiliar, liberando o loop
                                aux=0;
                            }
                        }
                        catch(NumberFormatException e){
                            System.out.println("Erro de formatação.");
                        }
                    }
                }               
                Aluno aluno = programa.getAlunos().get(codMat);
    
                System.out.println("Disciplina a ser matriculado");
                String codDis = sc.nextLine();//entrada do usuario para o codigo da disciplina
               
                if(programa.getDisciplinas().containsKey(codDis)){//verifica se o codigo da disciplina ja existe no mapa geral de disciplinas
                    aux=1;//auxilia no loop
                    while(aux==1){//loop
                        System.out.println("Código repetido para disciplina: "+codDis);
                        System.out.println("Tente novamente.");
                        codDis = sc.nextLine();//pede uma nova entrada para o codigo de disciplinas
                        if(!programa.getDisciplinas().containsKey(codDis)){//verifica se a disciplina nao esta no mapa geral de disciplinas
                            aux=0;//zera o auxiliar, encerrando o loop
                        }
                    }
                }


                Disciplina disciplina = programa.getDisciplinas().get(codDis);

                curso=programa.getCursos().get(1);

                if(curso.getListDisciplinas().containsKey(disciplina.getCodigo())){
                }
                else{
                    curso.addDisciplina(disciplina);
                }
                programa.matricular(aluno, disciplina);//matriculando aluno na disciplina
            }

            if(aux==2){
                System.out.println("Matricula do Aluno");

                int codMat = Integer.parseInt(sc.nextLine());
                // verificação de código existente
                int aux1=0;
                while(!programa.getAlunos().containsKey(codMat)){//verifica se o mapa de alunos ja possui essa matricula.
                aux1=1;//garante o loop enquanto a matrícula nao for válida
                    while(aux1==1){
                        try{
                            System.out.println("Matrícula não existente: "+codMat+".");
                            System.out.println("Tente novamente");
                            codMat = Integer.parseInt(sc.nextLine());//pede a entrada de uma nova matricula
                            if(programa.getAlunos().containsKey(codMat)){//caso a matrícula seja valida, entra nesse caso e zera o auxiliar, liberando o loop
                                aux=0;
                            }
                        }
                        catch(NumberFormatException e){
                            System.out.println("Erro de formatação.");
                        }
                    }
                }  
                Aluno aluno = programa.getAlunos().get(codMat);
    
                System.out.println("Disciplina a ser matriculado");
                String codDis = sc.nextLine();//entrada do usuario para o codigo da disciplina
               
                if(programa.getDisciplinas().containsKey(codDis)){//verifica se o codigo da disciplina ja existe no mapa geral de disciplinas
                    aux=1;//auxilia no loop
                    while(aux==1){//loop
                        System.out.println("Código repetido para disciplina: "+codDis);
                        System.out.println("Tente novamente.");
                        codDis = sc.nextLine();//pede uma nova entrada para o codigo de disciplinas
                        if(!programa.getDisciplinas().containsKey(codDis)){//verifica se a disciplina nao esta no mapa geral de disciplinas
                            aux=0;//zera o auxiliar, encerrando o loop
                        }
                    }
                }

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

                while(notaProva<0||notaProva>10){//verificando se a nota para avaliacao é valida
                    System.out.println("Nota inválida para avaliação "+codProva+" do(s) aluno(s) "+matAluno+": "+notaProva+".");
                    System.out.println("Tente novamente. ");
                    notaProva = Float.parseFloat(sc.nextLine());
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

                
                while(nota<0||nota>10){//verificando se a nota para avaliacao é valida
                    System.out.println("Nota inválida para avaliação "+codTrabalho+" do(s) aluno(s) : "+nota+".");
                    //FALTA A MATRICULA DOS ALUNOS POIS ELA AINDA NAO FOI ADQUIRIDA NESSA ETAPA DO PROGRAMA, LOGO, PARA SOLUCIONAR APENAS MUDANDO A ORDEM DOS OBJETOS
                    System.out.println("Tente novamente. ");
                    nota = Float.parseFloat(sc.nextLine());
                }


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

                while(notaProva<0||notaProva>10){//verificando se a nota para avaliacao é valida
                    System.out.println("Nota inválida para avaliação "+codProva+" do(s) aluno(s) "+matAluno+": "+notaProva+".");
                    System.out.println("Tente novamente. ");
                    notaProva = Float.parseFloat(sc.nextLine());
                }

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

                        System.out.println("\t" + "- " + aluno.getNome() + " ");
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

    public void serializarDados(Programa programa, Scanner sc) throws IOException{
        System.out.println("Digite o nome do arquivo");
                String arqnome= sc.nextLine();
                arqnome=arqnome+".dat";
                FileOutputStream file= new FileOutputStream(arqnome);
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(programa);
                out.close();
                System.out.println("Arquivo serializado como: "+arqnome);
    }

    public void carregarArquivos(Programa programa, Scanner sc){
        System.out.println("Digite o nome do arquivo");
                String arqnome= sc.nextLine();
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
    }























    

    public void relatorio(Programa programa, Scanner sc){
        System.out.println("Qual relatório você gostaria de ver");
                System.out.println("1- Pauta final de disciplina");
                System.out.println("2- Estatísticas por disciplina");
                System.out.println("3- Estatísticas por avaliação");
                int aux = Integer.parseInt(sc.nextLine());
                Disciplina disciplina;
               // CursoPos cursop;
                Curso cursog;
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
                          for(Map.Entry<Integer,Curso> me2 : programa.getCursos().entrySet()){
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
                    
                          //parte inutilizada, cursos de graduação e pos foram juntas

                    // cursop=mestrado;
                    // if(cursop.getListDisciplinas().containsKey(disciplina.getCodigo())){
                    //     for(Map.Entry<Integer, Aluno> me : disciplina.getListAluno().entrySet()){
                    //         aluno=me.getValue();
                    //         //PROCURANDO O ALUNO UM A UM DENTRO DO CURSO
                    //         if(cursop.getListAlunos().containsKey(aluno.getMatricula())){
                    //             total++;
                    //             media+=disciplina.getNotaf(aluno.getMatricula());
                    //             div++;
                    //             if(disciplina.getNotaf(aluno.getMatricula())>=5.0){
                    //             passou++;
                    //             }
                    //         }
                    //     }
                    //     media/=div;
                        
                    //     System.out.println("\t-"+cursop.getNome()+" media de notas finais: "+media+" ,percentual de alunos aprovados: "+((passou*100)/total)+"%;");
                    // }
                    // cursop=doutorado;
                    // if(cursop.getListDisciplinas().containsKey(disciplina.getCodigo())){
                    //     for(Map.Entry<Integer, Aluno> me : disciplina.getListAluno().entrySet()){
                    //         aluno=me.getValue();

                    //         //PROCURANDO O ALUNO UM A UM DENTRO DO CURSO
                    //         if(cursop.getListAlunos().containsKey(aluno.getMatricula())){
                    //             total++;
                    //             media+=disciplina.getNotaf(aluno.getMatricula());
                    //             div++;
                    //             if(disciplina.getNotaf(aluno.getMatricula())>=5.0){
                    //             passou++;
                    //             }
                    //         }
                    //     }
                    //     media/=div;
                        
                    //     System.out.println("\t-"+cursop.getNome()+" media de notas finais: "+media+" ,percentual de alunos aprovados: "+((passou*100)/total)+"%;");
                    //}

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
    











}
