package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos;

import java.util.List;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;

final class Rotacionador {

    private float angulo;
    private int contador;

    private float atualX;
    private float atualY;

    private final List<Float> listaX;
    private final List<Float> listaY;

    private final CameraManager cameraManager;

    public Rotacionador(List<Float> listaX, List<Float> listaY) {
        this.listaX = listaX;
        this.listaY = listaY;

        this.cameraManager = CameraUnico.getCameraManager();
    }

    public void rotacionarPontos(){
        getAngulo();

        for(this.contador = 0; this.contador < this.listaX.size(); this.contador++){

        }
    }

    private void rotacionar(){
        this.atualX = this.listaX.get(this.contador);
        this.atualY = this.listaY.get(this.contador);


    }


    private void getAngulo(){
        this.angulo = cameraManager.getAngulo_CameraJogo();
    }

}
