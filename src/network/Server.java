package network;

import java.net.ServerSocket;
import java.net.Socket;

import model.EmpDAO;

public class Server {
//	public static void main(String[] args) {System.out.println("Server 들어감");
	public void networkMethod() {
		System.out.println("서버 네트워크 관련 메소드 탔음");
		
		Socket socket = null; //Client와 통신하기 위한 Socket 생성
		User user = new User();  // 채팅방에 접속해 있는 Client 관리 객체
		ServerSocket serverSocket = null; //Client 접속을 받기 위한 ServerSocket
		
		int count = 0; // 쓰레드 할당을 위한 정수
		Thread thread[] = new Thread[10]; //10개의 쓰레드 할당, 즉 채팅방에 10명 접속가능
		
		try {
			serverSocket = new ServerSocket(9003); //서버 포트로 소켓을 연다
			
			while (true) {
				socket = serverSocket.accept(); //통신이 종료되기 전까지 연결
				

				/*
				 * receiver Class에서 implements Runnable을 사용했기 때문에
				 * Thread 객체 안에 쓰레드를 사용하려는 객체를 넣어줘서 객체화를 할 수 있습니다.
				 */
				thread[count] = new Thread(new Receiver(user, socket)); //Receiver class를 Thread에서 돌림
				
				thread[count].start(); //쓰레드 시작 
				count++;
			}
		} catch (Exception e) {
			System.out.println("server exception");
			e.printStackTrace();
		}
	} //method
//	} //main

}
