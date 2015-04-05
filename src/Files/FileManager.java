package Files;

import java.io.*;

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
    // Read the plain text file in binary mode
    public byte [] ReadText(String tPath) {

        byte[] buffer = null;
        File a_file = new File(tPath);

        try {
            FileInputStream fis = new FileInputStream(tPath);
            int length = (int) a_file.length();
            buffer = new byte[length];
            fis.read(buffer);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
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

