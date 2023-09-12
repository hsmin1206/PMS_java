package network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

/*
 * extends Thread, implements Runnable은 쓰레드 구현을 위해 사용합니다.
 * extends Thread는 Thread를 상속받고 객채화한 뒤에 객체명.start()를 통해 사용하며,
 * implements Runnable은 Thread 객체 안에 쓰레드를 사용하려는 객체를 넣어줘서 객체화한 뒤 사용합니다.
 * extends Thread와 implements Runnable은 비슷합니다.
 * 하지만 implements Runnable을 사용하면 다중 상속이 가능합니다.
 * 
 */

public class Send implements Runnable{
	
	DataOutputStream out;
	
	//Send Class에서의 in2는 채팅 내용을 받아오는 역할을 합니다.
	BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
	
	public Send(DataOutputStream out) {System.out.println("send 생성자 탔음 " + out);
		this.out = out;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				String msg = in2.readLine(); //메시지를 받아오면
				out.writeUTF(msg); //msg 출력
			} catch (Exception e) {
				System.out.println("send exception");
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
