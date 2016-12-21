package lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor;

import com.badlogic.gdx.utils.TimeUtils;

import lovera.cadilac.tiranossauro2.contratos.mensagens.MsgFromTimerColisao;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoAtualizavel;

class TimerColisao implements TipoAtualizavel{

    private final int SEGUNDOS = 1;
    private long contadorTempo;

    private final MsgFromTimerColisao msg;

    public TimerColisao(MsgFromTimerColisao msg) {
        this.msg = msg;
    }

    @Override
    public void atualizar() {
        realizarAcao();
    }

    private void realizarAcao(){
        if(TimeUtils.timeSinceMillis(this.contadorTempo) >= SEGUNDOS * 1000){
            this.msg.finalizarMovimento();
        }
    }

    public void inicializar(){
        this.contadorTempo = TimeUtils.millis();
    }
}
