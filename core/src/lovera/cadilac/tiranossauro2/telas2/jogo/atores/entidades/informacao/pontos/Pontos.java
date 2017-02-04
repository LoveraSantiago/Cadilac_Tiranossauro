package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class Pontos {

    private final Vector2 pontoTemp;

    private final LinkedList<Float> listaX;
    private final LinkedList<Float> listaY;

    private final Otimizador otimizador;
    private final RotacionadorDePontos rotacionador;

    public Pontos() {
        this.listaX = new LinkedList<Float>();
        this.listaY = new LinkedList<Float>();

        this.pontoTemp = new Vector2();
        this.otimizador = new Otimizador(this.listaX, this.listaY);
        this.rotacionador = new RotacionadorDePontos(this.listaX, this.listaY);
    }

    public void addPontos(float x, float y){
        this.listaX.add(x);
        this.listaY.add(y);
    }

    public boolean temPonto(){
        return  this.listaX.size() > 0;
    }

    public Vector2 consumirPonto(){
        this.pontoTemp.set(this.listaX.remove(), this.listaY.remove());
        return this.pontoTemp;
    }

    public void limparPontos(){
        this.listaY.clear();
        this.listaX.clear();
    }

    public int getQtdPontos(){
        return this.listaX.size();
    }

    public Vector2 getProximoPonto(){
        return this.pontoTemp.set(this.listaX.getFirst(), this.listaY.getFirst());
    }

    public void prepararPontos(){
        this.otimizador.otimizarPontos();
        this.rotacionador.rotacionarPontos();
    }
}
