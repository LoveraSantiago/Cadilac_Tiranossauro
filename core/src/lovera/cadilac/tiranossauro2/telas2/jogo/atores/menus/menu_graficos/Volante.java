package lovera.cadilac.tiranossauro2.telas2.jogo.atores.menus.menu_graficos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

final class Volante extends Actor implements Disposable{

    private final VolanteControle controle;
    private final VolanteListener listener;

    private final Texture meiaBussola;
    private final TextureRegion setaFixa;
    private final TextureRegion setaMovel;

    public Volante(Deslizador deslizador) {
        this.controle = new VolanteControle();
        this.listener = new VolanteListener(this, deslizador, this.controle);

        this.setaFixa    = new TextureRegion(new Texture(Gdx.files.internal("setafixa.png")));
        this.setaMovel   = new TextureRegion(new Texture(Gdx.files.internal("setamovel.png")));
        this.meiaBussola = new Texture(Gdx.files.internal("meiabussola.png"));
        addListener(this.listener);
    }

    public void calcularPontos(){
        this.controle.calcularPontos(getX(), getY(), getWidth());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.meiaBussola, getX(), getY(), getWidth(), getHeight());
        if(this.listener.isToqueAcontecendo()){

            batch.draw(this.setaFixa , this.controle.getPtXinicioPonteiro(), getY(),
                       this.controle.getMetadePonteiro(), 0, this.controle.getLarguraPonteiro(), getHeight(),
                       1, 1, -90 + this.listener.getAnguloFixo());

            if(this.listener.isPanAcontecendo()){
                batch.draw(this.setaMovel, this.controle.getPtXinicioPonteiro(), getY(),
                           this.controle.getMetadePonteiro(), 0, this.controle.getLarguraPonteiro(), getHeight(),
                           1, 1, -90 + this.listener.getAnguloMovel());
            }
        }
    }

    @Override
    public void dispose() {
        this.meiaBussola.dispose();
        this.setaFixa.getTexture().dispose();
        this.setaMovel.getTexture().dispose();
    }
}
