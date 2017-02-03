package lovera.cadilac.tiranossauro2.utils.custom_anotacoes;

public @interface Revisao {

    String dataModificacao();
    String dataRevisao();
    boolean revisada();

    boolean classePublica();
    String justificativa_classePublica();//Justificar casos positivos

    boolean classeFinal();
    String justificativa_classeFinal();//Justificar casos negativos

    boolean metodosEncapsulados();
    boolean atributosEncapsulados();

    boolean substituivel();//Se a classe for concreta pode ser utilizada como nested class
                           //Se for interface pode ser uma classe Abstrata
                           //Se é uma classe abstrata pode ser uma interface
    String[] TODOS();
    String descricao();
    String[] links();
}
