package net.daum.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;


// 2024-12-06 게시판 첨부파일(이진파일) 관련 Upload Code 작성 

@Controller
@RequestMapping("/bbs")
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		// 리턴타입이 없는 void 타입이면 매핑 주소인 /uploadForm View 페이지 파일명으로 설정된다.
	}
	
	@PostMapping("/uploadFormOK")
	public void uploadFormOK(MultipartFile[] uploadFile, Model model, HttpServletRequest request) {
		/*
		 * 스프링에서 MultipartFile 타입 API를 제공해서 업로드 되는 파일 데이터를 쉽게 처리한다. 다중 업로드 파일은 배열로 받는다
		 * <input type="file" name="uploadFile" />의 네임파라미터이름 uploadFile과 매개변수명은 같게 지정한다.
		 */
		
		String uploadFolder = request.getSession().getServletContext().getRealPath("upload");
		// 업로드 된 첨부파일을 저장할 실제 경로를 반환
		
		System.out.println("첨부파일 경로 : " + uploadFolder);
		
		for(MultipartFile multipartFile:uploadFile) {
			System.out.println("==============================================>");
			System.out.println("UpLoad File Name : "+multipartFile.getOriginalFilename());
			// 업로드 된 원래 원본 파일 명
			
			System.out.println("Upload File Size : "+multipartFile.getSize());
			// 업로드 파일 크기
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
				// 업로드 되는 원래 파일명으로 upload폴더에 실제 업로드
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 비동기식 이진파일 업로드 폼
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
	}
	
	//비동기식 업로드
    @PostMapping("/uploadAjaxOK")
    public void uploadAjaxOK(MultipartFile[] uploadFile, HttpServletRequest request) {
    	
    	System.out.println("update Ajax post...");
    	String uploadFolder = request.getSession().getServletContext().getRealPath("upload");
    	
    	for(MultipartFile multipartFile : uploadFile) {
    		
    		System.out.println("==========================>");
    		System.out.println("첨부된 원본파일명 : "+ multipartFile.getOriginalFilename());
    		System.out.println("업로드된 파일크기 : "+ multipartFile.getSize());
    		
    		String uploadFileName = multipartFile.getOriginalFilename();
    		
    		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
    		/* IE 경우 전체파일 경로가 전송되기 때문에 마지막 \를 맨 오른쪽부터 찾아서 가장 먼저 나오는 해당문자 \의 위치번호를 맨 왼쪽 첫문자를
    		 * 0부터 카운터 해서 해당 문자인 \의 위치번호를 구한다. 이 위치번호에 +1을 하면 실제 첨부된 파일명만 구할수 있다. 즉 \이후부터
    		 * 마지막 문자까지 구한다.
    		 */
    		System.out.println("Only File Name : "+uploadFileName);
    		
    		File saveFile = new File(uploadFolder, uploadFileName);
    		
    		try {
    			multipartFile.transferTo(saveFile);
    			// upload폴더에 첨부한 파일 실제 업로드 함
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }//uploadAjaxOK() 
}
