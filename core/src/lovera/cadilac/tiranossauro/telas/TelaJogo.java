package lovera.cadilac.tiranossauro.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

import lovera.cadilac.tiranossauro.atores.Corredor;
import lovera.cadilac.tiranossauro.atores.PistaDeCorrida;
import lovera.cadilac.tiranossauro.atores.VoltarOrigem;
import lovera.cadilac.tiranossauro.atores.graficos.Grafico;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.exp_log.ExpLog_ProjetorPtFuturo;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.exponencial.Exponencial_Manager;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.logaritmo.Logaritmo_Manager;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.paraboloide.Paraboloide_Manager;
import lovera.cadilac.tiranossauro.atores.graficos.tipos.vetor.Vetor_Manager;
import lovera.cadilac.tiranossauro.atores.graficos.utils.AreaJogavel;
import lovera.cadilac.tiranossauro.atores.graficos.utils.GraficosEnum;
import lovera.cadilac.tiranossauro.contratos.MensagemDeMenus;
import lovera.cadilac.tiranossauro.controladores.MeuBox2D;
import lovera.cadilac.tiranossauro.controladores.CameraManipulador;
import lovera.cadilac.tiranossauro.controladores.ControleManager;
import lovera.cadilac.tiranossauro.controladores.Fase;
import lovera.cadilac.tiranossauro.controladores.FaseManager;
import lovera.cadilac.tiranossauro.controladores.GraficoManager;
import lovera.cadilac.tiranossauro.telas.menus.MenuManager;

public final class TelaJogo implements MensagemDeMenus, Screen {

    private final SpriteBatch spriteBatch;//ok

    private final ControleManager controleManager;
    private final GraficoManager graficoManager;
    private final FaseManager faseManager;//ok
    private final MenuManager menuManager;

    private final MeuBox2D meuBox2D;

    private final CameraManipulador cameraManipulador;

    private final PistaDeCorrida pista;
    private final Corredor corredor;

    private final VoltarOrigem voltarOrigem;

    public TelaJogo() {

        //COMPONENTES NECESSARIOS
        this.faseManager = new FaseManager();//ok
        this.spriteBatch = new SpriteBatch();//ok
        this.cameraManipulador = new CameraManipulador();

        //COMPONENTES DE JOGO
        this.corredor = new Corredor(this.cameraManipulador, this.faseManager);
        this.pista    = new PistaDeCorrida(this.cameraManipulador.getCameraJogo());

        this.menuManager = new MenuManager(this, this.spriteBatch);

        Map<GraficosEnum, Grafico> mapaGraficos = inicializarGraficos();

        //INICIAR GERENCIADORES APOS COMPONENTES DO JOGO
        this.graficoManager = new GraficoManager(mapaGraficos);
        this.controleManager = new ControleManager(this.menuManager, mapaGraficos);

        //INICIAR COMPONENTE RESPONSAVEL POR VOLTAR A TELA AO PONTO DE ORIGEM
        this.voltarOrigem = new VoltarOrigem(this.faseManager, this.controleManager, this.corredor, null);

        //BOX2D
        this.meuBox2D = new MeuBox2D(this.cameraManipulador.getCameraJogo(), this.corredor);
        this.meuBox2D.setPistaDeCorrida(this.pista);
        this.corredor.setBox2dCoisas(this.meuBox2D.getCorredorBody(), this.meuBox2D.getWorld(), this.meuBox2D.getPistaBody());
    }

    private Map<GraficosEnum, Grafico> inicializarGraficos(){
        ExpLog_ProjetorPtFuturo projetorPt = new ExpLog_ProjetorPtFuturo();
        AreaJogavel areaJogavel = new AreaJogavel();

        Map<GraficosEnum, Grafico> mapaGraficos = new HashMap<GraficosEnum, Grafico>(GraficosEnum.values().length);
        mapaGraficos.put(GraficosEnum.VETOR,       new Vetor_Manager      (this.corredor, this.faseManager, areaJogavel));
        mapaGraficos.put(GraficosEnum.EXPONENCIAL, new Exponencial_Manager(this.corredor, this.faseManager, projetorPt, areaJogavel));
        mapaGraficos.put(GraficosEnum.LOGARITMO,   new Logaritmo_Manager  (this.corredor, this.faseManager, projetorPt, areaJogavel));
        mapaGraficos.put(GraficosEnum.PARABOLOIDE, new Paraboloide_Manager(this.corredor, this.faseManager, areaJogavel));
        return mapaGraficos;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//ok

        this.cameraManipulador.update();//ok

        //MAPA EM LAYER DIFERENTE
        this.spriteBatch.setProjectionMatrix(this.cameraManipulador.getProjectionMatrix_Jogo());//ok
        this.pista.meDesenhar(this.spriteBatch);//ok

        this.spriteBatch.setProjectionMatrix(this.cameraManipulador.getProjectionMatrix_Projecao());//ok
        this.graficoManager.meDesenhar(this.spriteBatch);

        this.corredor    .meDesenhar(this.spriteBatch);
        this.menuManager .meDesenhar(this.spriteBatch);
        this.voltarOrigem.meDesenhar(null);
        this.meuBox2D    .meDesenhar(null);//ok
    }

    @Override
    public void resize(int width, int height) {
        this.cameraManipulador.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        this.spriteBatch   .dispose();
        this.graficoManager.dispose();
        this.menuManager   .dispose();
    }

    @Override
    public void graficoEscolhido(GraficosEnum graficoEnum) {
        this.graficoManager.setarGraficoAtual(graficoEnum);//ok
        this.controleManager.setarControlavelAtual(graficoEnum);//ok
    }

    @Override
    public void helperEscolhido(byte btn) {
        if(btn == MenuManager.BTN_VOLTAR){
            this.faseManager.setFaseAtual(Fase.ESCOLHENDO_GRAFICO);
            this.controleManager.voltarMenuGrafico();
        }
    }

    @Override
    public void rotacionandoCamera(float angulo) {//ok
        this.cameraManipulador.rotacionarCameraEmVoltaDoPonto(this.corredor.getPosicaoJogada(), angulo);
    }

    @Override
    public void normatizarAngulo() {
        this.cameraManipulador.normatizarAngulo();
    }//ok

    @Override
    public Fase getFaseFromFaseManager() {
        return this.faseManager.getFaseAtual();
    }

    @Override
    public void setFaseToFaseManager(Fase faseAtual) {
        this.faseManager.setFaseAtual(faseAtual);
    }
}