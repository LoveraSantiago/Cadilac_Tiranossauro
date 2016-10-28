package lovera.cadilac.tiranossauro.atores.graficos.equacoes;

import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.Equacao;

public final class EquacaoExponencial implements Equacao {

    /*
    Definição da equação exponencial a(b^x)+c
    */
    private final float a =  1;
    private final float c = -1;

    private float b;

    public void setB(float b){
        this.b = acentuacaoDaCurva(b);
    }

    //GARANTE QUE A CURVA ESTEJA EM UM MINIMO DE 1.35 OU MAXIMO DE 1.062 SENDO 0.0048 A PROPORCAO
    private float acentuacaoDaCurva(float toqueY){
        return 1.35f - (Math.min(toqueY, 60) * 0.0048f);
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
