package com.felix.nio;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

public class ScannerTest {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.next());

        scanner = new Scanner(new File("/Users/finup/Downloads/zipkin1.json"));
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
        scanner.close();
    }
}
