package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.math.Vector2;

class CalculadorVelocidade {

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

        System.out.println("Velocidade " + VELOCIDADE);
        System.out.println("Percurso " + percursoTotal);
        System.out.println("Tempo total " + tempoTotal);
        System.out.println("Pontos " + pontos);
        System.out.println("Tempo ponto " + tempoPonto);
    }

    public Vector2 calcularVelocidadePonto(Vector2 posicaoAtual, Vector2 ponto){
        this.resultVelocidade.x = (ponto.x - posicaoAtual.x) / this.tempoPonto;
        this.resultVelocidade.y = (ponto.y - posicaoAtual.y) / this.tempoPonto;
        return this.resultVelocidade;
    }

}
