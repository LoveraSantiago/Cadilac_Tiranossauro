package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao;

public class EquacaoLogaritmo2 implements TipoEquacao{

    private float b;
    //alturaMax serve para ser a proporção maxima do y grafico para x da tela.
    private float alturaMax;

    //GARANTE QUE A CURVA ESTEJA EM UM MINIMO DE 1.02 OU MAXIMO 1.075 SENDO 0.0065 A PROPORCAO
    public void setB(float b, float maximo){
        this.b = 1.02f + (Math.min(b, 6f) * 0.009f);
//        System.out.println("Setado b: " + this.b);
        setAlturaMax(maximo);
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

    private void setAlturaMax(float maximo) {
        this.alturaMax = Math.min(getY(10), maximo);
    }

    public float getAlturaMax() {
        return alturaMax;
    }
}
