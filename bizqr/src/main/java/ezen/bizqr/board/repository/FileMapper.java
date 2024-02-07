package ezen.bizqr.board.repository;

import ezen.bizqr.board.domain.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    int insertFile(FileVO fvo);
    List<FileVO> getFileList(long bno);
    int deleteFile(String uuid);

    List<FileVO> selectListAllFile();
}
