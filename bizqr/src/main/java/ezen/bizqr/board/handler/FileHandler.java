package ezen.bizqr.board.handler;

import ezen.bizqr.board.domain.FileVO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class FileHandler {
    private final String UP_DIR = "C:\\_my_spinrg_file\\_java\\_fileUpload\\"; //경로

    public List<FileVO> uploadFiles(MultipartFile[] files){
        List<FileVO> flist = new ArrayList<>();
        LocalDate date = LocalDate.now();
        String today = date.toString();
        today = today.replace("-", File.separator);
        File folders = new File(UP_DIR, today);
        if(!folders.exists()) {
            folders.mkdirs();
        }
        for(MultipartFile file : files) {
            FileVO fvo = new FileVO();
            fvo.setStoreId(today);
            fvo.setFileSize(file.getSize());

            String originalFileName = file.getOriginalFilename();
            String onlyFileName = originalFileName.substring(
                    originalFileName.lastIndexOf(File.separator)+1);
            fvo.setFileName(onlyFileName);

            UUID uuid = UUID.randomUUID();
            fvo.setUuid(uuid.toString());
            String fullFileName = uuid.toString()+"_"+onlyFileName;
            File storeFile = new File(folders, fullFileName);
            try {
                file.transferTo(storeFile);
                if(isImageFile(storeFile)) {
                    fvo.setFileType(1);
                    File thumbnail = new File
                            (folders, uuid.toString()+"_th_"+onlyFileName);
                    Thumbnails.of(storeFile).size(75, 75).toFile(thumbnail);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.info("파일 저장 오류.......");
            }
            flist.add(fvo);
        }
        return flist;
    }

    private boolean isImageFile(File file) throws IOException {
        String mimeType = new Tika().detect(file);
        return mimeType.startsWith("image") ? true : false;
    }

}
