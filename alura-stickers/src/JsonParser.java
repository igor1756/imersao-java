import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    
    // Padroes do regex armazenados em pattern
    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    // Funcao que recebe um json e retorna a lista de itens
    public List<Map<String, String>> parse(String json) {
        // Chama o metodo matcher do pattern passando o json e armazena no matcher
        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {

            throw new IllegalArgumentException("Não encontrou items.");
        }

        // Chama o resultado do primeiro matcher divide em itens e armazena em um array de strings
        String[] items = matcher.group(1).split("\\},\\{");

        // Cria uma lista do tipo arraylist que contem maps
        List<Map<String, String>> dados = new ArrayList<>();

        // Pega cada item da lista
        for (String item : items) {

            // Cria um map do tipo hashmap
            Map<String, String> atributosItem = new HashMap<>();

            // Cria o segundo matcher
            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);

            // Enquanto houver conteudo no matcher executa
            while (matcherAtributosJson.find()) {
                // O primeiro grupo do matcher eh chave
                String atributo = matcherAtributosJson.group(1);
                // O segundo grupo do matcher eh valor
                String valor = matcherAtributosJson.group(2);
                // Insere chave e valor no map
                atributosItem.put(atributo, valor);
            }

            // Adiciona o map na lista a ser retornada
            dados.add(atributosItem);
        }

        return dados;
    } 

}
