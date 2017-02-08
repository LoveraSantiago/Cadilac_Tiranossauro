package lovera.cadilac.tiranossauro2.telas2.gerais;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.utils.imagens.Img;

public class ImgLeitor implements Disposable{

    private final TextureAtlas textureAtlas;

    public ImgLeitor() {
        this.textureAtlas = new TextureAtlas("imgs/img_pasta.atlas");
    }

    public Sprite getImg(Img img){
        return this.textureAtlas.createSprite(img.getNomeImg());
    }

    @Override
    public void dispose() {
        this.textureAtlas.dispose();
    }
}
