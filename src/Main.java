import Files.FileManager;
import Cipher.CipherCBC;


public class Main {

    public static void main(String[] args) {
        String tPath = "D:\\stam.txt";
        String keyPath = "D:\\key_example.txt";
        String ivPath = "D:\\IV_Example.txt";
        String dPath = "D:\\cipherMsg_Example.txt";
        FileManager fm = new FileManager(10);
        //byte[] text = fm.ReadText(tPath);
        byte [] key = fm.ReadKey(keyPath);
        byte [] iv = fm.ReadIV(ivPath);

        byte[] decriptedText = fm.ReadText(dPath);
        CipherCBC cipherCBC = new CipherCBC(iv, 10);
        //cipherCBC.Encryption(text,key, "D:\\newfile.txt" );
        cipherCBC.Decryption(decriptedText, key, "D:\\newDecriptedFile.txt" );
        System.out.println("done!");
    }
}
