package ezen.bizqr.board.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private long bno;
    private String email;
    private String nickName;
    private String title;
    private String content;
    private String mainImage;
    private String regAt;
    private String modAt;
    private long readCount;
    private int cmtQty;
    private int hasFile;
}