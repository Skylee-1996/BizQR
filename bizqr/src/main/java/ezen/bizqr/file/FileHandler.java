package ezen.bizqr.file;

import ezen.bizqr.file.FileVO;
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
    private final String UP_DIR = "C:\\_bizqr_fileUpload\\"; //경로


    public FileVO uploadFile(MultipartFile file){
        LocalDate date = LocalDate.now();
        String today = date.toString();
        today = today.replace("-", File.separator);
        File folders = new File(UP_DIR, today);
        if(!folders.exists()) {
            folders.mkdirs();
        }

        FileVO fvo = new FileVO();
        fvo.setSaveDir(today);
        fvo.setFileSize(file.getSize());

        String orgFilename = file.getOriginalFilename();
        String onlyFileName = orgFilename.substring(
                orgFilename.lastIndexOf(File.separator)+1);
        String extension = orgFilename.substring(orgFilename.lastIndexOf(".") + 1);  // 확장자
        fvo.setFileName(onlyFileName);

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fvo.setUuid(uuid);
        String saveFilename = uuid + "." + extension;
        File storeFile = new File(folders, saveFilename);
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

        return fvo;
    }


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
            fvo.setSaveDir(today);
            fvo.setFileSize(file.getSize());

            String orgFilename = file.getOriginalFilename();
            String onlyFileName = orgFilename.substring(
                    orgFilename.lastIndexOf(File.separator)+1);
            String extension = orgFilename.substring(orgFilename.lastIndexOf(".") + 1);  // 확장자
            fvo.setFileName(onlyFileName);

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fvo.setUuid(uuid);
            String saveFilename = uuid + "." + extension;
            File storeFile = new File(folders, saveFilename);
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
        return mimeType.startsWith("image");
    }

}
