package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao_LOGEXP;

public class EquacaoLogaritmo3 implements TipoEquacao_LOGEXP{

    private float b;
    //alturaMax serve para ser a proporção maxima do y grafico para x da tela.
    private float alturaMax;

    //GARANTE QUE A CURVA ESTEJA EM UM MINIMO DE 1.04 OU MAXIMO 1.40
    //6.0f E A DISTANCIA MAXIMA ENTRE O CORREDOR E A POSICAO DO TOQUE NO EIXO Y
    //0.06f RESULTADO DE (1.40 - 1.04)/6.0f
    @Override
    public void setB(float b, float diferencaAltura, float diferencaLargura){
        this.b = 1.04f + (Math.min(b, 6f) * 0.06f);
//        System.out.println("Setado b: " + this.b);
        setMaximo(diferencaAltura, diferencaLargura);
    }

    //Maximo de Largura
    //Garante que a curva esteja em um maximo da diferenca entre a posicao do corredor e ponto x maior da tela
    //mas escolhe a menor altura que pode vir da diferenca da altura
    @Override
    public void setMaximo(float maxAlt, float maxLarg) {
        this.alturaMax = Math.min(getY(maxLarg), maxAlt);
    }

    public float getB(){
        return this.b;
    }

    //O - 1 e para ajustar de acordo com o ajustador para acertar a origem
    @Override
    public float getX(float y) {
        return (float) Math.pow(this.b, y) - 1;
    }

    //O + 1 é um ajustador para que parta da origem
    @Override
    public float getY(float x) {
        return (float) Math.max((Math.log(x + 1) / Math.log(this.b)), 0f);
    }

    @Override
    public float getMaximo() {
        return alturaMax;
    }
}
