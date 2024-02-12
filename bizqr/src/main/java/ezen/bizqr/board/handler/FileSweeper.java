package ezen.bizqr.board.handler;

import ezen.bizqr.board.domain.FileVO;
import ezen.bizqr.board.repository.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableScheduling
public class FileSweeper {
    private final String BASE_PATH = "C:\\_my_spinrg_file\\_java\\_fileUpload\\";

    private final FileMapper mapper;

    //매 시간 매번 작동되도록 설정
    //@Scheduled(cron="0 * * * * *")
    public void fileSweeper() {
        log.info(">>> FileSweeper Running start~! >> : {} ", LocalDateTime.now()); //시작 시간 체크

        //1.db에 등록된 파일 전체 목록 가져오기
        List<FileVO> dbList = mapper.selectListAllFile();

        //2.저장소를 검색할 때 필요한 파일의 경로 => 리스트(실제 존재해야 할 리스트 값)
        List<String> currentFiles = new ArrayList<String>();
        for(FileVO fvo : dbList) {
            String filePath = fvo.getSaveDir()+ File.separator+fvo.getUuid();
            String fileName = fvo.getFileName();
            currentFiles.add(BASE_PATH+filePath+"_"+fileName);

            //3.이미지라면 썸네일 경로도 추가해줘야함
            if(fvo.getFileType() > 0) {
                currentFiles.add(BASE_PATH+filePath+"_th_"+fileName);
            }
        }
        log.info(">>> currentFiles >> {} "+ currentFiles);

        //4.오늘날짜를 반영할 폴더 구조 경로를 만들어야함
        LocalDate now = LocalDate.now();
        String today = now.toString();
        today = today.replace("-",File.separator);

        //5.경로를 기반으로 저장되어있는 파일을 검색
        //오늘날짜 폴더 안에 있는 전체 파일
        File dir = Paths.get(BASE_PATH+today).toFile(); //경로를 파일로 생성
        File[] allFileObjects = dir.listFiles(); //경로 안에 있는 파일을 배열로 구성

        //실제 저장되어 있는 파일과 db에 존재하는 파일을 비교하여 없는 파일은 삭제
        for(File file : allFileObjects) {
            String storedFileName = file.toPath().toString();
            if(!currentFiles.contains(storedFileName)) {
                //모든 리스트에서 존재하지 않는다면
                file.delete(); //파일 삭제
                log.info(">> delete file >>> {} ", storedFileName);
            }
        }
        log.info(">>> FileSweeper Running finish~! >> : {} ", LocalDateTime.now()); //끝나는 시간 체크
    }
}
