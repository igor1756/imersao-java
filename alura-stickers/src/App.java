import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        var geradora = new GeradoraDeFigurinhas();
        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            String titulo = filme.get("title");
            String urlImagem = filme.get("image");
            
            // Criando uma url do tipo url chamando a funcao que pega o stream da url e armazena numa variavel do tipo inputstream
            InputStream inputStream = new URL(urlImagem).openStream();

            // Criar uma variavel que servira para nomear o arquivo que sera salvo
            String nomeDoArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeDoArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}
