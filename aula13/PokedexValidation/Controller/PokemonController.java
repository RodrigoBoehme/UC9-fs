package Controller;

import Model.DAO.PokemonDAO;
import Model.Pokemon;
import java.sql.SQLException;
import java.util.List;

public class PokemonController {
    private PokemonDAO pokemonDAO;

    public PokemonController() {
        this.pokemonDAO = new PokemonDAO();
    }

    public void cadastrarPokemon(String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        if(nome==null||nome.trim().isEmpty()){
            throw new Exception("O nome é Obrigatorio");
        }
        if(tipoPrimario==null||tipoPrimario.trim().isEmpty()){
            throw new Exception("O tipo Primario é obrigatorio");
        }
        if(tipoPrimario.equals(tipoSecundario)){
            throw new Exception("O tipo secundario não pode ser igual ao primario.");
        }
        if(pokemonDAO.pokemonJaExiste(nome)){
            throw new Exception("Ja existe Pokemon com nome: "+nome);
        }




        // Exemplo de chamada do Model (já validado):
        Pokemon pokemon = new Pokemon(nome, tipoPrimario, tipoSecundario, nivel, hpMaximo);
        try {
            pokemonDAO.inserir(pokemon);
        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar Pokémon no banco de dados: " + e.getMessage());
        }
    }

    public void atualizarPokemon(int id, String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---

        if(pokemonDAO.buscarPorId(id)==null){
            throw new Exception("Não há pokemon com Id: "+id);
        }
        if(tipoPrimario.trim().equals(tipoSecundario.trim())){
            throw new Exception("O tipo secundario não pode ser igual ao primario.");
        }
        List<Pokemon> a=pokemonDAO.buscarPorNome(nome.trim());
        for(Pokemon poke:a){

            if(poke.getId()!=id&&poke.getNome().equals(nome)){
                throw new Exception("Nome ja está em Uso.");
            }
        }
        // Exemplo de chamada do Model (já validado):
        Pokemon pokemon = new Pokemon(id, nome, tipoPrimario, tipoSecundario, nivel, hpMaximo);
        try {
            pokemonDAO.atualizar(pokemon);
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar Pokémon no banco de dados: " + e.getMessage());
        }
    }

    public List<Pokemon> listarTodosPokemons() {
        return pokemonDAO.listarTodos();
    }

    public Pokemon buscarPokemonPorId(int id) {
        return pokemonDAO.buscarPorId(id);
    }

    public void removerPokemon(int id) throws Exception {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        if(pokemonDAO.buscarPorId(id)==null){
            throw new Exception("Nenhum pokemon encontrado com Id: "+id);
        }

        try {
            pokemonDAO.remover(id);
        } catch (SQLException e) {
            throw new Exception("Erro ao remover Pokémon: " + e.getMessage());
        }
    }

    public List<Pokemon> buscarPokemonPorNome(String nome) {
        // --- EXERCÍCIO: Adicionar validações aqui! ---
        return pokemonDAO.buscarPorNome(nome);
    }
}