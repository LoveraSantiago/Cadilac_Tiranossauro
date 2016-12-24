package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;

final class Rotacionador {

    private int contador;
    private float angulo;
    private float tempX;
    private float tempY;
    private float cos;
    private float sen;

    private final List<Float> listaX;
    private final List<Float> listaY;

    private Vector2 posicaoCorredor;
    private final CameraManager cameraManager;

    public Rotacionador(List<Float> listaX, List<Float> listaY) {
        this.listaX = listaX;
        this.listaY = listaY;

        this.cameraManager = CameraUnico.getCameraManager();
    }

    public void rotacionarPontos(){
        this.posicaoCorredor = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();
        setSenoCosseno();

        for(this.contador = 0; this.contador < this.listaX.size(); this.contador++){
            rotacionar();
        }
    }

    private void rotacionar(){
        this.tempX = this.listaX.get(this.contador) - this.posicaoCorredor.x;
        this.tempY = this.listaY.get(this.contador) - this.posicaoCorredor.y;

        this.listaX.set(this.contador, ((this.tempX * this.cos)-(this.tempY * this.sen)) + this.posicaoCorredor.x);
        this.listaY.set(this.contador, ((this.tempY * this.cos)+(this.tempX * this.sen)) + this.posicaoCorredor.y);
    }

    private void setSenoCosseno(){
        this.angulo = this.cameraManager.getAngulo_CamJogo();

        this.cos = MathUtils.cosDeg(this.angulo);
        this.sen = MathUtils.sinDeg(this.angulo);
    }
}
