package structs.Trie;

import java.io.Serializable;
import java.util.LinkedList;

public class Trie implements Serializable {
    private MovieNode root;

    public Trie() {
        this.root = new MovieNode();
    }

    public void insert(String movieName, int movieId) {
        MovieNode aux = root;
        movieName = movieName.trim().toLowerCase();
        // Faz apenas o caminhamento
        for (int i=0; i < movieName.length(); i++) {
            char c = (char) (movieName.charAt(i) & 0xFF);


            MovieNode nextNode = aux.getChildren()[c];
            if(nextNode == null) {
                aux.getChildren()[c] = new MovieNode(c);
            }

            aux = aux.getChildren()[c];
        }

        // Foi até o ultimo nodo, agora insere filme
        aux.setMovieId(movieId);
        aux.setLeaf(true);
    }

    public int getMovie(String movieName) {
        MovieNode aux = root;
        movieName = movieName.trim().toLowerCase();
        // Faz apenas o caminhamento
        for (int i=0; i < movieName.length(); i++) {
            char c = (char) (movieName.charAt(i) & 0xFF);

            MovieNode nextNode = aux.getChildren()[c];

            if(nextNode == null) {
                return -1;
            }

            aux = aux.getChildren()[c];
        }

        return aux.getMovieId();
    }

    public LinkedList<Integer> getAllMovies(String prefix) {
        MovieNode aux = root;
        prefix = prefix.trim().toLowerCase();

        // Faz apenas o caminhamento
        for (int i=0; i < prefix.length(); i++) {

            char c = (char) (prefix.charAt(i) & 0xFF);

            MovieNode nextNode = aux.getChildren()[c];

            if(nextNode == null) {
                return new LinkedList<Integer>();
            }

            aux = aux.getChildren()[c];
        }

        return aux.getSubTree();
    }

}
