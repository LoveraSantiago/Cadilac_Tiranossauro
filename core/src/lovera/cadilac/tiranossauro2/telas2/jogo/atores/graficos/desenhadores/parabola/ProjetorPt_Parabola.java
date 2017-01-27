package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.parabola;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.ProjetorDePonto2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoQuadratica2;

final class ProjetorPt_Parabola extends ProjetorDePonto2 {

    public ProjetorPt_Parabola() {
        super();
    }

    public final Vector2 calcularPtFuturoDireita_Horizontal(EquacaoQuadratica2 quadratica, float contador, Vector2 posicao){
        contador += Gdx.graphics.getDeltaTime();

        super.ptTemp.set(contador, quadratica.getY(contador));
        inverterXYDoVector2(super.ptTemp);

        super.ptTemp.x += posicao.x;
        super.ptTemp.y += posicao.y;

        return super.ptTemp;
    }

    public final Vector2 calcularPtFuturoEsquerda_Horizontal(EquacaoQuadratica2 quadratica, float contador, Vector2 posicao){
        contador += Gdx.graphics.getDeltaTime();

        super.ptTemp.set(contador, quadratica.getY(contador));
        inverterXYDoVector2(super.ptTemp);

        super.ptTemp.x += posicao.x;
        super.ptTemp.y += posicao.y;
        super.ptTemp.x = espelharDireitaPEsquerda(super.ptTemp.x, posicao.x);

        return super.ptTemp;
    }

    public final void inverterXYDoVector2(Vector2 vector2){
        super.ptTemp.x = vector2.x;
        vector2.set(vector2.y, super.ptTemp.x);
    }
}
