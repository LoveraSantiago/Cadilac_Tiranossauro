package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao;

public class ProjetorDePontoFuturo2 {

    protected final Vector2 ptTemp;

    public ProjetorDePontoFuturo2() {
        this.ptTemp = new Vector2();
    }

    public final float espelharEsquerdaPDireita(float x, float posicaoJogadorX){
        return (posicaoJogadorX - x) + posicaoJogadorX;
    }

    public final float espelharDireitaPEsquerda(float x, float posicaoJogadorX){
        return posicaoJogadorX - (x - posicaoJogadorX);
    }

    public final Vector2 calcularPtFuturoDireita(TipoEquacao equacao, float contador, Vector2 ptParametro){
        contador += Gdx.graphics.getDeltaTime();
        this.ptTemp.x = ptParametro.x + contador;
        this.ptTemp.y = ptParametro.y + equacao.getY(contador);

        return this.ptTemp;
    }

    public final Vector2 calcularPtFuturoEsquerda(TipoEquacao equacao, float contador, Vector2 ptParametro){
        contador += Gdx.graphics.getDeltaTime();
        this.ptTemp.x = ptParametro.x + contador;
        this.ptTemp.y = ptParametro.y + equacao.getY(contador);

        this.ptTemp.x = espelharEsquerdaPDireita(this.ptTemp.x, ptParametro.x);

        return this.ptTemp;
    }

    public Vector2 getPtTemp() {
        return this.ptTemp;
    }
}
