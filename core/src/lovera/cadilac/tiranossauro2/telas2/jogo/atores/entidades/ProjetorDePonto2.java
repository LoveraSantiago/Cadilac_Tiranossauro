package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades;

import com.badlogic.gdx.math.Vector2;

public class ProjetorDePonto2 {

    protected final Vector2 ptTemp;

    public ProjetorDePonto2() {
        this.ptTemp = new Vector2();
    }

    public final float espelharEsquerdaPDireita(float x, float posicaoJogadorX){
        return (posicaoJogadorX - x) + posicaoJogadorX;
    }

    public final float espelharDireitaPEsquerda(float x, float posicaoJogadorX){
        return posicaoJogadorX - (x - posicaoJogadorX);
    }

    public Vector2 getPtTemp() {
        return this.ptTemp;
    }
}