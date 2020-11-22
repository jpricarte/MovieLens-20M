package structs.GenreTrie;

import structs.TagTrie.TagNode;
import structs.hashTable.HashTable;
import structs.hashTable.MovieNode;

import java.util.LinkedList;

public class GenreTrie {
    private GenreNode root;

    public GenreTrie() {
        this.root = new GenreNode();
    }

    public void insert(String genre, int movieId, HashTable<MovieNode> movieHashTable) {
        GenreNode aux = root;
        genre = genre.trim().toUpperCase();

        // Faz apenas o caminhamento
        for (int i=0; i < genre.length(); i++) {
            char c = genre.charAt(i);

            // Procura filho ou cria, se esse não existir
            aux = aux.findOrCreateChild(c);
        }

        aux.addMovieIdIfNotExists(movieId, movieHashTable);
        aux.setLeaf(true);
    }

    public LinkedList<Integer> getMovies(String genreName) {
        GenreNode aux = root;
        genreName = genreName.trim().toUpperCase();

        // Faz o caminhamento ou retorna -1 se não existir caminho
        for (int i=0; i < genreName.length(); i++) {
            char c = genreName.charAt(i);

            aux = aux.findChild(c);
            if (aux == null) return new LinkedList<>();
        }

        return aux.getMovieIdList();
    }
}

