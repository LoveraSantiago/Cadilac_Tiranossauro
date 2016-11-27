package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.physics.box2d.Body;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoParseavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;

public final class Corredor2 implements TipoParseavel, TipoDesenhavel{

    private Lataria lataria;

    private Body corredor;

    public Corredor2() {
        this.lataria = new Lataria();
        this.corredor = meTransforme(this.lataria);
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.lataria.setPosicao(corredor.getPosition());
        this.lataria.meDesenhar(null);
    }

    @Override
    public Body meTransforme(Object lataria) {
        return new ParserToBody_Corredor().meTransforme(lataria);
    }
}
