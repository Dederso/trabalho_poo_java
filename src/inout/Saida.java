package inout;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import base.Aluno;
import base.Avaliacao;
import base.Curso;
import base.Disciplina;
import base.Programa;
import base.Prova;
import base.Trabalho;
import utilitarios.Funcoes;

public class Saida {
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

    public void relatorio1(Programa programa, Funcoes funcoes) {
        // variaveis inicializadas
        Disciplina disciplina = null;
        Aluno aluno = null;
        float notap = 0, notaf = 0, media = 0, peso = 0, div = 0;
        String string = "";
        int aux = 0;
        DecimalFormat df= new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        for (Map.Entry<String, Disciplina> dis : programa.getDisciplinas().entrySet()) {
            disciplina = dis.getValue();

            string = "";
            try (BufferedWriter bf = new BufferedWriter(new FileWriter("1-pauta-" + disciplina.getCodigo() + ".csv"))) {// abre e cria o arquivo e o buffer de escrita
                // escrevendo o cabeçalho
                bf.write("Matrícula;Aluno;");
                if (!disciplina.getListProva().isEmpty()) {// escreve as provas
                    for (Map.Entry<String, Prova> p : disciplina.getListProva().entrySet()) {
                        bf.write(p.getValue().getCodigo() + ";");
                    }
                }
                if (!disciplina.getListTrabalho().isEmpty()) {// escreve os trabalhos
                    for (Map.Entry<String, Trabalho> t : disciplina.getListTrabalho().entrySet()) {
                        bf.write(t.getValue().getCodigo() + ";");
                    }
                }
                bf.write("Média Parcial;Prova Final;Média Final");// resto do cabeçalho
                bf.newLine();
                for (Map.Entry<Double, Aluno> me : disciplina.getListAluno().entrySet()) {// acessa os alunos presentes na disciplina
                    aluno = me.getValue();// associa o aluno a uma variavel
                    notap = notaf = media = peso = div = 0;// zera as variaveis

                    string += df.format(aluno.getMatricula()) + ";" + aluno.getNome() + ";";// coloca as informações dos alunos a uma unica string string

                    if (!disciplina.getListProva().isEmpty()) {
                        for (Map.Entry<String, Prova> p : disciplina.getListProva().entrySet()) {
                            string += String.format("%.2f", p.getValue().getNota(aluno.getMatricula())) + ";";// coloca as provas na string

                            // pega as informaçoes para calcular a media
                            peso = p.getValue().getPeso();
                            media += (peso * p.getValue().getNota(aluno.getMatricula()));
                            div += peso;

                        }
                    }

                    if (!disciplina.getListTrabalho().isEmpty()) {
                        for (Map.Entry<String, Trabalho> t : disciplina.getListTrabalho().entrySet()) {
                            string += String.format("%.2f", t.getValue().getNota(aluno.getMatricula())) + ";";// coloca os trabalhos na string

                            // pega as informaçoes para calcular a media
                            peso = t.getValue().getPeso();
                            media += (peso * t.getValue().getNota(aluno.getMatricula()));
                            div += peso;
                        }

                    }
                    notap = media / div;// calcula a media parcial
                    string += String.format("%.2f", notap) + ";";// adiciona a media parcial na string

                    if (notap < 7) {// verifica se o aluno passou direto
                        if (disciplina.temFinal()) {
                            if (disciplina.getProvaF().getListAluno().containsKey(aluno.getMatricula())) {// se o aluno fez a prova
                                string += String.format("%.2f", disciplina.getProvaF().getNota(aluno.getMatricula()))
                                        + ";";// pega a da prova e coloca na string
                                notaf = (notap / 2) + (disciplina.getProvaF().getNota(aluno.getMatricula()) / 2);// nota final
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

                String[] nomes = new String[aux];

                for (int i = 0; i < aux; i++) {// adiciona a string dos alunos uma a uma no arquivo, e salta para a proxima linha
                    String[] separa = alunos[i].split(";");// separa a string de cada aluno
                    nomes[i] = separa[1];// adiciona o nome num vetor de strings
                }

                Arrays.sort(nomes);// organiza o vetor em ordem alfabetica

                for (int i = 0; i < aux; i++) {// adiciona os alunos de acordo com o nome
                    for (int j = 0; j < aux; j++) {
                        String[] separa = alunos[j].split(";");// separa os alunos para poder comparar o nome com a ordem
                        if (separa[1].equals(nomes[i])) {// compara o nome de cada aluno com os nomes em ordem
                           
                            bf.write(alunos[j]);// adiciona o aluno no arquivo
                            bf.newLine();// salta uma linha
                        }
                    }
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
        Aluno aluno = null;
        float total = 0, passou = 0;
        float media = 0, div = 0;
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
                        for (Map.Entry<Double, Aluno> me : disciplina.getListAluno().entrySet()) {// pega os alunos da disciplina

                            aluno = me.getValue();// atribui a uma variavel
                            // PROCURANDO O ALUNO UM A UM DENTRO DO CURSO
                            if (curso.getListAlunos().containsKey(aluno.getMatricula())) {// verifica se o aluno faz parte do curso
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

                        string += (disciplina.getCodigo() + ";" + disciplina.getNome() + ";" + curso.getNome() + ";"+ String.format("%.2f", media) + ";" + String.format("%.1f", passou) + "%" + "&");// escreve na string
                        // zera variaveis
                        div = media = 0;
                        total = passou = 0;
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
        Trabalho tra = null;
        DecimalFormat df= new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("3-avaliacoes.csv"))) {
            bf.write("Disciplina;Código;Avaliação;Data;Média");
            string = "";
            bf.newLine();
            for (Map.Entry<String, Disciplina> me : programa.getDisciplinas().entrySet()) {
                disciplina = me.getValue();
                for (Map.Entry<String, Prova> me2 : disciplina.getListProva().entrySet()) {
                    ava = me2.getValue();// atribui avaliação
                    string += disciplina.getCodigo() + ";" + ava.getCodigo() + ";" + ava.getNome() + ";"
                            + sdf.format(ava.getData()) + ";" + String.format("%.2f", ava.mediaAvaliativa()) + "&";

                }
                for (Map.Entry<String, Trabalho> me2 : disciplina.getListTrabalho().entrySet()) {
                    tra = me2.getValue();// atribui avaliação
                    string += disciplina.getCodigo() + ";" + tra.getCodigo() + ";" + tra.getNome() + ";"
                            + sdf.format(tra.getData()) + ";" + String.format("%.2f", tra.mediaT()) + "&";

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
