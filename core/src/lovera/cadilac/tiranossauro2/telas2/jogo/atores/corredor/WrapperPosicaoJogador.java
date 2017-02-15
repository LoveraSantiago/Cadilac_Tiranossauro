package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;

public final class WrapperPosicaoJogador {

    private final Vector2 posicaoJogador;
    private final Vector2 posicaoJogadorTemp;

    public WrapperPosicaoJogador(Vector2 posicaoJogador) {
        this.posicaoJogador = posicaoJogador;
        this.posicaoJogadorTemp = new Vector2();
    }

    public float getY() {
        return this.posicaoJogador.y;
    }

    public float getX(){
        return this.posicaoJogador.x;
    }

    public Vector2 getXY(){
        return this.posicaoJogadorTemp.set(this.posicaoJogador);
    }
}
