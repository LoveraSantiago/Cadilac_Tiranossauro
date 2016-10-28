package lovera.cadilac.tiranossauro.atores.graficos.equacoes;

import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.Equacao;

public final class EquacaoDaReta implements Equacao {

    private float coefAngular;
    private float intercepto;

    public void definirEquacaoDaReta(float x1, float y1, float x2, float y2){
        if(x2 == x1) x2 += .01f;//Impedir casos de divisão por 0

        this.coefAngular = (y2 - y1) / (x2 - x1);
        this.intercepto = y2 - (coefAngular * x2);
    }

    @Override
    public float getX(float y){
        return ((y - this.intercepto) / this.coefAngular);
    }

    @Override
    public float getY(float x){
        return (this.coefAngular * x) + intercepto;
    }

    @Override
    public String toString() {
        return "CoefAng:" + this.coefAngular + " Intercep:" + this.intercepto;
    }
}
