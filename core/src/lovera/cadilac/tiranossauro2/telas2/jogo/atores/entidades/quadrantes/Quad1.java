package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes;

final class Quad1 extends QuadrantePai {

    @Override
    public boolean meuQuadrante() {
        return super.getPtOrigemX() < this.ptX && super.getPtOrigemY() < this.ptY;
    }

    @Override
    public boolean pontoAtingido() {
        return super.getPtOrigemX() >= this.ptX && super.getPtOrigemY() >= this.ptY;
    }

    @Override
    public int getMeuQuadrante() {
        return 1;
    }
}
