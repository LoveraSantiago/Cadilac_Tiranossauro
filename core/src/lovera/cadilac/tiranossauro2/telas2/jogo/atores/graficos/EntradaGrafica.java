package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhadorGrafico;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.areajogavel.AreaJogavel2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;

public final class EntradaGrafica implements TipoControlavel, TipoDesenhavel, Disposable {

    private static AreaJogavel2 areaJogavel2;
    private static FaseManager2 faseManager2;

    private final Entrada2 entrada;
    private final TipoDesenhadorGrafico desenhador;

    public EntradaGrafica() {
        inicializarMembrosEstaticos();

        entrada = null;
        desenhador = null;
    }

    public EntradaGrafica(Class classeDesenhador){
        inicializarMembrosEstaticos();

        try{
            this.desenhador = (TipoDesenhadorGrafico) classeDesenhador.newInstance();
            this.entrada = desenhador.getEntrada();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar instancia da classe " + classeDesenhador.getName(), e);
        }
    }

    private void inicializarMembrosEstaticos(){
        if(areaJogavel2 == null){
            areaJogavel2 = new AreaJogavel2();
        }

        if(faseManager2 == null){
            faseManager2 = FaseUnico.getInstancia().getFaseManager2();
        }
    }

    public void configurarAreaJogavel(GraficosEnum2 graficoEnum){
        areaJogavel2.configurarAreaJogavel(graficoEnum);
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(faseManager2.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            areaJogavel2.meDesenhar(null);
        }
        else if(faseManager2.isFaseAtual(Fase2.JOGANDO)){
            areaJogavel2.meDesenhar(null);
            this.desenhador.meDesenhar(null);
        }
    }

    @Override
    public InputProcessor passarInputProcessor() {
        return this.entrada.passarInputProcessor();
    }

    @Override
    public void dispose() {
        areaJogavel2.dispose();
        this.desenhador.dispose();
    }
}
