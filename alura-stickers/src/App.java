import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
 
        ClienteHttp client = new ClienteHttp();
        String json = client.buscaDados(url);

        // extrair só os dados que interessam (titulo, poster, classificação)
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        List<Conteudo> listaExtraida = extrator.extrairConteudo(json);

        // exibir e manipular os dados 
        var geradora = new GeradoraDeFigurinhas();
        for (Conteudo item : listaExtraida) {

            String urlImagem = item.getUrlImagem();
            String titulo = item.getTitulo();
            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "alura-stickers/saida/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}
