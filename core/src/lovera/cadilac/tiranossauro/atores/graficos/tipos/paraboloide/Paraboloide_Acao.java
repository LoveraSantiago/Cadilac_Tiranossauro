package lovera.cadilac.tiranossauro.atores.graficos.tipos.paraboloide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoQuadratica;
import lovera.cadilac.tiranossauro.atores.graficos.utils.acoes.Acao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.MensagemGraficos;
import lovera.cadilac.tiranossauro.atores.graficos.utils.acoes.VelocidadeAcao;
import lovera.cadilac.tiranossauro.atores.graficos.utils.Direcao;

class Paraboloide_Acao extends Acao {

    private final Paraboloide_ProjetorPtFuturo projetorPt;

    private final EquacaoQuadratica quadratica;

    public Paraboloide_Acao(Corredor corredor, EquacaoQuadratica quadratica, Paraboloide_ProjetorPtFuturo projetorPt, MensagemGraficos msg) {
        super(corredor, msg);

        this.quadratica = quadratica;
        this.projetorPt = projetorPt;
    }

    @Override
    protected void acaoHorizontalADireita() {
        acaoHorizontalEmComum();

        super.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalDireita(contador, super.corredor.getPosicaoInicial()));
    }

    @Override
    protected void acaoHorizontalAEsquerda() {
        acaoHorizontalEmComum();

        super.corredor.setPosicaoProjX(this.projetorPt.espelharDireitaPEsquerda(super.corredor.getPosicaoProjX(), super.corredor.getPosicaoInicial().x));
        super.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalEsquerda(contador, super.corredor.getPosicaoInicial()));
    }

    private void acaoHorizontalEmComum(){
        super.contador += VelocidadeAcao.PASSO * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);

        super.corredor.setPosicaoProjX(super.corredor.getPosicaoInicial().x + this.quadratica.getY(super.contador));
        super.corredor.setPosicaoProjY(super.corredor.getPosicaoInicial().y + super.contador);
    }

    @Override
    protected void acaoVerticalADireita() {
        if(this.quadratica.isPtAbaixoDoVertice(super.contador, super.corredor.getPosicaoProjY(), super.corredor.getPosicaoInicial().y)){
            super.contador += VelocidadeAcao.PASSO * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
            acaoVerticalADireita_PtFuturo(super.contador);
            super.corredor.setPosicaoProjY(super.corredor.getPosicaoInicial().y + this.quadratica.getXMenor(super.contador));
        }
        else{
            super.contador -= VelocidadeAcao.PASSO * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
            acaoVerticalADireita_PtFuturo(super.contador);
            super.corredor.setPosicaoProjY(super.corredor.getPosicaoInicial().y + this.quadratica.getXMaior(super.contador));
        }
        super.corredor.setPosicaoProjX(super.corredor.getPosicaoInicial().x + super.contador);
    }

    private void acaoVerticalADireita_PtFuturo(float contador){
        super.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturo_VerticalDireita(contador, super.corredor.getPosicaoProjY(), super.corredor.getPosicaoInicial()));
    }

    @Override
    protected void acaoVerticalAEsquerda() {
        if(this.quadratica.isPtAbaixoDoVertice(super.contador, super.corredor.getPosicaoProjY(), super.corredor.getPosicaoInicial().y)){
            super.contador += VelocidadeAcao.PASSO * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
            acaoVerticalAEsquerda_PtFuturo(super.contador);
            super.corredor.setPosicaoProjY(super.corredor.getPosicaoInicial().y + this.quadratica.getXMenor(super.contador));
        }
        else{
            super.contador -= VelocidadeAcao.PASSO * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
            acaoVerticalAEsquerda_PtFuturo(super.contador);
            super.corredor.setPosicaoProjY(super.corredor.getPosicaoInicial().y + this.quadratica.getXMaior(super.contador));
        }
        super.corredor.setPosicaoProjX(super.corredor.getPosicaoInicial().x + super.contador);
        super.corredor.setPosicaoProjX(this.projetorPt.espelharDireitaPEsquerda(super.corredor.getPosicaoProjX(), super.corredor.getPosicaoInicial().x));
    }

    private void acaoVerticalAEsquerda_PtFuturo(float contador){
        super.corredor.setPtFuturoProj(this.projetorPt.calcularPtFuturo_HorizontalEsquerda(contador, super.corredor.getPosicaoProjY(), super.corredor.getPosicaoInicial()));
    }

    public void setLado(Direcao lado){
        super.setLado(lado);
    }

    public void setPosicaoFinal(Vector3 posicaoFinal){
        super.posicaoFinal.set(super.corredor.getPosicaoInicial().x, posicaoFinal.y);
    }

    public void setPosicaoInicial(){
        super.corredor.getPosicaoInicial().set(super.corredor.getPosicaoProjetada());
    }

    @Override
    protected final void finalizarAcao() {
        super.finalizarAcao();
    }

    @Override
    public void calcularPontoFinal() {
        throw new UnsupportedOperationException("Calcular ponto final não é necessario na classe Paraboloide_Acao");
    }

    @Override
    public void setEixo(Direcao eixo) {
        super.setEixo(eixo);
    }
}
