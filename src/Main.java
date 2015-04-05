import Files.FileManager;
import Cipher.CipherCBC;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        String path = "D:\\plainMsg1_example.txt";
        String keyPath = "D:\\key_example.txt";
        String ivPath = "D:\\IV_Example.txt";
        FileManager fm = new FileManager(10);
        LinkedList<String> l_String = fm.ReadText(path);
        byte [] key = fm.ReadKey(keyPath);
        byte [] iv = fm.ReadIV(ivPath);

        CipherCBC cipherCBC = new CipherCBC(iv);
        cipherCBC.EncryptText(l_String,key, "D:\\newfile.txt" );
        System.out.println("done!");

    }
}
