package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes;

final class Quad0 extends QuadrantePai{
    @Override
    public boolean meuQuadrante() {
        return this.ptOrigemX == this.ptX && this.ptOrigemY == this.ptY;
    }

    @Override
    public boolean pontoAtingido() {
        return true;
    }

    @Override
    int getMeuQuadrante() {
        return 0;
    }
}
