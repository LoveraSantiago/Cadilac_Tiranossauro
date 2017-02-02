package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.utils.Debugagem;

final class CalculadorVelocidade {

    private final float VELOCIDADE = 7f;

    private float tempoTotal;
    private float tempoPonto;

    private final Vector2 resultVelocidade;

    public CalculadorVelocidade() {
        this.resultVelocidade = new Vector2();
    }

    public void calcularVelocidadePercurso(int pontos, float percursoTotal){
        this.tempoTotal = percursoTotal / VELOCIDADE;
        this.tempoPonto = this.tempoTotal / pontos;
    }

    public Vector2 calcularVelocidadePonto(Vector2 posicaoAtual, Vector2 ponto){
        this.resultVelocidade.x = (ponto.x - posicaoAtual.x) / this.tempoPonto;
        this.resultVelocidade.y = Math.min((ponto.y - posicaoAtual.y) / this.tempoPonto, 10);
        Debugagem.dbgPontoVector2("Velocidade", this.resultVelocidade);
        return this.resultVelocidade;
    }

//    public Vector2 calcularVelocidadePonto(Vector2 posicaoAtual, Vector2 ponto){
//        this.resultVelocidade.x = (ponto.x - posicaoAtual.x) / this.tempoPonto;
//        this.resultVelocidade.y = (ponto.y - posicaoAtual.y) / this.tempoPonto;
//        Debugagem.dbgPontoVector2("Velocidade", this.resultVelocidade);
//        return this.resultVelocidade;
//    }
}
