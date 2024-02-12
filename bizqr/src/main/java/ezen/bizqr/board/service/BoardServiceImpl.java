package ezen.bizqr.board.service;

import ezen.bizqr.board.domain.BoardDTO;
import ezen.bizqr.board.domain.BoardVO;
import ezen.bizqr.board.domain.FileVO;
import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.board.repository.BoardMapper;
import ezen.bizqr.board.repository.CommentMapper;
import ezen.bizqr.board.repository.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
    private final BoardMapper mapper;
    private final FileMapper fileMapper;
    private final CommentMapper cmapper;

    @Transactional
    @Override
    public void register(BoardVO bvo) {
        int isOk = mapper.insert(bvo);

        }

    @Override
    public List<BoardVO> getList(PagingVO pgvo) {
        List<BoardVO> list = mapper.getList(pgvo);
        log.info("board list {}",list);
        return list;
    }

    @Transactional
    @Override
    public BoardDTO getDetail(long bno) {
        BoardDTO bdto = new BoardDTO();
        bdto.setBvo(mapper.getDetail(bno));
        bdto.setFlist(fileMapper.getFileList(bno));
        return bdto;
    }

    @Transactional
    @Override
    public void modify(BoardVO bvo) {
           mapper.update(bvo);
    }

    @Transactional
    @Override
    public void remove(long bno) {
        cmapper.delete(bno);
        mapper.delete(bno);
    }

    @Override
    public int getTotalCount(PagingVO pgvo) {
        return mapper.getTotalCount(pgvo);
    }

    @Override
    public int removeToFile(String uuid) {
        return fileMapper.deleteFile(uuid);
    }
}

