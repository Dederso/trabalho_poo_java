package base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Trabalho extends Avaliacao{

    private int max;
    private int grupos;
    private HashMap<Integer,Float> notasT=new HashMap<>();

    public Trabalho(Disciplina disciplina, String codigo, String nome, float peso, Date data, int max) {
        super(disciplina, codigo, nome, data);
        super.peso=peso;
        this.max=max;
    }

    public int getMax() {
        return max;
    }
  
    
    public int getGrupos(){
        return grupos;
    }
    @Override
    public void addnotasT(Float nota){
        grupos++;
        notasT.put(grupos, nota);
    }

    public HashMap<Integer,Float> getNotasT(){
        return notasT;
    }

    @Override
    public float mediaAvaliativa(){
       float media=0;
       System.out.println(super.getCodigo());
       for(Map.Entry<Integer,Float> me: notasT.entrySet()){
            media+=me.getValue();     
            System.out.println(media);     
        }
        media/=this.grupos;
        System.out.println(media+" "+grupos);
       return media;
    }
}


