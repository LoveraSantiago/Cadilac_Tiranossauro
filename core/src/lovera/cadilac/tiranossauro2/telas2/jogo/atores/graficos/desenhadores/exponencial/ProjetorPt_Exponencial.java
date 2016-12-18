package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.exponencial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ProjetorDePonto2;

class ProjetorPt_Exponencial extends ProjetorDePonto2{

    public final Vector2 calcularPtFuturoDireita_Horizontal(TipoEquacao equacao, float contador, Vector2 ptParametro){
        contador += Gdx.graphics.getDeltaTime();
        super.ptTemp.x = ptParametro.x + contador;
        super.ptTemp.y = ptParametro.y + equacao.getY(contador);

        return super.ptTemp;
    }

    public final Vector2 calcularPtFuturoEsquerda_Horizontal(TipoEquacao equacao, float contador, Vector2 ptParametro){
        contador += Gdx.graphics.getDeltaTime();
        super.ptTemp.x = ptParametro.x + contador;
        super.ptTemp.y = ptParametro.y + equacao.getY(contador);

        super.ptTemp.x = espelharEsquerdaPDireita(super.ptTemp.x, ptParametro.x);

        return super.ptTemp;
    }

}
