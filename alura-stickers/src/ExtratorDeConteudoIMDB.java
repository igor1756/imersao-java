import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoIMDB implements ExtratorDeConteudo {
    
    public List<Conteudo> extrairConteudo(String json){

        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributosDeItens = parser.parse(json);
        List<Conteudo> conteudos = new ArrayList<>();
        
        // Popular a lista de conteudos
        for (Map<String, String> atributo : listaDeAtributosDeItens) {

            String titulo = atributo.get("title");
            String urlImagem = atributo.get("imagem");
            Conteudo conteudo = new Conteudo(titulo, urlImagem);
            conteudos.add(conteudo);            
        }
        return conteudos;
    }
}