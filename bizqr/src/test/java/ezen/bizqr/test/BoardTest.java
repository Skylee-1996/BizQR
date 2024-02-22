package ezen.bizqr.test;

import ezen.bizqr.board.domain.BoardVO;
import ezen.bizqr.board.repository.BoardMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BoardTest {

    @Autowired
    private BoardMapper mapper;

    @Test
    void contextLoads() {
        for(int i=0; i<300; i++) {
            BoardVO bvo = BoardVO.builder()
                    .title("Title "+i)
                    .email("admin@admin.com")
                    .nickName("admin")
                    .content("Test Content "+i)
                    .build();
            mapper.insert(bvo);
        }
    }

}
