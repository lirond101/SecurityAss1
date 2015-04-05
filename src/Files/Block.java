package Files;

/**
 * Created by לירון on 03/04/2015.
 */
public class Block {

    public char[] c_Array;
    int _size;
    int index = 0;
    Boolean isFull = false;

    public Block(int size) {
        c_Array = new char[size];
        _size = size;
    }

    public void add(char a) {
        if (index<_size){
            c_Array[index] = a;
            index++;
        }
    }
}
