package com.security;

import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
//	// write your code here
        GenerateSig generateSig = new GenerateSig();
        VerifySig verifySig = new VerifySig();
        String options;
        String fileName;
        String signatureArguments;
        Scanner in = new Scanner(System.in);


        boolean runApp = true;

        while(runApp){
            System.out.println("1. Generate signature");
            System.out.println("2. Verify Signature");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");
            options = in.nextLine();
            if(options.equals("1")){
                System.out.print("Enter file name: ");
                fileName = in.nextLine();
                generateSig.createSignature(new String[]{fileName});
                System.out.println("You generated signature files");
            } else if(options.equals("2")){
                System.out.print ("Enter signature and filename(suepk sig input.txt): ");
                signatureArguments = in.nextLine();
                String[] stringArguments = signatureArguments.split(" ");
                verifySig.verify(stringArguments);
            } else if(options.equals("3")) {
                runApp = false;
            } else{
                System.out.println("You didn't choose an option.");
            }
        }



    }
}
