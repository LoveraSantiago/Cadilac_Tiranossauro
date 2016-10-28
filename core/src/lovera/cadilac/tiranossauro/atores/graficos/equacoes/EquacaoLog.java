package lovera.cadilac.tiranossauro.atores.graficos.equacoes;

import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.Equacao;

public final class EquacaoLog implements Equacao {

    private float b;
    //alturaMax serve para ser a proporção maxima do y grafico para x da tela.
    private float alturaMax;

    //GARANTE QUE A CURVA ESTEJA EM UM MINIMO DE 1.01 OU MAXIMO 1.1 SENDO 0.0015 A PROPORCAO
    public void setB(float b, float maximo){
        this.b = 1.01f + (Math.min(b, 60) * 0.0015f);
//        System.out.println("Setado b: " + this.b);
        setAlturaMax(maximo);
    }

    public float getB(){
        return this.b;
    }

    //Math.max Protege de não retornar um infinito negativo quando x = 0
    @Override
    public float getY(float x){
        return (float) Math.max((Math.log(x) / Math.log(this.b)), 0f);
    }

    @Override
    public float getX(float y){
        return (float) Math.pow(this.b, y);
    }

    private void setAlturaMax(float maximo) {
        this.alturaMax = Math.min(getY(100), maximo);
    }

    public float getAlturaMax() {
        return alturaMax;
    }
}
