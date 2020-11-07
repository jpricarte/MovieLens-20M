package structs.Trie;

import csvReader.structs.Movie;

import java.util.List;

public interface TrieObject {
//    public ArvoreTrie popularTrie(List<Movie> movieList);
    Object[] getChildren();
    boolean isLeaf();
    void setLeaf(boolean leaf);
}
