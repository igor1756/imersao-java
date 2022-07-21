import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        API minhaAPI = API.MEME;

        var http = new ClienteHttp();
        String json = http.buscaDados(minhaAPI.url());

        // exibir e manipular os dados 
        List<Conteudo> conteudos = minhaAPI.extrator().extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
