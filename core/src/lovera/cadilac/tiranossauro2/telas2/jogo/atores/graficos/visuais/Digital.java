package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.ImgUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;

import static lovera.cadilac.tiranossauro2.utils.imagens.Img.DIGITAL;

public class Digital implements TipoDesenhavel{

    private final Sprite digital;

    private final SpriteBatch spriteBatch;

    private final CameraManager cameraManager;

    public Digital() {
        this.digital = ImgUnico.getInstancia().getImgLeitor().getImg(DIGITAL);

        this.spriteBatch = SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch();
        this.cameraManager = CameraUnico.getCameraManager();
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.cameraManager.updateSpriteBatch_CamJogo();
        this.spriteBatch.begin();
        System.out.println("chamado");
        this.spriteBatch.draw(this.digital, 1, 1, 35, 50);
        this.spriteBatch.end();
    }
}
