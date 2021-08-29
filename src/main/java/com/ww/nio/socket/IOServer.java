package com.ww.nio.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author xiaohua
 * @description IO实现服务端
 * @date 2021-8-27 16:06
 */
public class IOServer {

    public static void main(String[] args) {
        server();
    }

    public static void server() {
        ServerSocket serverSocket = null;
        InputStream inputStream = null;

        try {
            serverSocket = new ServerSocket(8081);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];

            while (true) {
                Socket socket = serverSocket.accept();
                SocketAddress address = socket.getRemoteSocketAddress();
                System.out.println("处理客户端" + address);
                inputStream = socket.getInputStream();
                while (-1 != (recvMsgSize = inputStream.read(recvBuf))) {
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                    System.out.println(new String(temp));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != serverSocket) {
                    serverSocket.close();
                }

                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
