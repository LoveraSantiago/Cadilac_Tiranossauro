package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.exponencial;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.EntradaGrafica;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.ArrastarEntrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.utils.Fase2;
import lovera.cadilac.tiranossauro2.telas2.outras.AjustadorDeTela2;

//TODO: refatorar bem com outras EntradasGraficas
public class EntradaGrafico_Exponencial extends EntradaGrafica{

    private final Entrada2 entrada;
    private final DesenhadorGraf_Exponencial desenhador;

    private final Vector2 posicaoCorredorP;

    //TODO: levar faseManager para membro estatico final de EntradaGrafica
    private final FaseManager2 faseManager2;

    public EntradaGrafico_Exponencial() {
        super();
        this.entrada = new ArrastarEntrada2();
        this.desenhador = new DesenhadorGraf_Exponencial(this.entrada);

        this.faseManager2 = FaseManager2.getInstancia();
        this.posicaoCorredorP = CorredorManager.getInstancia().getCorredorP().getPosicaoJogo();
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(this.faseManager2.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            super.getAreaJogavel2().setarTamanhoEDesenhar(0, 0, AjustadorDeTela2.LARGURA_TELA, this.posicaoCorredorP.y);
        }
        else if(this.faseManager2.isFaseAtual(Fase2.JOGANDO)){
            super.getAreaJogavel2().setarTamanhoEDesenhar(0, 0, AjustadorDeTela2.LARGURA_TELA, this.posicaoCorredorP.y);
            this.desenhador.meDesenhar(null);
        }
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.entrada.passarInputProcessor();
    }

    @Override
    public void dispose() {
        this.desenhador.dispose();
        super.dispose();
    }
}
