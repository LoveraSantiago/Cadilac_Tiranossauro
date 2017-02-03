package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas;

public final class PincaEntradaQuadratica extends PincaEntrada2{

    @Override
    protected boolean isPtValidos() {
        return this.ptSuperiorProjetado.y > corredor.getPosicaoJogo().y + 15;
    }
}
