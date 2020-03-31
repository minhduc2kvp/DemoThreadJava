package com.minhvu.sockettest2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    static final int PORT = 8080;
    static ServerSocket serverSocket = null;
    private static List<Socket> clientSocket = new ArrayList<>();

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is listening...");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("New clients connected " + socket.toString());
                clientSocket.add(socket);
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static class ServerThread extends Thread {
        private Socket socket;

        public ServerThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                while (true){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String input = reader.readLine();

                    BufferedWriter bufferedWriter = null;
                    for (Socket st : clientSocket){
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(st.getOutputStream()));
                        bufferedWriter.write(input);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}