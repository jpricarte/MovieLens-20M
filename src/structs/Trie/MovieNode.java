package structs.Trie;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class MovieNode implements TrieObject {
    private char c;
    private MovieNode children[];
    private boolean isLeaf;
    private int movieId;

    public MovieNode() {
        this.children = (MovieNode[]) Array.newInstance(MovieNode.class, 256);
        this.isLeaf = false;
        movieId = -1;
    }

    public MovieNode(char c) {
        this.children = (MovieNode[]) Array.newInstance(MovieNode.class, 256);
        this.isLeaf = false;
        this.movieId = -1;
        this.c = c;
    }

    public MovieNode(int movieId) {
        this.movieId = movieId;
    }

    public LinkedList<Integer> getSubTree() {
        LinkedList<Integer> l = new LinkedList<Integer>();
        if (this.isLeaf()) {
            l.add(this.getMovieId());
        }
        for (int i=0; i<256; i++) {
            if (this.getChildren()[i] != null) {
                l.addAll( this.getChildren()[i].getSubTree() );
            }
        }
        return l;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public MovieNode[] getChildren() {
        return children;
    }

    public void setChildren(MovieNode[] children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
