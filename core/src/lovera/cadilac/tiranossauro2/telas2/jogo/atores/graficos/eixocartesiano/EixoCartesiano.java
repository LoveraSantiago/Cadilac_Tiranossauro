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

    private float posTracoY;
    private float posTracoX;

    public EixoCartesiano() {
        NinePatchLeitor ninePatchLeitor = NinePatchUnico.getInstancia().getNinePatchLeitor();
        this.eixoX = ninePatchLeitor.getNinePatch(EIXO_X);
        this.eixoX.scale(.1f, .1f);

        this.eixoY = ninePatchLeitor.getNinePatch(EIXO_Y);
        this.eixoY.scale(.1f, .1f);

        ImgLeitor imgLeitor = ImgUnico.getInstancia().getImgLeitor();
        this.tracoX = imgLeitor.getImg(TRACO_X);


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
        this.eixoX.draw(this.spriteBatch, this.areaTemp.getX(), this.posJogador.getY() - .5f, this.areaTemp.getWidth(), 1);
        this.eixoY.draw(this.spriteBatch, this.posJogador.getX() - .5f, this.areaTemp.getY(), 1, this.areaTemp.getHeight());

        this.posTracoY = this.posJogador.getY() - .5f;
        this.spriteBatch.draw(this.tracoX, this.posJogador.getX() - 7.5f, this.posTracoY, .5f, 1);
        this.spriteBatch.draw(this.tracoX, this.posJogador.getX() - 5, this.posTracoY, .5f, 1);
        this.spriteBatch.draw(this.tracoX, this.posJogador.getX() - 2.5f, this.posTracoY, .5f, 1);
        this.spriteBatch.draw(this.tracoX, this.posJogador.getX() + 2.5f, this.posTracoY, .5f, 1);
        this.spriteBatch.draw(this.tracoX, this.posJogador.getX() + 5, this.posTracoY, .5f, 1);

        this.posTracoX = this.posJogador.getX() - .5f;
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() - 5, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() - 2.5f, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 2.5f, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 5, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 7.5f, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 10, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 12.5f, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 15, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 17.5f, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 20, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 22.5f, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 25, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 27.5f, 1, .5f);
        this.spriteBatch.draw(this.tracoY, this.posTracoX, this.posJogador.getY() + 30, 1, .5f);

        this.spriteBatch.end();
    }
}
