package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.informacao.pontos;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.Rotacionador;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;

final class RotacionadorDePontos {

    private int contador;

    private final List<Float> listaX;
    private final List<Float> listaY;

    private Vector2 posicaoCorredor;
    private final Rotacionador rotacionador;

    public RotacionadorDePontos(List<Float> listaX, List<Float> listaY) {
        this.listaX = listaX;
        this.listaY = listaY;
        this.rotacionador = new Rotacionador();
    }

    public void rotacionarPontos(){
        this.posicaoCorredor = CorredorUnico.getInstancia().getCorredorManager().getCorredorP().getWrapperPosicaoJogador().getPosicaoJogador();
        setSenoCosseno();

        for(this.contador = 0; this.contador < this.listaX.size(); this.contador++){
            rotacionar();
        }
    }

    private void rotacionar(){
        this.rotacionador.rotacionar(this.listaX.get(this.contador), this.listaY.get(this.contador), this.posicaoCorredor);

        this.listaX.set(this.contador, this.rotacionador.getResultX());
        this.listaY.set(this.contador, this.rotacionador.getResultY());
    }

    private void setSenoCosseno(){
        this.rotacionador.atualizarAnguloDoJogo();
    }
}
