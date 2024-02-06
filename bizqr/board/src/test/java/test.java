import ezen.bizqr.domain.BoardVO;
import ezen.bizqr.domain.PagingVO;
import ezen.bizqr.repository.BoardMapper;
import ezen.bizqr.service.BoardServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


public class test {

    private BoardServiceImpl bs = new BoardServiceImpl();

    public void startTest() {
        for (int i = 0; i < 300; i++) {
            BoardVO bvo = new BoardVO();
            bvo.setTitle("Test Title " + i);
            bvo.setWriter("Test Writer");
            bvo.setContent("Test Content " + i);
            bdao.insert(bvo);
        }
    }
}
