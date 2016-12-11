package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoQuadrante;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Arredondador;

abstract class QuadrantePai implements TipoQuadrante{

    protected final static Arredondador arredondador;

    protected float ptOrigemX;
    protected float ptOrigemY;
    protected float ptX;
    protected float ptY;

    static {
        arredondador = new Arredondador();
    }

    public QuadrantePai() {
    }

    @Override
    public void setPontos(float ptOrigemX, float ptOrigemY, float ptX, float ptY) {
        this.ptOrigemX = ptOrigemX;
        this.ptOrigemY = ptOrigemY;
        this.ptX = ptX;
        this.ptY = ptY;
    }
}
