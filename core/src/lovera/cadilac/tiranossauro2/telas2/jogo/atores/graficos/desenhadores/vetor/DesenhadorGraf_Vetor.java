package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;

public final class DesenhadorGraf_Vetor extends DesenhadorGrafico{

    public DesenhadorGraf_Vetor() {
        super(new PincaEntrada2());
    }

    @Override
    public void meDesenhar(Object objeto) {
        super.resetarInformacao();
        desenharFiller(super.entrada.getPtSuperior().y, super.entrada.getPtLateral().x);
        desenharVetor();
    }

    public void desenharVetor(){
        super.rotacionarEAtualizar(super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y, super.posicaoCorredor);
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());

        super.iniciarShapeRenderer();
        super.addLinhaToShapeRenderer(super.posicaoCorredor, super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y);
        super.fecharShapeRenderer();

        super.addInformacao(super.posicaoCorredor.x, super.posicaoCorredor.y, super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y);   
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
