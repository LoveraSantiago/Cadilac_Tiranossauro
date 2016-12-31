package lovera.cadilac.tiranossauro2.telas2.jogo.atores.areajogavel;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.GraficosEnum2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;

public final class AreaJogavel2 implements TipoDesenhavel, Disposable {

    private final TextureAtlas textureAtlas;
    private final NinePatch ninePatch;

    private final SpriteBatch spriteBatch;

    private float x;
    private float y;
    private float w;
    private float h;

    private final CameraManager cameraManager;

    private final Vector2 posicaoJogador;

    private Rectangle areaTemp;

    public AreaJogavel2() {
        this.textureAtlas = new TextureAtlas("ninepatches/ninepatches_areajogavel.atlas");
        this.ninePatch = this.textureAtlas.createPatch("area_jogavel2");
        this.ninePatch.scale(.1f, .1f);

        this.spriteBatch = SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch();
        this.cameraManager = CameraUnico.getCameraManager();
        this.posicaoJogador = CorredorUnico.getInstancia().getCorredorManager().getCorredorP().getPosicaoJogo();
    }

    @Override
    public void meDesenhar(Object objeto) {
        this.spriteBatch.begin();
        this.ninePatch.draw(this.spriteBatch, this.x, this.y, this.w, this.h);
        this.spriteBatch.end();
    }

    private void setarTamanhoArea(float x, float y, float w, float h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void configurarAreaJogavel(GraficosEnum2 graficoEnum){
        this.areaTemp = this.cameraManager.getArea_CamProj();

        if(graficoEnum == GraficosEnum2.VETOR || graficoEnum == GraficosEnum2.PARABOLOIDE){
            setarTamanhoArea(this.areaTemp.getX(), this.posicaoJogador.y, this.areaTemp.getWidth(), this.areaTemp.getHeight() - (this.posicaoJogador.y - this.areaTemp.getY()));
        }
        else{
            setarTamanhoArea(this.areaTemp.getX(), this.areaTemp.getY(), this.areaTemp.getWidth(), this.posicaoJogador.y - this.areaTemp.getY());
        }
    }

    @Override
    public void dispose() {
        this.textureAtlas.dispose();
    }
}
