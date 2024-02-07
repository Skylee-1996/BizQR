package ezen.bizqr.board.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {
    private long cno;
    private long bno;
    private String userEmail;
    private String content;
    private String regAt;
    private String modAt;
}
