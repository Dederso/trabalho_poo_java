package aplicacao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import base.CursoPos;
import base.Programa;
import inout.Leitura;
import inout.Saida;
import utilitarios.Funcoes;

public class App {

    public static void main(String[] args) throws Exception,IOException{

        Locale.setDefault(new Locale("pt", "BR"));
        Programa programa = new Programa();
        CursoPos mestrado = new CursoPos(programa.numMestrado, "Mestrado");
        CursoPos doutorado = new CursoPos(programa.numDoutorado, "Doutorado");
        programa.adicionaCurso(doutorado);
        programa.adicionaCurso(mestrado);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");// formatacao da data de avaliacoes

        Funcoes funcoes = new Funcoes();

        Leitura leitura = new Leitura();
        Saida saida = new Saida();
        
        
        
        String pathAluno = "";
        String pathCursos = "";
        String pathAvaliacoes = "";
        String pathNotas = "";
        String pathDisciplinas = "";
        
       
        int i = 0;

       
       
        boolean readOnly = false;
        boolean writeOnly = false;
       
        try{
            // Verifica os argumentos de linha de comando
            for (i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.equals("-c")) {
                    i++;
                    pathCursos = args[i];
                } else if (arg.equals("-d")) {
                    i++;
                    pathDisciplinas = args[i];
                } else if (arg.equals("-p")) {
                    i++;
                    pathAvaliacoes = args[i];
                } else if (arg.equals("-a")) {
                    i++;
                    pathAluno = args[i];
                } else if (arg.equals("-n")) {
                    i++;
                    pathNotas = args[i];
                } else if (arg.equals("--read-only")) {
                    readOnly = true;
                } else if (arg.equals("--write-only")) {
                    writeOnly = true;
                }
            }

            

            if (readOnly) {
                //le os arquivos
                leitura.lerCurso(pathCursos, programa, funcoes);
                leitura.lerDisciplinas(pathDisciplinas, programa, funcoes);
                leitura.lerAvaliacoes(pathAvaliacoes, programa, funcoes, sdf);
                leitura.lerAlunos(pathAluno, programa, funcoes);
                leitura.lerNotas(pathNotas, programa, funcoes);

                funcoes.serializarDados(programa);
                    

            } else if (writeOnly) {

                
                programa=funcoes.carregarArquivos();
                
                saida.relatorio1(programa, funcoes);
                saida.relatorio2(programa);
                saida.relatorio3(programa, sdf);
            
            } else {
                //le os arquivos
                leitura.lerCurso(pathCursos, programa, funcoes);
                leitura.lerDisciplinas(pathDisciplinas, programa, funcoes);
                leitura.lerAvaliacoes(pathAvaliacoes, programa, funcoes, sdf);
                leitura.lerAlunos(pathAluno, programa, funcoes);
                leitura.lerNotas(pathNotas, programa, funcoes);
                //escreve os arquivos
                saida.relatorio1(programa, funcoes);
                saida.relatorio2(programa);
                saida.relatorio3(programa, sdf);
            }
        }
        catch(IOException e){
            System.out.println("Erro de I/O.");
        }
        catch(NumberFormatException e){
            System.out.println("Erro de formatação.");
        }
        catch(ParseException e){
            System.out.println("Erro de formatação.");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }   
}
  
