package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao;

public final class EquacaoLinear implements TipoEquacao{

    private float coefAngular;
    private float intercepto;

    public void definirEquacaoDaReta(float x1, float y1, float x2, float y2){
        if(x2 == x1) x2 += .01f;//Impedir casos de divisão por 0

        this.coefAngular = (y2 - y1) / (x2 - x1);
        this.intercepto = y2 - (coefAngular * x2);
    }

    public void definirEquacaoDaReta(Vector2 pt1, Vector2 pt2){
        definirEquacaoDaReta(pt1.x, pt1.y, pt2.x, pt2.y);
    }

    public void definirEquacaoDaReta(float x1, float y1, Vector2 pt2){
        definirEquacaoDaReta(x1, y1, pt2.x, pt2.y);
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
    public void setMaximo(float maxAlt, float maxLarg) {
        throw new UnsupportedOperationException("Equacao linear não precisa definir um limite");
    }

    @Override
    public float getMaximo() {
        throw new UnsupportedOperationException("Equacao linear não tem um limite maximo");
    }

    @Override
    public String toString() {
        return "CoefAng:" + this.coefAngular + " Intercep:" + this.intercepto;
    }
}
