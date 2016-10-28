package lovera.cadilac.tiranossauro.atores.graficos.tipos.exp_log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.Equacao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.projecao.ProjetorDePonto;

public class ExpLog_ProjetorPtFuturo extends ProjetorDePonto {

    public final Vector2 calcularPtFuturoDireita_Horizontal(Equacao equacao, float contador, Vector2 ptParametro){
        contador += Gdx.graphics.getDeltaTime();
        super.ptTemp.x = ptParametro.x + contador;
        super.ptTemp.y = ptParametro.y + equacao.getY(contador);

        return super.ptTemp;
    }

    public final Vector2 calcularPtFuturoEsquerda_Horizontal(Equacao equacao, float contador, Vector2 ptParametro){
        contador += Gdx.graphics.getDeltaTime();
        super.ptTemp.x = ptParametro.x + contador;
        super.ptTemp.y = ptParametro.y + equacao.getY(contador);

        super.ptTemp.x = espelharEsquerdaPDireita(super.ptTemp.x, ptParametro.x);

        return super.ptTemp;
    }

    public final Vector2 calcularPtFuturoDireita_Vertical(Equacao equacao, float contador, Vector2 ptParametro){
        contador += Gdx.graphics.getDeltaTime();
        super.ptTemp.x = ptParametro.x + equacao.getX(contador);
        super.ptTemp.y = ptParametro.y + (contador);

        return super.ptTemp;
    }

    public final Vector2 calcularPtFuturoEsquerda_Vertical(Equacao equacao, float contador, Vector2 ptParametro){
        contador += Gdx.graphics.getDeltaTime();
        super.ptTemp.x = ptParametro.x + equacao.getX(contador);
        super.ptTemp.y = ptParametro.y + contador;

        super.ptTemp.x = espelharEsquerdaPDireita(super.ptTemp.x, ptParametro.x);

        return super.ptTemp;
    }
    
}
