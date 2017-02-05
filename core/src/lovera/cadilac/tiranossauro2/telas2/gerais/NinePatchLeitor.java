package lovera.cadilac.tiranossauro2.telas2.gerais;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.utils.imagens.Img9Patch;

public class NinePatchLeitor implements Disposable{

    private final TextureAtlas textureAtlas;

    public NinePatchLeitor() {
        this.textureAtlas = new TextureAtlas("ninepatches/ninepatches_pasta.atlas");
    }

    public NinePatch getNinePatch(Img9Patch img){
        return this.textureAtlas.createPatch(img.getNomeImg());
    }

    @Override
    public void dispose() {
        this.textureAtlas.dispose();
    }
}
