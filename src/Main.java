import Files.FileManager;
import Cipher.CipherCBC;


public class Main {

    public static void main(String[] args) {
        String tPath = "D:\\plainMsg_example.txt";
        String keyPath = "D:\\key_example.txt";
        String ivPath = "D:\\IV_Example.txt";
        FileManager fm = new FileManager(10);
        byte[] text = fm.ReadText(tPath);
        byte [] key = fm.ReadKey(keyPath);
        byte [] iv = fm.ReadIV(ivPath);

        CipherCBC cipherCBC = new CipherCBC(iv, 10);
        cipherCBC.EncryptText(text,key, "D:\\newfile.txt" );
        System.out.println("done!");

    }
}
