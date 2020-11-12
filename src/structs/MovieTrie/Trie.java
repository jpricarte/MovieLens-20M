package structs.MovieTrie;

import java.io.Serializable;
import java.util.LinkedList;

public class Trie implements Serializable {
    private MovieNode root;

    public Trie() {
        this.root = new MovieNode();
    }

    public void insert(String movieName, int movieId) {
        MovieNode aux = root;
        movieName = movieName.trim().toUpperCase();

        // Faz apenas o caminhamento
        for (int i=0; i < movieName.length(); i++) {
            char c = movieName.charAt(i);

            // Procura filho ou cria, se esse não existir
            aux = aux.findOrCreateChild(c);
        }

        aux.setMovieId(movieId);
        aux.setLeaf(true);
    }

    public int getMovie(String movieName) {
        MovieNode aux = root;
        movieName = movieName.trim().toUpperCase();

        // Faz o caminhamento ou retorna -1 se não existir caminho
        for (int i=0; i < movieName.length(); i++) {
            char c = movieName.charAt(i);

            aux = aux.findChild(c);
            if (aux == null) return -1;
        }

        return aux.getMovieId();
    }

    public LinkedList<Integer> getAllMovies(String prefix) {
        MovieNode aux = root;
        prefix = prefix.trim().toUpperCase();

        // Faz o caminhamento ou retorna uma lista vazia se não houver caminho
        for (int i=0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            aux = aux.findChild(c);
            if (aux == null) return new LinkedList<>();
        }

        return aux.getSubTree();
    }

}
