package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.eixocartesiano;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.gerais.ImgLeitor;
import lovera.cadilac.tiranossauro2.telas2.gerais.NinePatchLeitor;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.WrapperPosicaoJogador;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.ImgUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.NinePatchUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;
import lovera.cadilac.tiranossauro2.utils.imagens.Img;

import static lovera.cadilac.tiranossauro2.utils.imagens.Img.TRACO_X;
import static lovera.cadilac.tiranossauro2.utils.imagens.Img.TRACO_Y;
import static lovera.cadilac.tiranossauro2.utils.imagens.Img9Patch.EIXO_X;
import static lovera.cadilac.tiranossauro2.utils.imagens.Img9Patch.EIXO_Y;

public final class EixoCartesiano implements TipoDesenhavel{

    private final NinePatch eixoX;
    private final NinePatch eixoY;

    private final Sprite tracoX;
    private final Sprite tracoY;

    private final SpriteBatch spriteBatch;

    private final CameraManager cameraManager;
    private final WrapperPosicaoJogador posJogador;

    private Rectangle areaTemp;

    public EixoCartesiano() {
        NinePatchLeitor ninePatchLeitor = NinePatchUnico.getInstancia().getNinePatchLeitor();
        this.eixoX = ninePatchLeitor.getNinePatch(EIXO_X);
        this.eixoX.scale(.1f, .1f);

        this.eixoY = ninePatchLeitor.getNinePatch(EIXO_Y);
        this.eixoY.scale(.1f, .1f);

        ImgLeitor imgLeitor = ImgUnico.getInstancia().getImgLeitor();
        this.tracoX = imgLeitor.getImg(TRACO_X);
        this.tracoX.scale(.01f);
        this.tracoY = imgLeitor.getImg(TRACO_Y);

        this.spriteBatch = SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch();

        this.cameraManager = CameraUnico.getCameraManager();
        this.posJogador = CorredorUnico.getInstancia().getCorredorManager().getCorredorP().getWrapperPosicaoJogador();
    }

    public void configurarEixo(){
        this.areaTemp = this.cameraManager.getArea_CamProj();
    }


    @Override
    public void meDesenhar(Object objeto) {
        this.spriteBatch.begin();
        this.eixoX.draw(this.spriteBatch, this.areaTemp.getX(), this.posJogador.getY() - (this.eixoX.getTotalHeight() / 2), this.areaTemp.getWidth(), 2);
        this.eixoY.draw(this.spriteBatch, this.posJogador.getX() - (this.eixoY.getTotalWidth() / 2), this.areaTemp.getY(), 2, this.areaTemp.getHeight());
        this.spriteBatch.draw(this.tracoX, 2, 2);
        this.spriteBatch.draw(this.tracoX, 10, 10);

        this.spriteBatch.end();
    }
}
