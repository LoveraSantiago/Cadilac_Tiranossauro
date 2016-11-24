package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoCamera;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;

public final class CameraManager implements TipoSingleton, TipoCamera{

    private static CameraManager cameraManager;

    private CameraJogo cameraJogo;
    private CameraProjecao cameraProjecao;

    @Override
    public void inicializar() {
        cameraManager = new CameraManager();
        this.cameraProjecao = new CameraProjecao();
        this.cameraJogo = new CameraJogo();
    }

    public static CameraManager getInstance() {
        return cameraManager;
    }

    @Override
    public void update() {
        this.cameraJogo.update();
        this.cameraProjecao.update();
    }

    @Override
    public void resize(int width, int height) {
        this.cameraJogo.resize(width, height);
        this.cameraProjecao.resize(width, height);
    }
}
