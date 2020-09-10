package CatchMind1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Provider.Service;
import java.util.ArrayList;

public class ChatServer implements Runnable {
   ServerSocket ss;
   Service service;
   ArrayList<Service> list = new ArrayList<>();

   public ChatServer() {
      try {
         ss = new ServerSocket(7777);
         System.out.println("채팅 서버 서비스 중..");
         new Thread(this).start();
      } catch (IOException e) {
         System.out.println("ChatServer err : " + e);
      }
   }

   @Override
   public void run() { // ChatServer의 run
      while (true) {
         try {
            Socket socket = ss.accept();
            service = new Service(socket);
            service.start();
            service.Chat_name = service.in.readLine(); // 접속자의 이름 읽기
            // System.out.println(service.chat_name);

            service.messageSend("/c" + service.Chat_name);
            for (Service ser : list) {
               ser.messageSend("/c" + service.Chat_name);
               service.messageSend("/c" + service.Chat_name);
            }

            list.add(service);
         } catch (IOException e) {
            System.out.println("ChatServer run err : " + e);
         }
      }
   }

   // client 별 쳐리를 위한 내부 클래스
   class Service extends Thread {
      String Chat_name;
      BufferedReader in;
      OutputStream out;
      Socket socket;

      public Service(Socket socket) {
         try {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "euc-kr"));
            out = socket.getOutputStream();
         } catch (Exception e) {
            System.out.println("Service err : " + e);
         }
      }

      @Override
      public void run() {
         while (true) {
            try {
               String msg = in.readLine();
               if (msg.charAt(1) == 'r') { // 대화명 변경
                  messageAll("/r" + Chat_name + "-" + msg.substring(2));
                  Chat_name = msg.substring(2);
               } else if (msg.charAt(1) == 'q') { // 퇴장
                  try {
                     messageAll("/q" + Chat_name);
                     list.remove(this);
                     in.close();
                     out.close();
                     socket.close();
                  } catch (Exception e) {

                  }
                  break;
               } else if (msg.charAt(1) == 's') { // 귓속말 /s이름 - 메세지
                  String name = msg.substring(2, msg.indexOf('-')).trim();
                  for (Service ser : list) {
                     if (name.equals(ser.Chat_name)) {
                        ser.messageSend((Chat_name + ">(secret)" + msg.substring(msg.indexOf('-') + 1)));
                     }
                  }
               } else { // 일반메세지
                  messageAll(Chat_name + ">" + msg);
               }
            } catch (IOException e) {
               break;
            }
         }
      }

      public void messageAll(String msg) {
         try {
            for (int i = 0; i < list.size(); i++) {
               Service ser = list.get(i);
               ser.messageSend(msg);
            }
         } catch (Exception e) {
            System.out.println("messageAll err : " + e);
         }
      }

      public void messageSend(String msg) {
         try {
            out.write((msg + "\n").getBytes("euc-kr"));
         }  catch (IOException e) {
            System.out.println("messageSend err : " + e);
         }
      }
   }

   public static void main(String[] args) {
      new ChatServer();
   }

}