package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades;

import com.badlogic.gdx.math.MathUtils;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

public class Rotacionador {

    private float angulo;
    private float cos;
    private float sen;

    private float tempX;
    private float tempY;

    private float resultX;
    private float resultY;

    private final static CameraManager cameraManager;

    static{
        cameraManager = CameraUnico.getCameraManager();
    }

    public Rotacionador() {
    }

    public void atualizarAnguloDoJogo(){
        this.angulo = this.cameraManager.getAngulo_CamJogo();

        this.cos = MathUtils.cosDeg(this.angulo);
        this.sen = MathUtils.sinDeg(this.angulo);
    }
}
