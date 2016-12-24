package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public final class Pontos {

    private final Vector2 pontoTemp;

    private final List<Float> listaX;
    private final List<Float> listaY;

    private final Otimizador otimizador;
    private final Rotacionador rotacionador;

    public Pontos() {
        this.listaX = new ArrayList<Float>();
        this.listaY = new ArrayList<Float>();

        this.pontoTemp = new Vector2();
        this.otimizador = new Otimizador(this.listaX, this.listaY);
        this.rotacionador = new Rotacionador(this.listaX, this.listaY);
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

    public void prepararPontos(){
        this.otimizador.otimizarPontos();
    }

}
