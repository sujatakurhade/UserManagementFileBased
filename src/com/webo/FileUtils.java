package com.webo;

import java.io.*;
import java.util.Scanner;

/**
 * This is file util class which contains all File Operations
 */
public final class FileUtils {

    public static void writeOperation(File file, Scanner scan) {
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter in = new BufferedWriter(writer);
            String[] temp = null;
            System.out.println("---------------------------------------------------");
            StringBuilder userInfo = new StringBuilder();
            System.out.println("Enter Name:");
            userInfo.append(scan.nextLine());
            userInfo.append(",");

            System.out.println("Enter Email:");
            userInfo.append(scan.nextLine());
            userInfo.append(",");

            System.out.println("Enter Contact Number:");
            userInfo.append(scan.nextLine());
            userInfo.append(",");

            System.out.println("Enter address:");
            userInfo.append(scan.nextLine());

            System.out.println("User Created Sucessfully");

            in.write(userInfo.toString());
            in.newLine();
            in.close();

        } catch (IOException ex) {
           System.out.print("Error while writing Operation"+ ex);
        }
    }

    public static void readFile(File file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader out = new BufferedReader(reader);
            String str;
            int count = countLines(file);
            if (count != 0 && count % 3 == 0) {
                System.out.println("=====================================================");
                System.out.println("Reading User Info");
                while ((str = out.readLine()) != null) {
                    System.out.println(str);
                }
                System.out.println("=====================================================");
            }
            out.close();
        } catch (IOException ex) {
            System.out.print("Error while Reading Operation"+ ex);
        }
    }

    private static int countLines(File file) throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader(file));
        lnr.skip(Long.MAX_VALUE);
        int count = lnr.getLineNumber();
        // LineNumberReader object should be closed to prevent resource leak
        lnr.close();
        return count;
    }

}
