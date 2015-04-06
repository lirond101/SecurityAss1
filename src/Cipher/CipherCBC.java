package Cipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by לירון on 03/04/2015.
 */
public class CipherCBC {
    byte[] changedVector;
    byte[] d_changedVector;
    int _blockSize;
    int index;
    int d_index;

    public CipherCBC(byte[] iv, int blockSize) {
        changedVector = iv;
        d_changedVector = iv;
        _blockSize = blockSize;
        index = 0;
        d_index = 0;
    }

    //returns a cipher code to a given plain text
    public void Encryption(byte[] l_sPlainText, byte[] b_key, String wPath) {

        while (index < l_sPlainText.length) {
                byte[] bCurrent = GetCurrent(l_sPlainText, _blockSize);
                byte[] toEncrypt = XorIt(bCurrent, changedVector);
                byte[] cipherBiteText = EncryptBlock(toEncrypt, b_key);
                changedVector = cipherBiteText;
                WriteToFile(cipherBiteText, wPath);
        }
    }

    public void Decryption(byte[] CipherText, byte[] b_key, String dPath){
        while (d_index <CipherText.length){
            byte[] dCurrent = GetCurrentToDecrypt(CipherText, _blockSize);
            byte[] decryptBiteText = DecryptBlock(dCurrent, b_key);
            byte[] iDecrypted = XorIt(d_changedVector, decryptBiteText);
            d_changedVector = dCurrent;
            WriteToFile(iDecrypted, dPath);
        }
    }

    private byte[] GetCurrentToDecrypt(byte[] cipherText, int blockSize) {
        byte [] res = new byte[blockSize];
        if (cipherText.length - d_index > blockSize) {
            for (int i = 0; i < res.length; i++) {
                res[i] = cipherText[d_index];
                System.out.println(d_index);
                d_index++;
            }
        }
        else {
            int length = cipherText.length-d_index;
            for(int j=0; j<length; j++ ) {
                res[j] = cipherText[d_index];
                System.out.println(d_index);
                d_index++;
            }
        }
        return res;
    }

    private byte[] DecryptBlock(byte[] toDecrypt, byte[] b_key) {
        byte[] decryptedBlock = new byte[10];
        byte[] referenceKey = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        String sKey = new String (b_key);

        for (int i = 0; i < toDecrypt.length; i++) {
            switch (toDecrypt[i]) {
                case 'a':
                    decryptedBlock[i] = referenceKey[sKey.indexOf('a')];
                    continue;
                case 'b':
                    decryptedBlock[i] = referenceKey[sKey.indexOf('b')];
                    continue;
                case 'c':
                    decryptedBlock[i] = referenceKey[sKey.indexOf('c')];
                    continue;
                case 'd':
                    decryptedBlock[i] = referenceKey[sKey.indexOf('d')];
                    continue;
                case 'e':
                    decryptedBlock[i] = referenceKey[sKey.indexOf('e')];
                    continue;
                case 'f':
                    decryptedBlock[i] = referenceKey[sKey.indexOf('f')];
                    continue;
                case 'g':
                    decryptedBlock[i] = referenceKey[sKey.indexOf('g')];
                    continue;
                case 'h':
                    decryptedBlock[i] = referenceKey[sKey.indexOf('h')];
                    continue;
            }
            decryptedBlock[i] = toDecrypt[i];
        }
        return decryptedBlock;
    }

    private byte[] GetCurrent(byte[] l_sPlainText, int blockSize) {

        byte [] res = new byte[blockSize];
            if (l_sPlainText.length - index > blockSize) {
                for (int i = 0; i < res.length; i++) {
                    res[i] = l_sPlainText[index];
                    System.out.println(index);
                    index++;
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

    public int IndexOfNULL(byte [] text, String path){
        //byte[] withNUL = ReadText(path);
        int j = 0;
        for(int i=text.length-1; i>-1; i--){
            if(text[i] == '0')
                j=i;
        }
        return j;
    }
}