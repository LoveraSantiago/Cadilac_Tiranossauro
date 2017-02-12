package lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.contratos.tipo.TipoControlavel;
import lovera.cadilac.tiranossauro2.contratos.tipo.TipoDesenhavel;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.desenhadores.desenhador.DesenhadorGrafico;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.entradas.Entrada2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais.AreaJogavel2;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.graficos.visuais.EixoCartesiano;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.AreaDaCamera;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.Fase2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;


//TODO membros estaticos inicializando em blocos estaticos
public final class EntradaGrafica implements TipoControlavel, TipoDesenhavel, Disposable {

    private static AreaJogavel2 areaJogavel2;
    private static EixoCartesiano eixoCartesiano;

    private AreaDaCamera areaDaCameraTemp;

    private static FaseManager2 faseManager2;

    private final Entrada2 entrada;
    private final DesenhadorGrafico desenhador;

    //Usado apenas para setar objeto sem comportamento, para evitar checagem de null na classe GraficoManager
    public EntradaGrafica() {
        inicializarMembrosEstaticos();

        entrada = null;
        desenhador = null;
    }

    public EntradaGrafica(Class classeDesenhador){
        inicializarMembrosEstaticos();

        try{
            this.desenhador = (DesenhadorGrafico) classeDesenhador.newInstance();
            this.entrada = desenhador.getEntrada();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar instancia da classe " + classeDesenhador.getName(), e);
        }
    }

    //TODO procurar por classes que usam membros estaticos e ver se forma de inicializacao deles se contiver ifs trocar por ternarios
    private void inicializarMembrosEstaticos(){
        areaJogavel2   = areaJogavel2   == null ? new AreaJogavel2()                         : areaJogavel2;
        faseManager2   = faseManager2   == null ? FaseUnico.getInstancia().getFaseManager2() : faseManager2;
        eixoCartesiano = eixoCartesiano == null ? new EixoCartesiano()                       : eixoCartesiano;
    }

    public void updateAreaCamera(GraficosEnum2 graficoEnum){
        this.areaDaCameraTemp = CameraUnico.getCameraManager().getArea_CamProj();

        areaJogavel2.configurarAreaJogavel(graficoEnum, this.areaDaCameraTemp);
        eixoCartesiano.configurarEixo(this.areaDaCameraTemp);

        this.desenhador.updateAreaCamera(this.areaDaCameraTemp);
    }

    @Override
    public void meDesenhar(Object objeto) {
        if(faseManager2.isFaseAtual(Fase2.ACEITAR_ENTRADA)){
            eixoCartesiano.meDesenhar(null);
            areaJogavel2.meDesenhar(null);
        }
        else if(faseManager2.isFaseAtual(Fase2.JOGANDO)){
            eixoCartesiano.meDesenhar(null);
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
        this.desenhador.dispose();
    }
}
