package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.parabola;

import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.entidades.equacoes.EquacaoQuadratica2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.PincaEntradaQuadratica;

public final class DesenhadorGraf_Parabola extends DesenhadorGrafico{

    private float contador;
    private float alturaChegadaTemp;

    private final Vector2 ptSuperior;
    private final Vector2 ptLateral;

    private final ProjetorPt_ParabolaFuturo projetorPt;
    private final EquacaoQuadratica2 quadratica;

    public DesenhadorGraf_Parabola() {
        super(new PincaEntradaQuadratica());

        this.quadratica = new EquacaoQuadratica2();
        this.projetorPt = new ProjetorPt_ParabolaFuturo();

        this.ptSuperior = new Vector2();
        this.ptLateral  = new Vector2();
    }

    @Override
    public void meDesenhar(Object objeto) {
        super.desenharDigitais();

        if(super.entrada.isJogadaValida()){
            resetarComponentes();
            super.desenharFiller(super.entrada.getPtSuperior().y, super.entrada.getPtLateral().x);
            desenharParabola();
        }
    }

    private void resetarComponentes(){
        super.resetarInformacao();

        this.ptSuperior.set(super.entrada.getPtSuperior());
        this.ptLateral.set(super.entrada.getPtLateral());
        this.alturaChegadaTemp = super.entrada.getPtSuperior().y;
    }

    //LEMBRETE: entradaPtSuperior vira o ponto X e entradaPtLateral vira o pontovertice
    private void desenharParabola(){
        super.iniciarShapeRenderer();

        super.pt1Desenho.set(super.posJog.getXY());
        if(this.ptLateral.x > super.posJog.getX()){
            procedimentoADireita();
        }
        else{
            procedimentoAEsquerda();
        }
        super.fecharShapeRenderer();
    }

    private void procedimentoADireita(){
        this.ptSuperior.x = this.ptSuperior.y -  super.posJog.getY();
        this.ptSuperior.y = 0;

        this.ptLateral.y = this.ptLateral.x -  super.posJog.getX();
        this.ptLateral.x = this.ptSuperior.x / 2;

        this.quadratica.definirEquacaoQuadratica(this.ptLateral.x, this.ptLateral.y, this.ptSuperior.x, this.ptSuperior.y);


        for(this.contador = 1; this.contador < this.ptSuperior.x; this.contador = this.contador + .1f){

            super.pt2Desenho.set(this.contador, this.quadratica.getY(this.contador));
            this.projetorPt.inverterXYDoVector2(super.pt2Desenho);
            super.pt2Desenho.x += super.posJog.getX();
            super.pt2Desenho.y += super.posJog.getY();

            super.addLinhaToShapeRenderer(super.pt1Desenho, super.pt2Desenho);

            super.addInformacao(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.addLinhaToShapeRenderer(super.pt1Desenho, super.posJog.getX(), this.alturaChegadaTemp);

        super.addInformacao(super.pt1Desenho.x, super.pt1Desenho.y, super.posJog.getX(), this.alturaChegadaTemp);

        super.rotacionarEAtualizar(this.projetorPt.calcularPtFuturoDireita(this.quadratica, 1, super.posJog.getXY()), super.posJog.getXY());
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());
    }

    private void procedimentoAEsquerda(){
        this.ptSuperior.x = this.ptSuperior.y -  super.posJog.getY();
        this.ptSuperior.y = 0;

        this.ptLateral.y =  super.posJog.getX() - this.ptLateral.x;
        this.ptLateral.x = this.ptSuperior.x / 2;

        this.quadratica.definirEquacaoQuadratica(this.ptLateral.x, this.ptLateral.y, this.ptSuperior.x, this.ptSuperior.y);

        for(this.contador = 1; this.contador < this.ptSuperior.x; this.contador = this.contador + .1f){

            super.pt2Desenho.set(this.contador, this.quadratica.getY(this.contador));
            this.projetorPt.inverterXYDoVector2(super.pt2Desenho);
            super.pt2Desenho.x += super.posJog.getX();
            super.pt2Desenho.y += super.posJog.getY();
            super.pt2Desenho.x = this.projetorPt.espelharDireitaPEsquerda(super.pt2Desenho.x, super.posJog.getX());

            super.addLinhaToShapeRenderer(super.pt1Desenho, super.pt2Desenho);

            super.addInformacao(super.pt1Desenho.x, super.pt1Desenho.y, super.pt2Desenho.x, super.pt2Desenho.y);

            super.pt1Desenho.set(super.pt2Desenho);
        }
        super.addLinhaToShapeRenderer(super.pt1Desenho, super.posJog.getX(), this.alturaChegadaTemp);

        super.addInformacao(super.pt1Desenho.x, super.pt1Desenho.y, super.posJog.getX(), this.alturaChegadaTemp);

        super.rotacionarEAtualizar(this.projetorPt.calcularPtFuturoEsquerda(this.quadratica, 1, super.posJog.getXY()), super.posJog.getXY());
        super.corredor.setPtFuturoProj(super.getXRotacionado(), super.getYRotacionado());
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
