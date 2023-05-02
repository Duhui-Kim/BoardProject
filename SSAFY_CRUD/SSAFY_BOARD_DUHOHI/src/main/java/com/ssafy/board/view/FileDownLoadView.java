package com.ssafy.board.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownLoadView extends AbstractView {

	public FileDownLoadView() {
		setContentType("application/download; charset=UTF-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		////////////////////////////////////////////////////////////////////
		ServletContext ctx = getServletContext();
		String realPath = ctx.getRealPath("/resources/upload");
		
		Map<String, Object> fileInfo = (Map<String, Object>) model.get("downloadFile"); // 전송받은 모델(파일 정보)
        String fileName = (String) fileInfo.get("fileName");    // 파일 경로
        String orgFileName = (String) fileInfo.get("orgFileName"); // 원래 파일명
        
        File file = new File(realPath, fileName);
		////////////////////////////////////////////////////////////////////
        
        response.setContentType(getContentType());
        response.setContentLength((int) file.length());
        
        String header = request.getHeader("User-Agent");
        boolean isIE = header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1;
        String filename = null;
        // IE는 다르게 처리
        if (isIE) {
        	filename = URLEncoder.encode(orgFileName, "UTF-8").replaceAll("\\+", "%20");
        } else {
            filename = new String(orgFileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try { 
                    fis.close(); 
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        out.flush();
    }
}
