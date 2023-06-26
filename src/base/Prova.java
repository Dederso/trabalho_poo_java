package base;

import java.util.Date;

public class Prova extends Avaliacao {

    private float peso;

    public Prova(Disciplina disciplina, String codigo, String nome, float peso, Date data) {
        super(disciplina, codigo, nome, data);
        this.peso = peso;
     
    }


    public float getPeso() {
        return peso;
    }


}
