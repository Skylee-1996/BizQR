package ezen.bizqr.service;

import ezen.bizqr.domain.BoardDTO;
import ezen.bizqr.domain.BoardVO;
import ezen.bizqr.domain.FileVO;
import ezen.bizqr.domain.PagingVO;
import ezen.bizqr.repository.BoardMapper;
import ezen.bizqr.repository.FileMapper;
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

    @Transactional
    @Override
    public void register(BoardDTO bdto) {
        int isOk = mapper.insert(bdto.getBvo());
        if(isOk > 0 && bdto.getFlist().size() > 0) {
            //파일이 존재한다면...
            long bno = mapper.getBno();
            for(FileVO fvo : bdto.getFlist()) {
                fvo.setBno(bno);
                isOk *= fileMapper.insertFile(fvo);
            }
        }
    }

    @Override
    public List<BoardVO> getList(PagingVO pgvo) {

        return mapper.getList(pgvo);
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
    public void modify(BoardDTO boardDTO) {
        int isOk = mapper.update(boardDTO.getBvo());
        try {
            if(isOk > 0 && boardDTO.getFlist().size()>0) {
                long bno = boardDTO.getBvo().getBno();
                for(FileVO fvo: boardDTO.getFlist()) {
                    fvo.setBno(bno);
                    isOk *= fileMapper.insertFile(fvo);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            log.info("file null");
        };

    }

    @Override
    public void remove(long bno) {
        // TODO Auto-generated method stub
        mapper.delete(bno);
    }

    @Override
    public int getTotalCount(PagingVO pgvo) {
        // TODO Auto-generated method stub
        return mapper.getTotalCount(pgvo);
    }

    @Override
    public int removeToFile( String uuid) {
        return fileMapper.deleteFile(uuid);
    }
}
