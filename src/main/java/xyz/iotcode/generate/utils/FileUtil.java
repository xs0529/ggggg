package xyz.iotcode.generate.utils;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * File工具类，扩展 hutool 工具包
 * @author Zheng Jie
 * @date 2018-12-27
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {

    /**
     * 定义GB的计算常量
     */
    private static final int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    private static final int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    private static final int KB = 1024;

    /**
     * 格式化小数
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * MultipartFile转File
     * @param multipartFile
     * @return
     */
    public static File toFile(MultipartFile multipartFile){
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix="."+getExtensionName(fileName);
        File file = null;
        try {
            // 用uuid作为文件名，防止生成的临时文件重复
            file = File.createTempFile(IdUtil.simpleUUID(), prefix);
            // MultipartFile to File
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 删除
     * @param files
     */
    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 获取文件扩展名
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     * @param filename
     * @return
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 文件大小转换
     * @param size
     * @return
     */
    public static String getSize(int size){
        String resultSize = "";
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = DF.format(size / (float) GB) + "GB   ";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = DF.format(size / (float) MB) + "MB   ";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = DF.format(size / (float) KB) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }

    /**
     * 判断指定的文件或文件夹删除是否成功
     * @param FileName 文件或文件夹的路径
     * @return true or false 成功返回true，失败返回false
     */
    public static boolean deleteAnyone(String FileName){
        File file = new File(FileName);//根据指定的文件名创建File对象
        if ( !file.exists() ){  //要删除的文件不存在
            System.out.println("文件"+FileName+"不存在，删除失败！" );
            return false;
        }else{ //要删除的文件存在
            if ( file.isFile() ){ //如果目标文件是文件
                return deleteFile(FileName);
            }else{  //如果目标文件是目录
                return deleteDir(FileName);
            }
        }
    }


    /**
     * 判断指定的文件删除是否成功
     * @param fileName 文件路径
     * @return true or false 成功返回true，失败返回false
     */
    public static boolean deleteFile(String fileName){
        File file = new File(fileName);//根据指定的文件名创建File对象
        if (  file.exists() && file.isFile() ){ //要删除的文件存在且是文件
            if ( file.delete()){
                System.out.println("文件"+fileName+"删除成功！");
                return true;
            }else{
                System.out.println("文件"+fileName+"删除失败！");
                return false;
            }
        }else{
            System.out.println("文件"+fileName+"不存在，删除失败！" );
            return false;
        }
    }





    /**
     * 删除指定的目录以及目录下的所有子文件
     * @param dirName is 目录路径
     * @return true or false 成功返回true，失败返回false
     */
    public static boolean deleteDir(String dirName){
        if ( dirName.endsWith(File.separator) )//dirName不以分隔符结尾则自动添加分隔符
            dirName = dirName + File.separator;
        File file = new File(dirName);//根据指定的文件名创建File对象
        if ( !file.exists() || ( !file.isDirectory() ) ){ //目录不存在或者
            System.out.println("目录删除失败"+dirName+"目录不存在！" );
            return false;
        }
        File[] fileArrays = file.listFiles();//列出源文件下所有文件，包括子目录
        for ( int i = 0 ; i < fileArrays.length ; i++ ){//将源文件下的所有文件逐个删除
            FileUtil.deleteAnyone(fileArrays[i].getAbsolutePath());
        }
        if ( file.delete() )//删除当前目录
            System.out.println("目录"+dirName+"删除成功！" );
        return true;
    }
}
