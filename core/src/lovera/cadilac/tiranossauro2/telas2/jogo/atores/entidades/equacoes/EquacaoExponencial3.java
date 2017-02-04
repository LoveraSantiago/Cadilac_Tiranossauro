package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao_LOGEXP;

public final class EquacaoExponencial3 implements TipoEquacao_LOGEXP{

    /*
    Definição da equação exponencial a(b^x)+c
    */
    private final float a =  1;
    private final float c = -1;

    private float b;

    private float larguraMax;

    //diferencaAltura - diferenca entre a posicao do Corredor e ponto Y mais alto da tela
    //ptXMax - diferenca entre a posicao do Corredor e ponto x maior da tela
    public void setB(float b, float diferencaAltura, float diferencaLargura){
        this.b = acentuacaoDaCurva(b);
        setMaximo(diferencaAltura, diferencaLargura);
    }

    //Maximo de Largura
    //Garante que a curva esteja em um maximo da diferenca entre a posicao do corredor e ponto x maior da tela
    //mas escolhe a menor largura que pode vir da diferenca da altura
    @Override
    public void setMaximo(float maxAlt, float maxLarg) {
        this.larguraMax = Math.min(getX(maxAlt), maxLarg);
    }

    @Override
    public float getMaximo() {
        return larguraMax;
    }

    //GARANTE QUE A CURVA ESTEJA EM UM MINIMO DE 2.00 OU MAXIMO DE 1.2
    //6.0f E A DISTANCIA MAXIMA ENTRE O CORREDOR E A POSICAO DO TOQUE NO EIXO Y
    //0.09f RESULTADO DE (2.00 - 1.25)/6.0f
    private float acentuacaoDaCurva(float toqueY){
        return 2.0f - (Math.min(toqueY, 6f) * .125f);
    }

    public float getY(float b, float x){
        return (float)((a * Math.pow(b, x)) + c);
    }

    @Override
    public float getY(float x){
        return getY(this.b, x);
    }

    @Override
    public float getX(float y){
        return (float) (Math.log((y - c) / a) / Math.log(b));
    }
}
