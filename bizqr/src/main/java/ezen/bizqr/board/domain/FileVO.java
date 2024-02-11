package ezen.bizqr.board.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class FileVO {
    private String uuid;
    private String saveDir;
    private String fileName;
    private String storeId;
    private int fileType;
    private long bno;
    private long fileSize;
    private String regAt;
}
