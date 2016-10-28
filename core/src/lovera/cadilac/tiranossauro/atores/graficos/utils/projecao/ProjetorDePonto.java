package lovera.cadilac.tiranossauro.atores.graficos.utils.projecao;

import com.badlogic.gdx.math.Vector2;

public class ProjetorDePonto {

    protected final Vector2 ptTemp;

    public ProjetorDePonto() {
        this.ptTemp = new Vector2();
    }

    public final float espelharEsquerdaPDireita(float x, float posicaoJogadorX){
        return (posicaoJogadorX - x) + posicaoJogadorX;
    }

    public final float espelharDireitaPEsquerda(float x, float posicaoJogadorX){
        return posicaoJogadorX - (x - posicaoJogadorX);
    }
}
