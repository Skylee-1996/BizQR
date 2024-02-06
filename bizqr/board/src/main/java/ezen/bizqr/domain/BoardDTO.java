package ezen.bizqr.domain;

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
