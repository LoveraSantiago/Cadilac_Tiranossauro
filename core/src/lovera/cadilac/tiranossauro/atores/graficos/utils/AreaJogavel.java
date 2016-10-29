package lovera.cadilac.tiranossauro.atores.graficos.utils;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro.contratos.Desenhavel;

public class AreaJogavel implements Desenhavel, Disposable{

    private final TextureAtlas textureAtlas;
    private final NinePatch ninePatch;

    private float x;
    private float y;
    private float w;
    private float h;

    public AreaJogavel() {
        this.textureAtlas = new TextureAtlas("ninepatches/ninepatches_areajogavel.atlas");
        this.ninePatch = this.textureAtlas.createPatch("area_jogavel2");
    }

    @Override
    public void meDesenhar(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        this.ninePatch.draw(spriteBatch, this.x, this.y, this.w, this.h);
        spriteBatch.end();
    }

    public void setarTamanhoArea(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setarTamanhoEDesenhar(SpriteBatch spriteBatch, float x, float y, float w, float h){
        setarTamanhoArea(x, y, w, h);
        meDesenhar(spriteBatch);
    }

    @Override
    public void dispose() {
        this.textureAtlas.dispose();
    }
}
