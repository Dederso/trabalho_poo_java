package base;

import java.util.Date;

public class Prova extends Avaliacao {


    public Prova(Disciplina disciplina, String codigo, String nome, float peso, Date data) {
        super(disciplina, codigo, nome, data);
        super.peso = peso;
     
    }


}
