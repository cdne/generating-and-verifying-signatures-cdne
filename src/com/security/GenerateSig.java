package com.security;

import java.io.*;
import java.security.*;


public class GenerateSig {
    void createSignature(String[] args){
        if(args.length != 1){
            System.out.println("Usage: Enter the name of the file you want to create a signature for.");
        } else try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyPairGenerator.initialize(1024, random);

            KeyPair pair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();

            Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
            dsa.initSign(privateKey);

            FileInputStream fis = new FileInputStream(args[0]);
            BufferedInputStream bufin = new BufferedInputStream(fis);
            byte[] buffer = new byte[1024];
            int len;
            while((len = bufin.read(buffer)) >= 0){
                dsa.update(buffer, 0, len);
            };
            bufin.close();

            byte[] realSig = dsa.sign();

            FileOutputStream sigfos = new FileOutputStream("sig");
            sigfos.write(realSig);
            sigfos.close();

            byte[] key = publicKey.getEncoded();
            FileOutputStream keyfos = new FileOutputStream("suepk");
            keyfos.write(key);
            keyfos.close();

        } catch(Exception e ){
            System.err.println("Caught exception " + e.toString());
        }
    }
}
