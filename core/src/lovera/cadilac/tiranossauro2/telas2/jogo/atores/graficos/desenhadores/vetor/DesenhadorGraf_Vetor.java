package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import com.badlogic.gdx.graphics.Color;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais.Digital;
import lovera.cadilac.tiranossauro2.utils.Debugagem;

public final class DesenhadorGraf_Vetor extends DesenhadorGrafico{

    public DesenhadorGraf_Vetor() {
        super(new PincaEntrada2());
    }
    Digital digital = new Digital();
    @Override
    public void meDesenhar(Object objeto) {
        Debugagem.desenharPontoNaTela(Color.RED, super.entrada.getPtSuperior(), true);
        Debugagem.desenharPontoNaTela(Color.ORANGE, super.entrada.getPtLateral(), false);
        super.resetarInformacao();
        super.desenharFiller(super.entrada.getPtSuperior().y, super.entrada.getPtLateral().x);
        desenharVetor();
        digital.meDesenhar(super.entrada.getPtSuperior());
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
