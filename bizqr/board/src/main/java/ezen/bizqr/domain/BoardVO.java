package ezen.bizqr.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
    private long bno;
    private String email;
    private String userName;
    private String title;
    private String content;
    private String regDate;
    private String modDate;
    private long readCount;
    private int cmtQty;
    private int hasFile;
}