package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public final class Pontos {

    private final Vector2 pontoTemp;

    private final List<Float> listaX;
    private final List<Float> listaY;

    private int contador;
    private float atualX;
    private float atualY;

    public Pontos() {
        this.listaX = new ArrayList<Float>();
        this.listaY = new ArrayList<Float>();

        this.pontoTemp = new Vector2();
    }

    public void addPontos(float x, float y){
        this.listaX.add(x);
        this.listaY.add(y);
    }

    public boolean temPonto(){
        return  this.listaX.size() > 0;
    }

    public Vector2 consumirPonto(){
        this.pontoTemp.set(this.listaX.get(0), this.listaY.get(0));
        limparPrimeiraPosicao();
        return this.pontoTemp;
    }

    private void limparPrimeiraPosicao(){
        this.listaX.remove(0);
        this.listaY.remove(0);
    }

    public void limparPontos(){
        this.listaY.clear();
        this.listaX.clear();
    }

    public int getQtdPontos(){
        return this.listaX.size();
    }

    public void otimizarPontos(){
        this.atualX = this.listaX.get(0);
        this.atualY = this.listaY.get(0);

        this.contador = 1;
        while(this.contador < this.listaX.size() - 1){
            if(calcularHipotenusa() < 1){
                this.listaX.remove(this.contador);
                this.listaY.remove(this.contador);
            }
            else{
                this.atualX = this.listaX.get(this.contador);
                this.atualY = this.listaY.get(this.contador);
                this.contador++;
            }
        }
    }

    private double calcularHipotenusa(){
        return Math.sqrt(Math.pow(this.listaX.get(this.contador) - this.atualX, 2) + Math.pow(this.listaY.get(this.contador) - this.atualY, 2));
    }
}
