package Cipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by לירון on 03/04/2015.
 */
public class CipherCBC {
    byte[] changedVector;
    int _blockSize;
    int index;

    public CipherCBC(byte[] iv, int blockSize) {
        changedVector = iv;
        _blockSize = blockSize;

        index = 0;
    }

    //returns a cipher code to a given plain text
    public void EncryptText(byte[] l_sPlainText, byte[] b_key, String wPath) {
        //b_iv = iv;

        int iCurrent = 0;
        while (index < l_sPlainText.length) {
            //for (int i = 0; i < l_sPlainText.size(); i++) {
                byte[] bCurrent = GetCurrent(l_sPlainText, _blockSize );
                byte[] toEncrypt = XorIt(bCurrent, changedVector);
                byte[] cipherBiteText = EncryptBlock(toEncrypt, b_key);
                changedVector = cipherBiteText;
                WriteToFile(cipherBiteText, wPath);
            //index += _blockSize;
            //}
        }
    }

    private byte[] GetCurrent(byte[] l_sPlainText, int blockSize) {

        byte [] res = new byte[blockSize];
            if (l_sPlainText.length - index > blockSize) {
                for (int i = 0; i < res.length; i++) {
                    res[i] = l_sPlainText[index];
                    System.out.println(index);
                    index++;
                    if (index == 353)
                        System.out.println(index);
                }
            }
            else {
                int length = l_sPlainText.length-index;
                for(int j=0; j<length; j++ ) {
                    res[j] = l_sPlainText[index];
                    System.out.println(index);
                    index++;
                }
            }
        return res;
    }

    private byte[] EncryptBlock(byte[] toEncrypt, byte[] b_key) {
        byte[] encryptedBlock = new byte[10];
        for (int i = 0; i < toEncrypt.length; i++) {
            switch (toEncrypt[i]) {
                case 'a':
                    encryptedBlock[i] = b_key[0];
                    continue;
                case 'b':
                    encryptedBlock[i] = b_key[1];
                    continue;
                case 'c':
                    encryptedBlock[i] = b_key[2];
                    continue;
                case 'd':
                    encryptedBlock[i] = b_key[3];
                    continue;
                case 'e':
                    encryptedBlock[i] = b_key[4];
                    continue;
                case 'f':
                    encryptedBlock[i] = b_key[5];
                    continue;
                case 'g':
                    encryptedBlock[i] = b_key[6];
                    continue;
                case 'h':
                    encryptedBlock[i] = b_key[7];
                    continue;
            }
            encryptedBlock[i] = toEncrypt[i];
        }
        return encryptedBlock;
    }

    private byte[] XorIt(byte[] bCurrent, byte[] iv) {
        byte[] res = new byte[10];

        for(int i=0; i<10; i++) {
            int temp = (int)bCurrent[i] ^ (int)iv[i];
            res[i] = (byte)(0xff & temp);
        }
        return res;
    }

    public void WriteToFile(byte[] contentInBytes, String wPath) {

            boolean isExist = new File(wPath).exists();
            File file = new File(wPath);
            FileOutputStream fos  = null;
            //File file;

        try {
              fos = new FileOutputStream(wPath, isExist);
                fos.write(contentInBytes);
                System.out.println("Done");
            }
        catch (FileNotFoundException e) {
            System.out.println("File not found" + e);

        }
        catch (IOException ioe) {

            System.out.println("Exception while writing file " + ioe);
        }
        finally {
            //close the streams using close method
            try {
                if (fos != null) {
                    fos.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }
    }
}
