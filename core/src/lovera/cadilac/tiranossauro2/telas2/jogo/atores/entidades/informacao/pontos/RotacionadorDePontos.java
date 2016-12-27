package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Rotacionador;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorManager;

final class RotacionadorDePontos {

    private int contador;
    private float tempX;
    private float tempY;

    private final List<Float> listaX;
    private final List<Float> listaY;

    private Vector2 posicaoCorredor;
    private final CameraManager cameraManager;
    private final Rotacionador rotacionador;

    public RotacionadorDePontos(List<Float> listaX, List<Float> listaY) {
        this.listaX = listaX;
        this.listaY = listaY;

        this.cameraManager = CameraUnico.getCameraManager();
        this.rotacionador = new Rotacionador();
    }

    public void rotacionarPontos(){
        this.posicaoCorredor = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();
        setSenoCosseno();

        for(this.contador = 0; this.contador < this.listaX.size(); this.contador++){
            rotacionar();
        }
    }

    private void rotacionar(){
        this.rotacionador.rotacionar(this.listaX.get(this.contador), this.listaY.get(this.contador), this.posicaoCorredor);

        this.listaX.set(this.contador, this.rotacionador.getResultX() + this.posicaoCorredor.x);
        this.listaY.set(this.contador, this.rotacionador.getResultY() + this.posicaoCorredor.y);
    }

    private void setSenoCosseno(){
        this.rotacionador.atualizarAnguloDoJogo();
    }
}