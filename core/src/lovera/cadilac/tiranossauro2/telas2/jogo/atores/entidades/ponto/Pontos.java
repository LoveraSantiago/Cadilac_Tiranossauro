package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ponto;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public final class Pontos {

    private final Vector2 pontoTemp;

    private final List<Float> listaX;
    private final List<Float> listaY;

    public Pontos() {
        this.listaX = new ArrayList<Float>();
        this.listaY = new ArrayList<Float>();

        this.pontoTemp = new Vector2();
    }

    public void addPontos(float x, float y){
        this.listaX.add(x);
        this.listaY.add(y);
    }

    public void addPontos(Vector2 posicao){
        addPontos(posicao.x, posicao.y);
    }

    public boolean temProximoPonto(){
        return  this.listaX.size() > 0;
    }

    public Vector2 consumirProximoPonto(){
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
}
