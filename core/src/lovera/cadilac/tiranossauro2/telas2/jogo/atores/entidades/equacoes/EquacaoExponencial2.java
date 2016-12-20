package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao;

public class EquacaoExponencial2 implements TipoEquacao{

    /*
    Definição da equação exponencial a(b^x)+c
    */
    private final float a =  1;
    private final float c = -1;

    private float b;

    public void setB(float b){
        this.b = acentuacaoDaCurva(b);
    }

    //ANTIGO TAMANHO DE TELA
    //GARANTE QUE A CURVA ESTEJA EM UM MINIMO DE 1.35 OU MAXIMO DE 1.062 SENDO 0.0048 A PROPORCAO
//    private float acentuacaoDaCurva(float toqueY){
//        return 1.35f - (Math.min(toqueY, 60) * 0.0048f);
//    }

    //GARANTE QUE A CURVA ESTEJA EM UM MINIMO DE 2.00 OU MAXIMO DE 1.415
    //6.0f E A DISTANCIA MAXIMA ENTRE O CORREDOR E A POSICAO DO TOQUE NO EIXO Y
    //0.09f RESULTADO DE (2.00 - 1.4515)/6.0f
    private float acentuacaoDaCurva(float toqueY){
        return 2.0f - (Math.min(toqueY, 6f) * .0914f);
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
