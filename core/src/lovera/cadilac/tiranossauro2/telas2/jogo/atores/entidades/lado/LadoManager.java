package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.lado;

public final class LadoManager{

    private DirecaoEnum direcaoAtual;

    public LadoManager() {
        resetDirecaoAtual();
    }

    public void setPosicaoToque(float ptXJogador, float ptXToque){
         this.direcaoAtual = ptXJogador < ptXToque ? DirecaoEnum.DIREITA : DirecaoEnum.ESQUERDA;
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
