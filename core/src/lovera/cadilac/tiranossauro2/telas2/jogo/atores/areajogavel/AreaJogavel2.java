package lovera.cadilac.tiranossauro2.telas2.jogo.atores.areajogavel;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.componente.tela.mSpriteBatch;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

public class AreaJogavel2 implements TipoDesenhavel, Disposable {

    private final TextureAtlas textureAtlas;
    private final NinePatch ninePatch;

    private final SpriteBatch spriteBatch;

    private float x;
    private float y;
    private float w;
    private float h;

    public AreaJogavel2() {
        this.textureAtlas = new TextureAtlas("ninepatches/ninepatches_areajogavel.atlas");
        this.ninePatch = this.textureAtlas.createPatch("area_jogavel2");

        this.spriteBatch = mSpriteBatch.getInstancia();
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.spriteBatch.begin();
        this.ninePatch.draw(this.spriteBatch, this.x, this.y, this.w, this.h);
        this.spriteBatch.end();
    }

    public void setarTamanhoArea(float x, float y, float w, float h){
//        this.x = x * AjustadorDeTela2.ESCALA;
//        this.y = y * AjustadorDeTela2.ESCALA;
//        this.w = w * AjustadorDeTela2.ESCALA;
//        this.h = h * AjustadorDeTela2.ESCALA;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setarTamanhoEDesenhar(float x, float y, float w, float h){
        setarTamanhoArea(x, y, w, h);
        meDesenhar(null);
    }

    @Override
    public void dispose() {
        this.textureAtlas.dispose();
    }

}
