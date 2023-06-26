package base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Trabalho extends Avaliacao{

    private int max;
    private float peso;
    private int grupos;
    private HashMap<Integer,Float> notasT=new HashMap<>();

    public Trabalho(Disciplina disciplina, String codigo, String nome, float peso, Date data, int max) {
        super(disciplina, codigo, nome, data);
        this.peso=peso;
        this.max=max;
    }

    public int getMax() {
        return max;
    }
  
    public float getPeso(){
        return this.peso;
    }
    
    public int getGrupos(){
        return grupos;
    }
    public void addnotasT(Float nota){
        grupos++;
        notasT.put(grupos, nota);
    }
    public HashMap<Integer,Float> getNotasT(){
        return notasT;
    }
    public float mediaT(){
       float media=0;
       
       for(Map.Entry<Integer,Float> me: notasT.entrySet()){
            media+=me.getValue();          
        }
        media/=this.grupos;
       return media;
    }
}


