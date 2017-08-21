package com.webo;

import java.io.*;
import java.util.Queue;

/**
 * This class which take care read operation of file
 */
public class ReadProcessor extends Thread {
    private final Queue sharedQ;
    private final File file;

    public ReadProcessor(File file, Queue sharedQ) {
        super("Read Processor");
        this.file = file;
        this.sharedQ = sharedQ;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sharedQ) {
                //waiting condition
                while (sharedQ.size() == 0) {
                    try {
                        FileUtils.readFile(file);
                        sharedQ.wait();
                    } catch (InterruptedException ex) {
                        System.out.print("Error while Reading thread Operation"+ ex);
                    }
                }
                int number = (int) sharedQ.poll();
                sharedQ.notify();

//                //Termination condition
                if (number == ApplicationConstants.TotalUserSize - 1) {
                    break;
                }
            }
        }
    }
}