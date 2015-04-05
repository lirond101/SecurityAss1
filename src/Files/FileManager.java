package Files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Created by ����� on 03/04/2015.
 */

// The Class manage to read the text, key and IV. the text is deposited in structure of String list by size block of 10 chars.
// The key and IV are deposited in byte Arrays.
public class FileManager {
    int _blockSize;

    public FileManager(int blockSize) {
        this._blockSize = blockSize;
    }
    // Read the plain text file
    public  LinkedList<String> ReadText(String tPath) {
        LinkedList<String> l_String = new LinkedList<String>();
        List<Block> l_Block = new LinkedList<Block>();

        FileReader fr = null;
        try {
            fr = new FileReader(tPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String sCurrent;

        try {
            while ((sCurrent = br.readLine()) != null) {
                for (int i = 0; i < sCurrent.toCharArray().length; i++) {
                    if (l_Block.isEmpty()) {
                        Block block = new Block(_blockSize);
                        block.add(sCurrent.toCharArray()[i]);
                        l_Block.add(block);
                        continue;
                    }
                    if (i%_blockSize==0) {
                        Block block = new Block(_blockSize);
                        block.add(sCurrent.toCharArray()[i]);
                        l_Block.add(block);
                        continue;
                    }
                    l_Block.get(l_Block.size() - 1).add(sCurrent.toCharArray()[i]);
                }
            }
            for (int i = 0; i < l_Block.size(); i++){
                String toAdd = new String(l_Block.get(i).c_Array);
                l_String.add(toAdd);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return l_String;
    }

    // Read the key from key file
    public byte[] ReadKey(String kPath) {
        FileReader fr = null;
        try {
            fr = new FileReader(kPath);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        BufferedReader br = null;
        if (fr != null) {
            br = new BufferedReader(fr);
        }
        String sCurrent;
        //char [] c_Array = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        //String sKeyCalc = "";
        char [] key_Array = new char[8];
        //int index = 0;
        byte[] b_Key = new byte[]{0, 0, 0, 0, 0, 0, 0, 0,};

        try {
            if (br != null) {
                while ((sCurrent = br.readLine()) != null) {
                        switch (sCurrent.charAt(0)) {
                            case 'a':
                                key_Array[0] = sCurrent.charAt(2);
                                continue;
                            case 'b':
                                key_Array[1] = sCurrent.charAt(2);
                                continue;
                            case 'c':
                                key_Array[2] = sCurrent.charAt(2);
                                continue;
                            case 'd':
                                key_Array[3] = sCurrent.charAt(2);
                                continue;
                            case 'e':
                                key_Array[4] = sCurrent.charAt(2);
                                continue;
                            case 'f':
                                key_Array[5] = sCurrent.charAt(2);
                                continue;
                            case 'g':
                                key_Array[6] = sCurrent.charAt(2);
                                continue;
                            case 'h':
                                key_Array[7] = sCurrent.charAt(2);
                                continue;
                        }
                    //key_Array[index] = sCurrent.charAt(sCurrent.length() - 1);
                    //index++;
                }
                String sKey = new String(key_Array);
                b_Key = sKey.getBytes();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return b_Key;
    }

    // Read the IV from IV file
    public byte[] ReadIV(String ivPath) {
        FileReader fr = null;
        try {
            fr = new FileReader(ivPath);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        BufferedReader br = null;
        if (fr != null) {
            br = new BufferedReader(fr);
        }

        byte[] b_IV = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        try {
            if (br != null) {
                String sIV = br.readLine();
                b_IV = sIV.getBytes();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return b_IV;
    }
}

