package lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera;

import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.NormatizadorDeAngulos;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;

final class CameraAngulo {

    private float anguloAtual;

    private final Vector3 eixoZ;
    private final Vector3 posicaoTemp;

    private final CameraJogo cameraJogo;
    private final NormatizadorDeAngulos normatizador;

    public CameraAngulo(CameraJogo cameraJogo) {
        this.cameraJogo = cameraJogo;

        this.anguloAtual = 0;
        this.posicaoTemp = new Vector3();
        this.eixoZ       = new Vector3(0, 0, 1);

        this.normatizador = new NormatizadorDeAngulos();
    }

    public void rotacionarCameraEmVoltaDoPonto(float angulo){
        this.posicaoTemp.set(CorredorUnico.getInstancia().getCorredorManager().getCorredorP().getPosicaoJogo(), 0);

        this.cameraJogo.getCamera().rotateAround(this.posicaoTemp, this.eixoZ, angulo);
        this.anguloAtual = this.anguloAtual + angulo;
    }

    public void normatizarAngulo(){
        this.anguloAtual = this.normatizador.normatizar(this.anguloAtual);
    }

    public float getAnguloAtual() {
        return anguloAtual;
    }
}
