package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import com.badlogic.gdx.Gdx;

import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromDeslizador;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;

final class Deslizador{

    private final byte ENTRADA = 0;
    private final byte FIXO    = 1;
    private final byte SAIDA   = 2;
    private byte estadoPosicaoBarra;

    private final short FORA_DA_TELA = -162;
    private short posicaoBarra = 0;

    private final short VELOCIDADE_BARRA = 500;

    private final MsgFromDeslizador msg;

    public Deslizador(MsgFromDeslizador msg) {
        this.msg = msg;
        setBarraPosicao_Inicial();
    }

    public void analisarPosicaoDaBarra() {
        switch (this.estadoPosicaoBarra){
            case FIXO:
                return;
            case ENTRADA:
                procedimentoEntradaBarra();
                break;
            case SAIDA:
                procedimentoSaidaBarra();
                break;
        }
    }

    private void procedimentoEntradaBarra(){
        if(this.posicaoBarra >= 0){
            setBarraPosicao_Normal();
        }
        else{
            this.posicaoBarra += this.VELOCIDADE_BARRA * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
        }
    }

    private void procedimentoSaidaBarra(){
        if(this.posicaoBarra >= this.FORA_DA_TELA){
            this.posicaoBarra -= this.VELOCIDADE_BARRA * Math.min(Gdx.graphics.getDeltaTime(), 0.1f);
        }
        else{
            setBarraPosicao_Inicial();
            FaseUnico.getInstancia().getFaseManager2().setFaseAtual(Fase2.ACEITAR_ENTRADA);
        }
    }

    private void setBarraPosicao_Inicial(){
        this.estadoPosicaoBarra = this.ENTRADA;
        this.posicaoBarra = this.FORA_DA_TELA;
    }

    private void setBarraPosicao_Normal(){
        this.estadoPosicaoBarra = this.FIXO;
        this.posicaoBarra = 0;
        this.msg.setBarraPosicao_Normal();
    }

    public void setBarraPosicao_Saida(){
        this.estadoPosicaoBarra = this.SAIDA;
        this.posicaoBarra = 0;
    }

    public boolean isPosicaoBarraFixa(){
        return this.estadoPosicaoBarra == FIXO;
    }

    public short getPosicaoBarra() {
        return posicaoBarra;
    }
}
