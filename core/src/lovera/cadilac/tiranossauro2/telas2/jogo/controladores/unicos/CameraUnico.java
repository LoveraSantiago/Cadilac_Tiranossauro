package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoSingleton;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;

public final class CameraUnico implements TipoSingleton {

    private static CameraManager cameraManager;

    @Override
    public void inicializar() {
        cameraManager = new CameraManager();
    }

    public static CameraManager getCameraManager() {
        return cameraManager;
    }
}
