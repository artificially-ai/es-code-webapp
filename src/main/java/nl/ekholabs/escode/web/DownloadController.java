package nl.ekholabs.escode.web;
 

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import javax.servlet.http.HttpServletResponse;  

import java.io.*;  
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
  
@Controller  
public class DownloadController{  
    @RequestMapping("/downloadGenerated")  
    public void fileDownloadGenerated(HttpServletResponse response)throws IOException,FileNotFoundException{  
      
        //get the name of the first file
        File rootFolder = new File(EsCode.ROOT_GENERATE);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
            .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
            .map(f -> f.getName())
            .collect(Collectors.toList());
        String firstFileName = fileNames.get(0);
        File fisrtFileFolder=new File(EsCode.ROOT_GENERATE+"/");
//        /*for debug*/
//        System.out.println(fisrtFileFolder.getAbsolutePath());
        
        //for download method entity below       
        try{          
          String realPath=fisrtFileFolder.getAbsolutePath()+"/"+firstFileName;
          response.setHeader("content-disposition", "attachment;filename="+firstFileName);
          InputStream in = new FileInputStream(realPath);
          int len = 0;
          byte[] buffer = new byte[1024];
          OutputStream out = response.getOutputStream();
          while ((len = in.read(buffer)) > 0) {
            out.write(buffer,0,len);
          }
          in.close();
          out.close();       
        }catch(Exception e){
          e.printStackTrace();
        }
    }  
    
    @RequestMapping("/downloadParsed")  
    public void fileDownloadParsed(HttpServletResponse response)throws IOException,FileNotFoundException{  
      
        //get the name of the first file
        File rootFolder = new File(EsCode.ROOT_PARSE);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
            .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
            .map(f -> f.getName())
            .collect(Collectors.toList());
        String firstFileName = fileNames.get(0);
        File fisrtFileFolder=new File(EsCode.ROOT_PARSE+"/");
        
        //for download method entity below       
        try{          
          String realPath=fisrtFileFolder.getAbsolutePath()+"/"+firstFileName;
          response.setHeader("content-disposition", "attachment;filename="+firstFileName);
          InputStream in = new FileInputStream(realPath);
          int len = 0;
          byte[] buffer = new byte[1024];
          OutputStream out = response.getOutputStream();
          while ((len = in.read(buffer)) > 0) {
            out.write(buffer,0,len);
          }
          in.close();
          out.close();       
        }catch(Exception e){
          e.printStackTrace();
        }
    }  
} 