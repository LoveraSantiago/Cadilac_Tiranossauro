package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.vetor;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntrada2;

public final class DesenhadorGraf_Vetor extends DesenhadorGrafico{

    public DesenhadorGraf_Vetor() {
        super(new PincaEntrada2());
    }

    @Override
    public void meDesenhar(Object objeto) {
        resetarComponentes();
        desenharVetor();
    }

    private void resetarComponentes(){
        super.resetarInformacao();
    }
    
    public void desenharVetor(){
        super.rotacionarEAtualizar(super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y, super.posicaoCorredor);
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());

        super.iniciarShapeRenderer();

//TODO LEVAR ESSE CARA PARA OS EIXOS CARTESIANOS
//        this.shapeRenderer.line(super.posicaoCorredor.x, super.posicaoCorredor.y - 1,
//                                super.posicaoCorredor.x, super.entrada.getPtSuperior().y);
//
//        if(super.entrada.getPtLateral().x >= super.posicaoCorredor.x){
//            this.shapeRenderer.line(super.posicaoCorredor.x - 1    , super.posicaoCorredor.y,
//                                    super.entrada.getPtLateral().x , super.posicaoCorredor.y);
//        }
//        else{
//            this.shapeRenderer.line(super.entrada.getPtLateral().x, super.posicaoCorredor.y,
//                                    super.posicaoCorredor.x + 1   , super.posicaoCorredor.y);
//        }

        super.addLinhaToShapeRenderer(super.posicaoCorredor, super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y);
        super.fecharShapeRenderer();

        super.addInformacao(super.posicaoCorredor.x, super.posicaoCorredor.y, super.entrada.getPtLateral().x, super.entrada.getPtSuperior().y);   
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
