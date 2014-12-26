package com.vl.training.sample;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
final public class TransactionUsingThread {
    private TransactionUsingThread() {
    }
    public static void main(final String[] args) throws IOException {
        String name = Thread.currentThread().getName();
        System.out.println("Thread : " + name + " Thread started ");
        //reading directory as input file from commandline arguments
        File directory = new File(args[0]);
        //storing list of files in an array named File
        File[] listfiles = directory.listFiles();
        Transaction t = new Transaction();
        //creating an arry for threads with the size as no.of.files in the directory 
        ThreadTransaction[] tt = new ThreadTransaction[listfiles.length];
        System.out.print("no of files in the given directory : ");
        System.out.println(listfiles.length);
        for (int i = 0; i < listfiles.length; i++) {
            String filename = listfiles[i].getName();
            if (filename.endsWith(".txt") || filename.endsWith(".TXT")) {
                Scanner sc = new Scanner(listfiles[i]);
                tt[i] = new ThreadTransaction(t , sc);
                System.out.print("file " + i + " name : ");
                System.out.println(listfiles[i].getName());
                //calling thread[i] for i th file
                tt[i].start();
            }
        }
        for (int i = 0; i < listfiles.length; i++) {
            try {
                tt[i].join();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        //displaying the total transactions at end
        t.printDetails();
        System.out.println("main thread ended");
    }
}
class ThreadTransaction extends Thread {
    private Transaction t;
    private Scanner sc;
    private String name;
    ThreadTransaction(final Transaction trans , final Scanner scan) {
        this.t = trans;
        this.sc = scan;
    }
    //to print currently executing thread name
    public void printMsg() {
        Thread thread = Thread.currentThread();
        name = thread.getName();
        //System.out.println("name=" + name);
        System.out.println("Thread :" + name + "started");
    }
    //thread starts from here
    public void run() {
        printMsg();
        try {
            t.doTransaction(sc);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Thread :" + name + "ended");
    }
}
class Transaction {
    private Map<String, Double> m = new HashMap<String, Double>();
    public  synchronized void doTransaction(final Scanner sc)
           throws IOException {
        boolean temp = false;
        //System.out.println("accountnumber transtype  amount");
        while (sc.hasNext()) {
            String accountnumber = sc.next();
            System.out.print(accountnumber + "\t");
            String transtype = sc.next();
            System.out.print(transtype + "\t");
            double balance = sc.nextDouble();
            System.out.println(balance + "\t");
            Double amount = m.get(accountnumber);
            if (amount == null) {
                if (transtype.equals("W")) {
                    balance = 0 - balance;
                }
                m.put(accountnumber , balance);
            } else {
                if (transtype.equals("D")) {
                    balance = amount + balance;
                    temp = true;
                }
                if (transtype.equals("W")) {
                    balance = amount - balance;
                    temp = true;
                }
            }
            if (temp) {
                m.put(accountnumber , balance);
            }
        }
    }
    void printDetails() {
        System.out.println("account details after doing transaction");
        System.out.println("AccNo \t Amount");
        Set<Entry<String, Double>> entries = m.entrySet();
        for (Entry<String, Double> ent : entries) {
            System.out.println(ent.getKey() + "\t" + ent.getValue());
        }
    }
}