package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;

public final class DesenhadorGraf_Vetor extends DesenhadorGrafico{

    public DesenhadorGraf_Vetor() {
        super(new PincaEntrada2());
    }

    @Override
    public void meDesenhar(Object objeto) {
        super.digitais.desenharDigitais(super.entrada.getPtSuperior(), super.entrada.getPtLateral());

        if(this.entrada.isJogadaValida()){
            super.resetarInformacao();
            super.desenharFiller(super.entrada.getPtSuperior().y, super.entrada.getPtLateral().x);
            desenharVetor();
        }
    }

    public void desenharVetor(){
        super.rotacionarEAtualizar(super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y, super.posJog.getXY());
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());

        super.iniciarShapeRenderer();
        super.addLinhaToShapeRenderer(super.posJog.getXY(), super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y);
        super.fecharShapeRenderer();

        super.addInformacao(super.posJog.getX(), super.posJog.getY(), super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y);   
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
