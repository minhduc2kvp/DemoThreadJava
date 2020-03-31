package com.minhvu.sockettest2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient2 {


    public static void main(String[] args) {
        final String LOCALHOST = "localhost";
        final int PORT = 8080;

        Socket socket = null;

        try {
            socket = new Socket(LOCALHOST,PORT);
            System.out.println("Enter your name : ");
            String username = new Scanner(System.in).nextLine();
            new ReadThread(socket).start();
            new WriteThread(socket,username).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ReadThread extends Thread {
        private Socket socket;

        public ReadThread (Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true){
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String input = bufferedReader.readLine();
                    System.out.println(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class WriteThread extends Thread {
        private Socket socket;
        private String name;

        public WriteThread (Socket socket,String name){
            this.socket = socket;
            this.name = name;
        }

        @Override
        public void run() {
            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                while (true){
                    String output = new Scanner(System.in).nextLine();
                    bufferedWriter.write("[" + name + "]: " +output);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
