package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoParseavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;

public final class Corredor2 implements TipoParseavel, TipoDesenhavel{

    private final Lataria lataria;
    private final CalculadorAngulo_Corredor calcAngulo;

    private Body corredor;

    public Corredor2() {
        this.lataria = new Lataria();
        this.corredor = meTransforme(this.lataria);
        this.calcAngulo = new CalculadorAngulo_Corredor(this.corredor);

        this.lataria.setCorredor(this.corredor);
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.calcAngulo.atualizar();
        this.lataria.setPosicao(this.corredor);
        this.lataria.meDesenhar(null);
    }

    public final void setPtFuturoProj(float x, float y){
        this.calcAngulo.calcularAngulo(x, y);
    }

    public final void setPtFuturoProj(Vector2 ptFuturo){
        setPtFuturoProj(ptFuturo.x, ptFuturo.y);
    }

    @Override
    public Body meTransforme(Object lataria) {
        return new ParserToBody_Corredor().meTransforme(lataria);
    }

    public Vector2 getPosicaoJogo(){
        return this.corredor.getPosition();
    }
}
