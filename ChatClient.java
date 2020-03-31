package com.minhvu.sockettest2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        final String LOCALHOST = "localhost";
        final int PORT = 8080;

        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket(LOCALHOST,PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println("Enter your name : ");
            String username = new Scanner(System.in).nextLine();

            new ClientThread(bufferedWriter,username).start();
            new ReadThread(bufferedReader).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
