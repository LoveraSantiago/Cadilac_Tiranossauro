package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoCamera;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public final class CameraManager implements TipoSingleton, TipoCamera{

    private static CameraManager cameraManager;

    private final CameraJogo cameraJogo;
    private final CameraProjecao cameraProjecao;

    private CameraManager() {
        this.cameraProjecao = new CameraProjecao();
        this.cameraJogo = new CameraJogo();
    }

    @Override
    public void inicializar() {
        cameraManager = new CameraManager();
    }

    public static CameraManager getInstance() {
        return cameraManager;
    }

    @Override
    public void update() {
        this.cameraJogo.update();
        this.cameraProjecao.update();
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

    @Override
    public void resize(int width, int height) {
        this.cameraJogo.resize(width, height);
        this.cameraProjecao.resize(width, height);
    }

    public CameraProjecao getCameraProjecao() {
        return cameraProjecao;
    }

    public CameraJogo getCameraJogo() {
        return cameraJogo;
    }
}
