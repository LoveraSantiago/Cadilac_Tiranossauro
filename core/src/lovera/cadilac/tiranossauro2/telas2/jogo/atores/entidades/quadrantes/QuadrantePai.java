package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoQuadrante;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Arredondador;

abstract class QuadrantePai implements TipoQuadrante{

    protected final static Arredondador arredondador;

    private Vector2 ptOrigem;
    protected float ptX;
    protected float ptY;

    static {
        arredondador = new Arredondador();
    }

    public QuadrantePai() {
    }

    @Override
    public void setPontos(Vector2 ptOrigem, float ptX, float ptY) {
        this.ptOrigem = ptOrigem;
        this.ptX = arredondador.arredondar(ptX);
        this.ptY = arredondador.arredondar(ptY);
    }

    protected float getPtOrigemX(){
        return this.arredondador.arredondar(this.ptOrigem.x);
    }

    protected float getPtOrigemY(){
        return this.arredondador.arredondar(this.ptOrigem.y);
    }
}
