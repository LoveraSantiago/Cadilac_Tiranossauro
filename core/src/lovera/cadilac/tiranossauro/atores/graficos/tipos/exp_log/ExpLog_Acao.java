package lovera.cadilac.tiranossauro.atores.graficos.tipos.exp_log;

import com.badlogic.gdx.Gdx;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.utils.acoes.Acao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.Equacao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.MensagemGraficos;
import lovera.cadilac.tiranossauro.atores.graficos.utils.acoes.VelocidadeAcao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.Direcao;

public class ExpLog_Acao extends Acao {

    private final Equacao equacao;

    private final ExpLog_ProjetorPtFuturo projetorPt;

    public ExpLog_Acao(Corredor corredor, Equacao equacao, ExpLog_ProjetorPtFuturo projetorPt, MensagemGraficos msg) {
        super(corredor, msg);

        this.equacao = equacao;
        this.projetorPt = projetorPt;
    }

    @Override
    protected void acaoHorizontalADireita() {
        acaoHorizontalEmComum();
        super.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturoDireita_Horizontal(this.equacao, super.contador, super.corredor.getPosicaoInicial()));
    }

    @Override
    protected void acaoHorizontalAEsquerda() {
        acaoHorizontalEmComum();
        super.corredor.setPosicaoProjX(this.projetorPt.espelharEsquerdaPDireita(super.corredor.getPosicaoProjX(), super.corredor.getPosicaoInicial().x));
        super.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturoEsquerda_Horizontal(this.equacao, super.contador, super.corredor.getPosicaoInicial()));
    }

    private void acaoHorizontalEmComum(){
        super.contador += VelocidadeAcao.PASSO * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);

        super.corredor.setPosicaoProjX(super.corredor.getPosicaoInicial().x + super.contador);
        super.corredor.setPosicaoProjY(super.corredor.getPosicaoInicial().y + this.equacao.getY(super.contador));
    }

    @Override
    protected void acaoVerticalADireita() {
        acaoVerticalEmComum();
        super.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturoDireita_Vertical(this.equacao, super.contador, super.corredor.getPosicaoInicial()));
    }

    @Override
    protected void acaoVerticalAEsquerda() {
        acaoVerticalEmComum();
        super.corredor.setPosicaoProjX(this.projetorPt.espelharEsquerdaPDireita(super.corredor.getPosicaoProjX(), super.corredor.getPosicaoInicial().x));
        super.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturoEsquerda_Vertical(this.equacao, super.contador, super.corredor.getPosicaoInicial()));
    }

    private void acaoVerticalEmComum(){
        super.contador += VelocidadeAcao.PASSO * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);

        super.corredor.setPosicaoProjX(super.corredor.getPosicaoInicial().x + this.equacao.getX(super.contador));
        super.corredor.setPosicaoProjY(super.corredor.getPosicaoInicial().y + super.contador);
    }

    public void calcularPontoFinal(Direcao lado) {
        setLado(lado);
        calcularPontoFinal();
        analiseDeEixo();
    }

    @Override
    protected void calcularPontoFinal() {
        reajustePosicaoFinalParaDentroDaTela(this.corredor.getCameraManipulador().getMaiorPtYDaCameraProjecao());
    }

    private void reajustePosicaoFinalParaDentroDaTela(float yMaxTela) {
        if(super.posicaoFinal.y > yMaxTela){
            super.posicaoFinal.x = this.equacao.getX(yMaxTela - super.corredor.getPosicaoProjY());
            super.posicaoFinal.y = this.equacao.getY(super.posicaoFinal.x) + super.corredor.getPosicaoProjY();
            super.posicaoFinal.x += super.corredor.getPosicaoProjX();
        }
    }
}
