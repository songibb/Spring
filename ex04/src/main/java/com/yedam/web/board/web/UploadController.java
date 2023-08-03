package com.yedam.web.board.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	//개발환경과 배포환경이 다르기 때문에 경로는 app.properties에 따로 설정해둠

	@Value("${file.upload.path}")
	private String uploadPath;
	
	//페이지
	@GetMapping("upload")
	public void getUploadPage() {
		
	}
	
	
	//처리
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {   //@RequestPart 사용하려면 multipartResolver 등록해줘야함
	    
		List<String> imageList = new ArrayList<>();
		
	    for(MultipartFile uploadFile : uploadFiles){
	    	//uploadFile의 contentType을 확인(이미지인지)
	    	if(uploadFile.getContentType().startsWith("image") == false){
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	    	
	    	//경로 생성 코드
	    	//사용자가 보내온 내용에서 파일이 무엇인지 가져오는 것 (업로드파일이 가진 오리지널 네임 가져오기)
	        String originalName = uploadFile.getOriginalFilename();
	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
	        
	        System.out.println("fileName : " + fileName);
	        
	        //파일이름 충돌 방지
	        //날짜 폴더 생성 (날짜별로 관리)
	        String folderPath = makeFolder();
	        //UUID
	        String uuid = UUID.randomUUID().toString();
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        //시간을 초로 돌리면 중복할 수 없는 값
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        
	        //파일이름을 통한 실제 경로
	        String saveName = uploadPath + File.separator + uploadFileName;
	        
	        //자바가 인식할 수 있는 형태로 값을 변환
	        Path savePath = Paths.get(saveName);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath);
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        	//만약 trasferTo 사용하지 않으면 스트림을 열어서 처리해야함
	        	
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        
	        // DB에 저장
	        // fullname사용하면 안됨 -> uploadFileName이라는 변수 사용
	        //upload밑에 mapping된 주소가 필요
	        
	        imageList.add(setImagePath(uploadFileName));
	     }
	    
	    return imageList;
	}
	private String makeFolder() {
		//지역기준으로 오늘 날짜 가져오기
		// LocalDate를 문자열로 포멧
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		
		//자바는 "/"를 인식 못함 -> 자바가 인식하는 기준으로 변한
		String folderPath = str.replace("/", File.separator);
		
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		
		//생성완료되면 해당 경로 반환
		return folderPath;
	}
	
	private String setImagePath(String uploadFileName) {
		//운영체제에서 인식할 수 있도록 다시 "/"로 변환
		return uploadFileName.replace(File.separator, "/");
	}
	
}
