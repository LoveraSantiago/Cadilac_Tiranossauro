package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoEquacao;

public final class EquacaoQuadratica2 implements TipoEquacao {

    private float a;
    private float b;

    private float delta;

    private float raiz1;
    private float raiz2;

    private final float x1 = 0;
    private final float y1 = 0;

    private final Vector2 ptVertice;

    public EquacaoQuadratica2() {
        this.ptVertice = new Vector2();
    }

    public void definirEquacaoQuadratica(float verticeX, float verticeY, float raizX, float raizY){
        double denom = (this.x1 - verticeX) * (this.x1 - raizX) * (verticeX - raizX);
        this.a = (float) ((raizX * (verticeY - this.y1) + verticeX * (this.y1 - raizY) + this.x1 * (raizY - verticeY)) / denom);
        this.b = (float) ((Math.pow(raizX, 2) * (this.y1 - verticeY) + Math.pow(verticeX, 2) * (raizY - this.y1) +  Math.pow(this.x1, 2) * (verticeY - raizY)) / denom);

        this.ptVertice.set(verticeX, verticeY);
    }

    @Override
    public float getX(float y) {
        if(y > this.ptVertice.y){
//            System.out.println("Passou do vertice era " + y + " passsou a ser " + this.ptVertice.y);
            y = this.ptVertice.y;
        }

        this.delta = (float) (Math.pow(this.b, 2) - (4 * this.a * (-y)));
        this.delta = this.delta < 0 ? 0 : this.delta;

        this.raiz1  = (float) ((-this.b + Math.sqrt(this.delta)) / (2 * this.a));
        this.raiz2 = (float) ((-this.b - Math.sqrt(this.delta)) / (2 * this.a));
//        System.out.println("raiz1: " + this.raiz1 + " raiz2: " + this.raiz2);
        return 0;
    }

    public float getXMaior(float y){
        getX(y);
        return Math.max(this.raiz1, this.raiz2);
    }

    public float getXMenor(float y){
        getX(y);
        return Math.min(this.raiz1, this.raiz2);
    }

    public boolean isPtAbaixoDoVertice(float contador, float corredorPosY, float posInicialY){
        return contador < this.ptVertice.y && corredorPosY < posInicialY + this.ptVertice.x;
    }

    @Override
    public float getY(float x) {
        return (float) ((this.a * Math.pow(x, 2)) + (this.b * x));
    }

    @Override
    public String toString() {
        return "Quadratica a = " + this.a + " b = " + this.b + " c = 0";
    }
}