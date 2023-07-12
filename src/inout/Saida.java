package inout;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import base.Aluno;
import base.Avaliacao;
import base.Curso;
import base.Disciplina;
import base.Programa;
import utilitarios.Funcoes;

public class Saida {

    static class PrioridadeComparator1 implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            String[] parts1 = s1.split(";");
            String[] parts2 = s2.split(";");

            // Obtém os valores de acordo com a ordem de prioridade
            String valor1 = parts1[1]; // Prioridade 1
            String valor2 = parts2[1];
           
            return valor1.compareTo(valor2);
        }
    }

    static class PrioridadeComparator2 implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            String[] parts1 = s1.split(";");
            String[] parts2 = s2.split(";");

            // Obtém os valores de acordo com a ordem de prioridade
            String valor1 = parts1[0]; // Prioridade 1
            String valor2 = parts2[0];

            String valor3 = parts1[3]; // Prioridade 2
            String valor4 = parts2[3];

            String valor5 = parts1[2]; // Prioridade 3
            String valor6 = parts2[2];

            // Compara os valores de acordo com a ordem de prioridade
            int comparacao = valor1.compareTo(valor2);
            if (comparacao != 0) {
                return comparacao;
            }

            comparacao = valor4.compareTo(valor3);
            if (comparacao != 0) {
                return comparacao;
            }

            return valor5.compareTo(valor6);
        }
    }

    static class PrioridadeComparator3 implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String[] parts1 = s1.split(";");
            String[] parts2 = s2.split(";");

            // Obtém os valores de acordo com a ordem de prioridade
            String valor1 = parts1[0]; // Prioridade 1
            String valor2 = parts2[0];

            // Compara os valores de acordo com a ordem de prioridade
            int comparacao = valor1.compareTo(valor2);
            if (comparacao != 0) {
                return comparacao;
            }
            Date valor3;
            Date valor4;
            
            try {
                valor3 = sdf.parse(parts1[3]);  //Prioridade 2
                valor4 = sdf.parse(parts2[3]);
                comparacao = valor3.compareTo(valor4);
                return comparacao;
            } catch (ParseException e) {
                System.out.println("Erro de formatação.");
            }

            return comparacao;
        }
    }
static class PrioridadeComparatorP implements Comparator<Avaliacao> {

        @Override
        public int compare(Avaliacao s1, Avaliacao s2) {
           

            Date valor3;
            Date valor4;
            
           
                valor3 = s1.getData();  //Prioridade 2
                valor4 =s2.getData();
                int comparacao = valor3.compareTo(valor4);
                return comparacao;
           
          
        }
    }

    public void relatorio1(Programa programa, Funcoes funcoes) {
        // variaveis inicializadas
        Disciplina disciplina = null;
        Aluno aluno = null;
        float notap = 0, notaf = 0;
        String string = "";
        int aux = 0;
        DecimalFormat df= new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        for (Map.Entry<String, Disciplina> dis : programa.getDisciplinas().entrySet()) {
            disciplina = dis.getValue();
            disciplina.calculaNotaF();
            string = "";
            try (BufferedWriter bf = new BufferedWriter(new FileWriter("1-pauta-" + disciplina.getCodigo() + ".csv"))) {// abre e cria o arquivo e o buffer de escrita
                // escrevendo o cabeçalho
                LinkedList<Avaliacao> ava = new LinkedList<>();
                bf.write("Matrícula;Aluno;");
               
                if (!disciplina.getListAvaliacoes().isEmpty()) {// escreve as provas
                    for (Map.Entry<String, Avaliacao> a : disciplina.getListAvaliacoes().entrySet()) {
                        ava.add(a.getValue());
                    }
                }
                
                Collections.sort(ava,new PrioridadeComparatorP());
                
                for(Avaliacao p:ava){
                    bf.write(p.getCodigo()+";");
                }
                bf.write("Média Parcial;Prova Final;Média Final");// resto do cabeçalho
                bf.newLine();
                for (Map.Entry<Double, Aluno> me : disciplina.getListAluno().entrySet()) {// acessa os alunos presentes na disciplina
                    aluno = me.getValue();// associa o aluno a uma variavel
                    notap = notaf = 0;// zera as variaveis
                    notap = disciplina.getNotap(aluno.getMatricula());
                    notaf = disciplina.getNotaf(aluno.getMatricula());
                    string += df.format(aluno.getMatricula()) + ";" + aluno.getNome() + ";";// coloca as informações dos alunos a uma unica string string

                    if (!disciplina.getListAvaliacoes().isEmpty()) {
                       for(Avaliacao p:ava){
                            string += String.format("%.2f", p.getNota(aluno.getMatricula())) + ";";// coloca as provas na string
                        }
                    }
                    string += String.format("%.2f", notap) + ";";// adiciona a media parcial na string

                    if (notap < 7) {// verifica se o aluno passou direto
                        if (disciplina.temFinal()) {
                            if (disciplina.getProvaF().getListAluno().containsKey(aluno.getMatricula())) {// se o aluno fez a prova
                                string += String.format("%.2f", disciplina.getProvaF().getNota(aluno.getMatricula()))+ ";";// pega a da prova e coloca na string
                                string += String.format("%.2f", notaf);// adiciona a nota final na string
                            } else {
                                notaf = (notap / 2);// caso nao tenha feito a prova, será considerado como 0
                                string += 0 + ";" + String.format("%.2f", notaf);// adiciona na string
                            }
                        }
                    } else {

                        string += "-;" + String.format("%.2f", notap);// caso tenha passado direto a nota da final não existe
                    }
                    string += "&";// separação de linha
                }
                String[] alunos = string.split("&");// adiciona um token no final de cada aluno para que seja separada em um vetor de strings

                aux = alunos.length;// pega o tamanho das strings


                Arrays.sort(alunos,new PrioridadeComparator1());// organiza o vetor em ordem alfabetica

                for (int i = 0; i < aux; i++) {// adiciona os alunos de acordo com o nome
                    bf.write(alunos[i]);// adiciona o aluno no arquivo
                    bf.newLine();// salta uma linha
                }
            }

            catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public void relatorio2(Programa programa) {
        // variaveis
        Disciplina disciplina = null;
        Curso curso = null;
        String string = "";
        DecimalFormat df= new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("2-disciplinas.csv"))) {// abre/cria o arquivo e o buffer de escrita
            bf.write("Código;Disciplina;Curso;Média;% Aprovados");// escreve o cabeçalho
            bf.newLine();
            for (Map.Entry<String, Disciplina> dis : programa.getDisciplinas().entrySet()) {// pega todas as disciplinas
                disciplina = dis.getValue();// atribui a uma variavel
                disciplina.calculaNotaF();// calcula a nota final
                for (Map.Entry<Integer, Curso> me2 : programa.getCursos().entrySet()) {// pega todos os cursos
                   
                    curso = me2.getValue();// atribui a uma variavel

                    if (curso.getListDisciplinas().containsKey(disciplina.getCodigo())) {// verifica se a disciplina está presente nesse curso
                       
                        curso.calculaMediasCurso(disciplina.getCodigo());//faz os calculos
                        
                        string += (disciplina.getCodigo() + ";" + disciplina.getNome() + ";" + curso.getNome() + ";"+ String.format("%.2f", curso.getMedia(disciplina.getCodigo())) + ";" + String.format("%.1f", curso.getPassou(disciplina.getCodigo())) + "%" + "&");// escreve na string
                        
                    }
                }

            }

            String[] linhas = string.split("&");

            int aux = linhas.length;

            Arrays.sort(linhas, new PrioridadeComparator2());

            for (int i = 0; i < aux; i++) {
                bf.write(linhas[i]);
                bf.newLine();
                
            }

        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void relatorio3(Programa programa, SimpleDateFormat sdf) {// relatoria de avaliacao
        Avaliacao ava = null;
        Disciplina disciplina = null;
        String string = "";
        DecimalFormat df= new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
       
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("3-avaliacoes.csv"))) {
            bf.write("Disciplina;Código;Avaliação;Data;Média");
            string = "";
            bf.newLine();
            for (Map.Entry<String, Disciplina> me : programa.getDisciplinas().entrySet()) {
                disciplina = me.getValue();
                for (Map.Entry<String, Avaliacao> me2 : disciplina.getListAvaliacoes().entrySet()) {
                    ava = me2.getValue();// atribui avaliação
                    string += disciplina.getCodigo() + ";" + ava.getCodigo() + ";" + ava.getNome() + ";" + sdf.format(ava.getData()) + ";" + String.format("%.2f", ava.mediaAvaliativa()) + "&";

                }
                
                
            }
            String[] strings = string.split("&");
            int aux = strings.length;// pega o tamanho das strings
            Arrays.sort(strings, new PrioridadeComparator3());
            for (int i = 0; i < aux - 1; i++) {// adiciona a string dos alunos uma a uma no arquivo, e salta para a proxima linha
                                               
                bf.write(strings[i]);// adiciona
                bf.newLine();// salta
            }
            bf.write(strings[aux - 1]);// adiciona o ultimo aluno, sem saltar uma linha a mais
            bf.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
