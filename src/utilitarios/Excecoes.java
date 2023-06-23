package utilitarios;

public class Excecoes extends Exception {    
    static void disclipineExists() {
        //O mesmo código foi usado para dois cursos diferentes.
    }

    static void studentExists() {
        //A mesma matrícula foi usada para dois alunos diferentes.
    }

    static void disciplineCodeError() {
        //Código de disciplina usado na planilha de avaliações ou na planilha de alunos não foi definido na planilha de disciplinas.
    }

    static void avaliantionWeightError() {
        //Peso de uma avaliação é um número negativo ou zero.
    }

    static void avaliationTypeError() {
        //Tipo de uma avaliação não é nem ‘T’ nem ‘P’.
    }

    static void groupSizeSet() {
        //Tamanho máximo de grupo foi especificado para prova.
    }

    static void groupSizeError() {
        //Tamanho máximo de grupo é um número negativo ou zero.
    }

    static void groupTypeError() {
        //Tipo de um aluno não é nem ‘G’ nem ‘P’.
    }

    static void courseExists() {
        //Código de curso especificado para um aluno de graduação não foi definido na planilha de cursos.
    }

    static void courseTypeError() {
        //Código de curso especificado para um aluno de pós-graduação não é nem ‘M’ nem ‘D’.
    }

    static void avaliationExists() {
        //Código de avaliação usado na planilha de notas não foi definido na planilha de avaliações.
    }

    static void studentNotFound() {
        //Matrícula de aluno usada na planilha de notas não foi definida na planilha de alunos.
    }

    static void avaliationGradeError() {
        //Nota na planilha de notas é um número negativo ou maior do que 10.
    }

    static void disciplineDonstHasAvaliation() {
        //Disciplina não possui nenhuma avaliação cadastrada.
    }

    static void avaliationStudentError() {
        //A planilha de notas contém nota de um aluno em uma avaliação de uma disciplina que ele não está matriculado.
    }

    static void avaliationError() {
        //A planilha de notas contém mais de uma nota para o mesmo aluno e a mesma avaliação, seja numa prova,seja num grupo de trabalho.
    }
}
