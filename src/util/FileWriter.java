package util;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileWriter {
 
    private FileOutputStream fos;
     
    public void writeFile(MultipartFile file, String path, String fileName){
         
        try{
         
            byte fileData[] = file.getBytes();
            
            File dir = new File(path);
            if(!dir.exists()){
            	System.out.println("디렉토리가 존재하지 않습니다.");
            	dir.mkdir();
        	}
            
            fos = new FileOutputStream(path + "\\" + fileName, true);
             
            fos.write(fileData);
         
        }catch(Exception e){
             
            e.printStackTrace();
             
        }finally{
             
            if(fos != null){
                 
                try{
                    fos.close();
                }catch(Exception e){}
                 
                }
        }// try end;
         
    }// wirteFile() end;
}