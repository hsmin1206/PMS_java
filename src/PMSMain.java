import javax.swing.JFrame;

import network.Server;
import view.LoginView;

public class PMSMain extends JFrame{	
	LoginView login;
	
	public PMSMain() {
		LoginView login = new LoginView();
		login.setVisible(true);
		dispose();
	}

	public static void main(String[] args) {
		new PMSMain();
//		new PMSMain();
		//서버 실행 디비 서버 아이피일 경우에만 사용, 그 외엔 주석 처리 필요 할 것 같음
		Server	 server = new Server();
		server.networkMethod();
		// 아래 클라이언트 코드는 여기서 실행하면 순서가 맞지 않은 것 같아서 MainView class에서 구현했음
//		Client client = new Client();
//		client.clientMethod();
	}

}
