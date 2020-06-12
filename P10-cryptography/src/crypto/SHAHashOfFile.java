/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author ASUS
 */
public class SHAHashOfFile {
  public static String hashFil(String file, String alorithm){
      String hashed = "";
      try {
          MessageDigest sha = MessageDigest.getInstance(alorithm);
          FileInputStream fis = new FileInputStream(file);
          byte[] dataBytes = new byte[1024];
          int nRead;
          while ((nRead = fis.read(dataBytes)) != -1) {              
              sha.update(dataBytes, 0, nRead);
          }
          byte[] shaBytes = sha.digest();
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < shaBytes.length; i++) {
              sb.append(Integer.toString((shaBytes[i] & 0xff) +
                    0x100, 16).substring(1));
          }
          hashed = sb.toString();
      } catch (IOException | NoSuchAlgorithmException e) {
      }
      return hashed;
  }
    public static void main(String[] args) {
        String file = System.getProperty("user.dir")+File.separator+"manifest.mf"; 
        System.out.println("File\t: "+file);
        System.out.println("SHA-1\t: "+hashFil(file,"SHA-1"));
        System.out.println("SHA-256\t: "+hashFil(file, "SHA-256"));
        System.out.println("SHA-512\t: "+hashFil(file, "SHA-512"));
    }
}
