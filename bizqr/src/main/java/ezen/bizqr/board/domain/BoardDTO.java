package ezen.bizqr.board.domain;

import ezen.bizqr.file.FileVO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class BoardDTO {
    private BoardVO bvo;
    private List<FileVO> flist;
}
