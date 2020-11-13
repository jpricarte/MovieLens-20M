package structs.TagTrie;

import java.io.Serializable;
import java.util.LinkedList;

public class TagNode implements Serializable {
    private char c;
    private LinkedList<TagNode> children;
    private boolean isLeaf;
    private LinkedList<Integer> movieIdList;

    public TagNode() { // Construtor da raiz
        this.children = new LinkedList<TagNode>();
        this.isLeaf = false;
        this.movieIdList = null;
    }

    public TagNode(char c) {
        this.children = new LinkedList<TagNode>();
        this.isLeaf = false;
        this.movieIdList = null;
        this.c = c;
    }

//    public TagNode(int movieId) {
//        this.movieId = movieId;
//    }

    public int findIndex(char c) {
        for (TagNode m: children) {
            if(c == m.getC()) {
                return this.getChildren().indexOf(m);
            }
        }
        return -1;
    }

    public int addChild(char c) {
        int i;
        for (i=0; i<children.size(); i++) {
            if(children.get(i).c > c && i != 0) {
                children.add(i-1, new TagNode(c));
                break;
            } else if (children.get(i).c > c) {
                children.add(i, new TagNode(c));
                break;
            }
        }
        if (i == children.size()) children.addLast(new TagNode(c));

        return i;
    }

    public TagNode findChild(char c) {
        int index = this.findIndex(c);
        if(index < 0) {
            return null;
        } else {
            return this.children.get(index);
        }
    }

    public TagNode findOrCreateChild(char c) {
        int index = this.findIndex(c);
        if(index < 0) {
            index = this.addChild(c);
        }

        return this.children.get(index);
    }

    public LinkedList<Integer> getSubTree() {
        LinkedList<Integer> l = new LinkedList<Integer>();
        if (this.isLeaf()) {
//            l.add(this.getMovieIds());
        }

        for (TagNode m : this.children) {
            l.addAll(m.getSubTree());
        }

        return l;
    }

    // Return true if movie does not exists yet
    public boolean addMovieIdIfNotExists(int movieId) {
        if (movieIdList == null) {
            movieIdList = new LinkedList<Integer>();
            movieIdList.addLast(movieId);
            return true;
        }
        for (int id : movieIdList) {
            if (id == movieId) {
                return false;
            }
        }
        movieIdList.addLast(movieId);
        return true;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public LinkedList<TagNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public LinkedList<Integer> getMovieIdList() {
        return movieIdList;
    }

    public void setMovieIdList(LinkedList<Integer> movieIdList) {
        this.movieIdList = movieIdList;
    }

}
