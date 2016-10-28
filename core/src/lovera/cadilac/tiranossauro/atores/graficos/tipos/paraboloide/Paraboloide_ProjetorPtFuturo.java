package lovera.cadilac.tiranossauro.atores.graficos.tipos.paraboloide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoQuadratica;
import lovera.cadilac.tiranossauro.atores.graficos.utils.projecao.ProjetorDePonto;

class Paraboloide_ProjetorPtFuturo extends ProjetorDePonto {

    private final EquacaoQuadratica quadratica;

    public Paraboloide_ProjetorPtFuturo(EquacaoQuadratica quadratica) {
        this.quadratica = quadratica;
    }

    public final Vector2 calcularPtFuturo_HorizontalDireita(float contador, Vector2 posicao){
        contador += Gdx.graphics.getDeltaTime();

        super.ptTemp.set(contador, this.quadratica.getY(contador));
        inverterXYDoVector2(super.ptTemp);

        super.ptTemp.x += posicao.x;
        super.ptTemp.y += posicao.y;

        return super.ptTemp;
    }

    public final Vector2 calcularPtFuturo_HorizontalEsquerda(float contador, Vector2 posicao){
        contador += Gdx.graphics.getDeltaTime();

        super.ptTemp.set(contador, this.quadratica.getY(contador));
        inverterXYDoVector2(super.ptTemp);

        super.ptTemp.x += posicao.x;
        super.ptTemp.y += posicao.y;
        super.ptTemp.x = espelharDireitaPEsquerda(super.ptTemp.x, posicao.x);

        return super.ptTemp;
    }

    public final Vector2 calcularPtFuturo_HorizontalEsquerda(float contadorPtFuturo, float corredorPosY, Vector2 posicaoInicial){
        super.ptTemp.set(calculoQuadraticaVerticalComum(contadorPtFuturo, corredorPosY, posicaoInicial));
        super.ptTemp.x = espelharDireitaPEsquerda(super.ptTemp.x, posicaoInicial.x);
        return super.ptTemp;
    }

    public final Vector2 calcularPtFuturo_VerticalDireita(float contadorPtFuturo, float corredorPosY, Vector2 posicaoInicial){
        return calculoQuadraticaVerticalComum(contadorPtFuturo, corredorPosY, posicaoInicial);
    }

    private Vector2 calculoQuadraticaVerticalComum(float contadorPtFuturo, float corredorPosY, Vector2 posicaoInicial){
        if(this.quadratica.isPtAbaixoDoVertice(contadorPtFuturo, corredorPosY, posicaoInicial.y)){
            contadorPtFuturo += Gdx.graphics.getDeltaTime();
            super.ptTemp.y = posicaoInicial.y + this.quadratica.getXMenor(contadorPtFuturo);
        }
        else{
            contadorPtFuturo -= Gdx.graphics.getDeltaTime();
            super.ptTemp.y = posicaoInicial.y + this.quadratica.getXMaior(contadorPtFuturo);
        }
        super.ptTemp.x = posicaoInicial.x + contadorPtFuturo;

        return super.ptTemp;
    }

    public final void inverterXYDoVector2(Vector2 vector2){
        super.ptTemp.x = vector2.x;
        vector2.set(vector2.y, super.ptTemp.x);
    }
}
