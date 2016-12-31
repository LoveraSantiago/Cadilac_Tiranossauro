package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.lado;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;

public final class LadoManager{

    private DirecaoEnum direcaoAtual;

    private final Vector2 posicaoJogador;

    public LadoManager() {
        this.posicaoJogador = CorredorUnico.getInstancia().getCorredorManager().getCorredorP().getPosicaoJogo();
        resetDirecaoAtual();
    }

    public void setPosicaoToque(float x){
         this.direcaoAtual = this.posicaoJogador.x < x ? DirecaoEnum.ESQUERDA : DirecaoEnum.DIREITA;
    }

    public void resetDirecaoAtual(){
        this.direcaoAtual = DirecaoEnum.INDEFINIDO;
    }

    public DirecaoEnum getDirecaoAtual() {
        return direcaoAtual;
    }

    public boolean isLado(DirecaoEnum direcao){
        return this.direcaoAtual == direcao;
    }
}
