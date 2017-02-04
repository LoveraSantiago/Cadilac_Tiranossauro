package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;

public final class WrapperPosicaoJogador {

    private Vector2 posicaoJogador;

    public WrapperPosicaoJogador(Vector2 posicaoJogador) {
        this.posicaoJogador = posicaoJogador;
    }

    public Vector2 getPos() {
        return posicaoJogador;
    }
}
