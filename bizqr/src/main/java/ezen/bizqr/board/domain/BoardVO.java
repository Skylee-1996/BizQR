package ezen.bizqr.board.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private long bno;
    private String userEmail;
    private String userName;
    private String title;
    private String content;
    private String regAt;
    private String modAt;
    private long readCount;
    private int cmtQty;
    private int hasFile;
}