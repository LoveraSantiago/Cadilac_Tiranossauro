package lovera.cadilac.tiranossauro2.telas2.jogo.atores.ponto;

import com.badlogic.gdx.utils.Pool;

public class PoolDePontos extends Pool<Ponto>{

    @Override
    protected Ponto newObject() {
        return new Ponto();
    }
}
