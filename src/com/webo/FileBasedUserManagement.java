package com.webo;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *This is the Main class for File Based User Management
 */
public class FileBasedUserManagement {

    public static final String USER_HOME = "user.home";
    public static final String USER_TXT = "user.txt";

    public static void main(String args[]) throws InterruptedException, IOException {

        final Queue sharedQ = new LinkedList();
        String userHome = System.getProperty(USER_HOME);
        File textFile = new File(userHome, USER_TXT);
        textFile.createNewFile();
        Thread writeProcessor = new WriteProcessor(textFile, sharedQ);
        Thread readProcessor = new ReadProcessor(textFile, sharedQ);

        writeProcessor.start();
        readProcessor.start();

    }
}