package ezen.bizqr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BizqrApplicationTests {

	private BoardDAO bdao;
	@Test
	void contextLoads() {
		for(int i=0;i<300;i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Test Title "+i);
			bvo.setWriter("Test Writer");
			bvo.setContent("Test Content "+i);
			bdao.insert(bvo);
		}
	}

}
