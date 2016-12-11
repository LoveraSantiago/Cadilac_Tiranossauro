package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoQuadrante;

abstract class QuadrantePai implements TipoQuadrante{

    protected float ptOrigemX;
    protected float ptOrigemY;
    protected float ptX;
    protected float ptY;

    public void setPontos(float ptOrigemX, float ptOrigemY, float ptX, float ptY) {
        this.ptOrigemX = ptOrigemX;
        this.ptOrigemY = ptOrigemY;
        this.ptX = ptX;
        this.ptY = ptY;
    }

    public void setPontos(Vector2 origem, Vector2 pt){
        setPontos(origem.x, origem.y, pt.x, pt.y);
    }

    abstract int getMeuQuadrante();
}
