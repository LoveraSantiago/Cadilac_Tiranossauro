package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.eixocartesiano;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.gerais.NinePatchLeitor;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.NinePatchUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;

import static lovera.cadilac.tiranossauro2.utils.imagens.Img9Patch.EIXO_X;
import static lovera.cadilac.tiranossauro2.utils.imagens.Img9Patch.EIXO_Y;

public final class EixoCartesiano implements TipoDesenhavel{

    private final NinePatch eixoX;
    private final NinePatch eixoY;

    private final SpriteBatch spriteBatch;

    public EixoCartesiano() {
        NinePatchLeitor ninePatchLeitor = NinePatchUnico.getInstancia().getNinePatchLeitor();
        this.eixoX = ninePatchLeitor.getNinePatch(EIXO_X);
        this.eixoY = ninePatchLeitor.getNinePatch(EIXO_Y);

        this.spriteBatch = SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch();
    }


    @Override
    public void meDesenhar(Object objeto) {
        this.spriteBatch.begin();
        this.eixoX.draw(this.spriteBatch, 0, 0, 10, 10);
        this.eixoY.draw(this.spriteBatch, 0, 0, 10, 10);
        this.spriteBatch.end();
    }
}
