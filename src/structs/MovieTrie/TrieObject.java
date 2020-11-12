package structs.MovieTrie;

import java.util.LinkedList;

public interface TrieObject {
//    public ArvoreTrie popularTrie(List<Movie> movieList);
    LinkedList<MovieNode> getChildren();
    boolean isLeaf();
    void setLeaf(boolean leaf);
}
