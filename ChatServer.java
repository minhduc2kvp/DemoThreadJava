package com.minhvu.sockettest2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ChatServer {

    public static void main(String[] args) {
        final int PORT = 8080;
        ServerSocket serverSocket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is conneting");

            socket = serverSocket.accept();
            System.out.println("New client connected");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));



            while (true){
//                nhận dữ liệu do client gửi đến
                String input = bufferedReader.readLine();

                System.out.println(input);

//                bufferedWriter.write(input);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();

                new ClientThread(bufferedWriter,"server").start();
//                new ReadThread(bufferedReader);


                if (input.contains("bye")){
                    break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}