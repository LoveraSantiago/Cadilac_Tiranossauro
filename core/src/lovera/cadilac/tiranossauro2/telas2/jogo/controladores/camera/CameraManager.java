package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoCamera;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public final class CameraManager implements TipoSingleton, TipoCamera{

    private static CameraManager cameraManager;

    private CameraJogo cameraJogo;
    private CameraProjecao cameraProjecao;
    private CameraAngulo angulo;

    @Override
    public void inicializar() {
        cameraManager = this;
        this.cameraProjecao = new CameraProjecao();
        this.cameraJogo = new CameraJogo();
        this.angulo = new CameraAngulo();
    }

    public static CameraManager getInstance() {
        return cameraManager;
    }

    @Override
    public void resize(int width, int height) {
        this.cameraJogo.resize(width, height);
        this.cameraProjecao.resize(width, height);
    }

    @Override
    public void atualizar() {
        this.cameraJogo.atualizar();
        this.cameraProjecao.atualizar();
    }

    //Não dá para realizar a operação nas duas classes cameras igual ao metodo update pq o spritebatch deve ser atualizado em tempos
    //diferente para as cameras diferentes
    @Override
    public void updateSpriteBatch() {
        throw new UnsupportedOperationException("Operação não suportada ver CameraManager updateSpriteBatch comentários");
    }

    public void updateSpriteBatch_CamJogo() {
        this.cameraJogo.updateSpriteBatch();
    }

    public void updateSpriteBatch_CamProj() {
        this.cameraProjecao.updateSpriteBatch();
    }

    public void normatizarAngulo(){
        this.angulo.normatizarAngulo();
    }

    public OrthographicCamera getCameraProjecao() {
        return cameraProjecao.getCamera();
    }

    public Viewport getViewPortCameraProjecao(){
        return this.cameraProjecao.getViewport();
    }

    public OrthographicCamera getCameraJogo() {
        return cameraJogo.getCamera();
    }
}
