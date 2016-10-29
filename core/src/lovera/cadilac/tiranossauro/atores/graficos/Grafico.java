package lovera.cadilac.tiranossauro.atores.graficos;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro.atores.graficos.utils.AreaJogavel;
import lovera.cadilac.tiranossauro.atores.graficos.utils.contratos.MensagemGraficos;
import lovera.cadilac.tiranossauro.atores.graficos.utils.entradas.Entrada;
import lovera.cadilac.tiranossauro.contratos.Controlavel;
import lovera.cadilac.tiranossauro.contratos.Desenhavel;
import lovera.cadilac.tiranossauro.controladores.Fase;
import lovera.cadilac.tiranossauro.controladores.FaseManager;

//TODO - Metodo meDesenhar tambem parece que poderá ser refatorado pelo mesmo motivo acima.
public abstract class Grafico implements Controlavel, Desenhavel, Disposable, MensagemGraficos {

    protected final FaseManager faseManager;
    protected final Entrada entrada;
    protected final AreaJogavel areaJogavel;

    public Grafico(Entrada entrada, FaseManager faseManager, AreaJogavel areaJogavel) {
        this.faseManager = faseManager;

        this.entrada = entrada;
        this.entrada.setMensageiro(this);
        this.areaJogavel = areaJogavel;
    }

    protected abstract void realizarAcao();

    @Override
    public final Fase getFaseFromFaseManager() {
        return this.faseManager.getFaseAtual();
    }

    @Override
    public final void setFaseToFaseManager(Fase faseAtual) {
        this.faseManager.setFaseAtual(faseAtual);
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.entrada.passarInputProcessor();
    }


}
