package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.utils.TimeUtils;

import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromTimerColisao;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;

final class TimerColisao implements TipoAtualizavel{

    private long contadorTempo;

    private final MsgFromTimerColisao msgMovimentador;
    private final MsgFromTimerColisao msgCalcAngulo;

    public TimerColisao(MsgFromTimerColisao msgMovimentador, MsgFromTimerColisao msgCalcAngulo) {
        this.msgMovimentador = msgMovimentador;
        this.msgCalcAngulo = msgCalcAngulo;
    }

    @Override
    public void atualizar() {
        realizarAcao();
    }

    private void realizarAcao(){
        if(TimeUtils.timeSinceMillis(this.contadorTempo) >= 500){
            this.msgMovimentador.timerFinalizado();
            this.msgCalcAngulo.timerFinalizado();
        }
    }

    public void inicializar(){
        this.contadorTempo = TimeUtils.millis();
    }
}
