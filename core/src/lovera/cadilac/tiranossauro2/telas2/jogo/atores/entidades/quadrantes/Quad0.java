package lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.quadrantes;

final class Quad0 extends QuadrantePai{
    @Override
    public boolean meuQuadrante() {
        return this.ptOrigemX == this.ptX && this.ptOrigemY == this.ptY;
    }

    //ESSE METODO NAO DEVE SER CHAMADO EM CASO DE PONTOS IGUAIS
    @Override
    public boolean pontoAtingido() {
        throw new UnsupportedOperationException("Não deve ser chamado.");
    }

    @Override
    int getMeuQuadrante() {
        return 0;
    }
}
