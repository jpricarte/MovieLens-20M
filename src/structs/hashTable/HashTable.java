package structs.hashTable;

import java.io.Serializable;


public class HashTable<T extends HashObject> implements Serializable {
    private int tableSize;
    private T[] hashTable;
    private boolean[] occupied;

    public HashTable(int tableSize) {
        this.tableSize = tableSize;
        this.hashTable = (T[]) new HashObject[tableSize];
        this.occupied  = new boolean[tableSize];
    }

    public void insert(T elem) {
        int hash = generateHash(elem.getId());

        while (this.occupied[hash]) {
            hash = generateAuxHash(elem.getId(), hash);
        }

        this.occupied[hash] = true;
        this.hashTable[hash] = elem;
    }

    public T find(int id) {
        int hash = generateHash(id);

        while(this.occupied[hash] && this.hashTable[hash].getId() != id) {
            hash = generateAuxHash(id, hash);
        }

        if(!this.occupied[hash]) {
            return null;
        } else return this.hashTable[hash];
    }

    private int generateHash(int id) {
        return (id * 31) % tableSize;
    }

    private int generateAuxHash(int id, int actualHash) {
        return (actualHash + (id * 37)) % tableSize;
    }
}
