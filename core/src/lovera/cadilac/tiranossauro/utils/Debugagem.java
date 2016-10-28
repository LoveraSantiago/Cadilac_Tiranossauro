package lovera.cadilac.tiranossauro.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.StringBuilder;

import lovera.cadilac.tiranossauro.atores.graficos.equacoes.EquacaoDaReta;

public class Debugagem {

    private static ShapeRenderer shapeRenderer = new ShapeRenderer();

    public static void dbgAtor_Posicao(String nomeAtor, Actor ator){
        System.out.println(nomeAtor + " origemX: " + ator.getOriginX());
        System.out.println(nomeAtor + " origemY: " + ator.getOriginY());
        System.out.println(nomeAtor + " X: " + ator.getX());
        System.out.println(nomeAtor + " Y: " + ator.getY());
    }

    public static void dbgEquacaoDaReta(EquacaoDaReta eq, float entrada){
        System.out.println("Equacao Reta: " + eq + " f(" + entrada + ") x=" + eq.getX(entrada) + " y=" + eq.getY(entrada));
    }

    public static void dbgPinch(Vector2 i1, Vector2 i2, Vector2 p1, Vector2 p2){
        System.out.println("i1 x:" + i1.x + " y:" + i1.y);
        System.out.println("i2 x:" + i2.x + " y:" + i2.y);

        System.out.println("p1 x:" + p1.x + " y:" + p1.y);
        System.out.println("p2 x:" + p2.x + " y:" + p2.y);
    }

    public static void dbgPan(float x, float y, float deltaX, float deltaY){
        dbgPanComum("Pan", x, y, deltaX, deltaY);
    }

    public static void dbgPanStop(float x, float y, float deltaX, float deltaY){
        dbgPanComum("PanStop", x, y, deltaX, deltaY);
    }

    private static void dbgPanComum(String texto, float x, float y, float deltaX, float deltaY){
        System.out.println(texto + " x:" + x + " y: " + y + " deltaX:" + deltaX + " deltaY:" + deltaY);
    }

    public static void dbgPontoVector3(String texto, Vector3 p){
        System.out.println(texto + " x:" + p.x + " y:" + p.y);
    }

    public static void dbgPontoVector2(String texto, Vector2 p){
        dbgPontoVector2(texto, p.x, p.y);
    }

    public static void dbgPontoVector2(String texto, float x, float y){
        System.out.println(texto + " x:" + x + " y:" + y);
    }

    public static void dbgSprite(String texto, Sprite sprite){
        StringBuilder strBuider = new StringBuilder();
        strBuider.append("Sprite " + texto + "/n");
        strBuider.append("pt origem x:" + sprite.getOriginX() + " y:" + sprite.getOriginY());
        System.out.println(strBuider);
    }

    public static void dbgTouchDragged(int x, int y, int pt){
        System.out.println("TouchDragged x:" + x + " y:" + y + " pt:" + pt);
    }

    public static void dbgTouchDown(float x, float y, int pt, int btn){
        dbgTouchComum("TouchDown", x, y, pt, btn);
    }

    public static void dbgTouchUp(float x, float y, float pt, float btn){
        dbgTouchComum("TouchUp", x, y, pt, btn);
    }

    private static void dbgTouchComum(String texto, float x, float y, float pt, float btn){
        System.out.println(texto + " x:" + x + " y:" + y + " pt:" + pt + " btn:" + btn);
    }

    public static void desenharPontoNaTela(Color cor, float x, float y){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(cor);
        shapeRenderer.circle(x, y, 50);
        shapeRenderer.end();
    }

    public static void desenharPontoNaTela(Color cor, Vector2 posicao, boolean printDbg){
        desenharPontoNaTela(cor, posicao.x, posicao.y);

        if(printDbg) dbgPontoVector2("Ponto desenhado posicao", posicao);
    }

    public static void desenharPontoNaTela(Color cor, Vector3 posicao, boolean printDbg){
        desenharPontoNaTela(cor, posicao.x, posicao.y);
        if(printDbg)dbgPontoVector3("Ponto desenhado posicao", posicao);
    }
}
