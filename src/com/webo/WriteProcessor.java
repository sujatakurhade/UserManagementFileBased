package com.webo;

import java.io.File;
import java.util.Queue;
import java.util.Scanner;

/**
 * This class will take write operation of file
 */
public class WriteProcessor extends Thread {
    private final Queue sharedQ;
    private final File file;

    public WriteProcessor(File file, Queue sharedQ) {
        super("Write Processor");
        this.file = file;
        this.sharedQ = sharedQ;
    }

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write Opearation started: ");
        System.out.println("Write Thread started execution");
        for (int i = 0; i < ApplicationConstants.TotalUserSize; i++) {
            synchronized (sharedQ) {
                //waiting condition
                try {
                    FileUtils.writeOperation(file, scan);
                    sharedQ.add(i);
                    sharedQ.notify();
                    sharedQ.wait();
                } catch (InterruptedException ex) {
                    System.out.print("Error while writing threading Operation"+ ex);
                }
            }
        }
        scan.close();
    }
}
