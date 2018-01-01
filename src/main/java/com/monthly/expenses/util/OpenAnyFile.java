package com.monthly.expenses.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenAnyFile {

    public static void openAnyFile(String fullPath,ServletContext context,HttpServletRequest request,HttpServletResponse response,String type,String fileName){

        try{
            final int BUFFER_SIZE = 4096;
            File downloadFile=new File(fullPath);

            FileInputStream inputStream = new FileInputStream(downloadFile);

            // get MIME type of the file
            String mimeType = context.getMimeType(fullPath);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = type;
            }
            System.out.println("MIME type: " + mimeType);

            response.setContentType(type);
            response.setHeader("Content-disposition", "attachment; filename="+fileName);

            // get output stream of the response
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            // write bytes read from the input stream into the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outStream.close();
        }catch(Exception e){
            e.getLocalizedMessage();
        }


    }
}
