package lovera.cadilac.tiranossauro2.telas2.jogo;

import com.badlogic.gdx.utils.Disposable;

import lovera.cadilac.tiranossauro2.telas2.jogo.atores.corredor.CorredorManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.atores.pista_de_corrida.PistaDeCorrida2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.fase.FaseManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.GraficoManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.InformacaoManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MenuManager2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.MeuBox2D2;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.camera.CameraManager;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CameraUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.ControleUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.CorredorUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.FaseUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.LadoUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.SpriteBatchUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.unicos.VoltarOrigemUnico;
import lovera.cadilac.tiranossauro2.telas2.jogo.controladores.voltar.VoltarOrigem2;

final class SingletonsManager_TelaJogo implements Disposable{

    private CameraManager cameraManager;
    private MenuManager2 menuManager2;
    private CorredorManager corredorManager;
    private GraficoManager2 graficoManager2;
    private VoltarOrigem2 voltarOrigem2;

    private MeuBox2D2 meuBox2DTemp;

    private PistaDeCorrida2 pistaDeCorrida2;

    public void inicializarSingletons(){
        new SpriteBatchUnico().inicializar();

        new LadoUnico().inicializar();

        new CameraUnico().inicializar();
        this.cameraManager = CameraUnico.getCameraManager();

        new FaseUnico().inicializar();

        new MeuBox2D2().inicializar();

        this.pistaDeCorrida2 = new PistaDeCorrida2();
        this.pistaDeCorrida2.inicializar();

        new InformacaoManager().inicializar();

        new CorredorUnico().inicializar();
        this.corredorManager = CorredorUnico.getInstancia().getCorredorManager();

        this.menuManager2 = new MenuManager2();
        this.menuManager2.inicializar();

        this.graficoManager2 = new GraficoManager2();
        this.graficoManager2.inicializar();

        new ControleUnico(this.graficoManager2.getMapaEntradaGraficas()).inicializar();

        new VoltarOrigemUnico().inicializar();
        this.voltarOrigem2 = VoltarOrigemUnico.voltarOrigem2();
    }

    public void render(float delta){
        this.meuBox2DTemp = MeuBox2D2.getInstancia();

        this.cameraManager.atualizar();
        this.meuBox2DTemp.atualizar();

        //UPDATE DO SPRITEBATCH COM CAMERA JOGO PARA RENDERIZAR PISTA
        this.cameraManager.updateSpriteBatch_CamJogo();
        this.pistaDeCorrida2.meDesenhar(null);

        //UPDATE DO SPRITEBATCH COM CAMERA NORMAL
        this.cameraManager.updateSpriteBatch_CamProj();
        this.graficoManager2.meDesenhar(null);

        //UPDATE DO SPRITEBATCH COM CAMERA JOGO PARA RENDERIZAR PISTA
        this.cameraManager.updateSpriteBatch_CamJogo();
        this.corredorManager.meDesenhar(null);

        this.menuManager2.meDesenhar(null);

        //SENDO CHAMADO POR ULTIMO PARA PODER SER VISUALIZADO POR CIMA DA TELA
        this.meuBox2DTemp.meDesenhar(null);
        this.voltarOrigem2.meDesenhar(null);
    }

    @Override
    public void dispose() {
        SpriteBatchUnico.getInstancia().getSpriteBatchManager().getSpriteBatch().dispose();
        MeuBox2D2.getInstancia().dispose();
        this.menuManager2.dispose();
    }
}
