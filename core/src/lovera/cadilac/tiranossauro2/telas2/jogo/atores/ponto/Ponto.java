package lovera.cadilac.tiranossauro2.telas2.jogo.atores.ponto;

import com.badlogic.gdx.utils.Pool;

public final class Ponto implements Pool.Poolable{

    private float x;
    private float y;

    public Ponto init(float x, float y){
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public void reset() {
        this.x = 0;
        this.y = 0;
    }
}
