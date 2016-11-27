package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoBody2D;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;

public final class Corredor2 implements TipoBody2D, TipoDesenhavel{

    private Lataria lataria;

    private Body corredor;

    public Corredor2() {
        this.lataria = new Lataria();
        this.corredor = meTransformeEmBody(this.lataria);
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.lataria.setPosicao(corredor.getPosition());
        this.lataria.meDesenhar(null);
    }

    @Override
    public Body meTransformeEmBody(Object lataria) {
        return new ParserToBody_Corredor().meTransformeEmBody(lataria);
    }
}
