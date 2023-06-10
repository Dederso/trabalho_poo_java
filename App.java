
import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        //inicializando o programa onde sao guardadas todas as informacoes
        Programa programa = new Programa();
        Funcoes funcoes=new Funcoes();
        int i = 50;//inteiro fora do loop para que não haja conflito caso haja erro 
        
        Scanner sc = new Scanner(System.in);//abre um scanner para entradas

        // criando e armazenando os cursos de pos-graduacao, os códigos 1 e 2 serão reservados aos cursos de pos
        CursoPos mestrado = new CursoPos(1,"Mestrado");
        CursoPos doutorado = new CursoPos(2,"Doutorado");
        programa.adicionaCurso(mestrado);
        programa.adicionaCurso(doutorado);
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//formatacao da data de avaliacoes
        
        while (i != 0) {//loop do menu
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
           
           try{i = Integer.parseInt(sc.nextLine());}//pega do usuário a entrada correspondente a funcao
            
           catch(NumberFormatException e){
            System.out.println("Erro de formatação.\n");
           }
            if (i == 1) {
                // Cadastrar curso
                funcoes.cadastrarCurso(programa,sc);
            }

            if (i == 2) {
                // Cadastrar disciplina
                funcoes.cadastrarDisciplina(programa, sc);
            }

            if (i == 3) {
                // Cadastrar avaliação
                funcoes.cadastrarAvaliacao(programa, sc, sdf);
            }

            if (i == 4) {
                // Cadastrar aluno
                funcoes.cadastrarAluno(programa, sc);
            }

            if (i == 5) {
                // Matricula aluno em disciplina
                funcoes.matricularAlunoDisciplina(programa, sc);
            }

            if (i == 6) {
               //Registra nota do aluno em uma avaliação
               funcoes.registraNotaAluno(programa, sc);
            }

            if (i == 7) {
                // Imprimir dados
                funcoes.imprimirDados(programa);
            }
            if (i == 8) {
                // Serializar dados
                funcoes.serializarDados(programa, sc);
            }
            if(i == 9){
                // Carregar arquivos salvos
                funcoes.carregarArquivos(programa, sc);
            }

            if(i == 10){
              // Imprime relatório
              funcoes.relatorio(programa,sc);
            }
            
            if(i<0&&i>10){
                System.out.println("numero inválido, tente novamente");
            }
            
            if (i == 0) {
                sc.close();
                System.out.println("Programa encerrado.");
                i=0;
            }
            if(i!=0){
                i=50;
            }
        }

    }
}




 // NAO UTILIZADOS   
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


