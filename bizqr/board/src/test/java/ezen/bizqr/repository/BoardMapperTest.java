package ezen.bizqr.repository;

import ezen.bizqr.domain.BoardVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BoardMapperTest {
    @Autowired
    private BoardMapper mapper;

    @Test
    void insert() {
        for (int i = 0; i < 300; i++) {
            BoardVO bvo = new BoardVO();
            bvo.setTitle("Test Title " + i);
            bvo.setEmail("Test Writer");
            bvo.setContent("Test Content " + i);
            mapper.insert(bvo);
        }
    }
}