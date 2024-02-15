package ezen.bizqr.file;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class FileVO {
    private String uuid;
    private long bno;
    private String storeId;
    private String menuId;
    private String saveDir;
    private String fileName;
    private int fileType;
    private long fileSize;
    private String regAt;
}


