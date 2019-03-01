package com.harry.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 将图片转换为Base64<br>
 * 将base64编码字符串解码成img图片
 * @创建时间 2015-06-01 15:50
 *
 */
public class Img2Base64Util {
	static Logger log = LoggerFactory.getLogger(Img2Base64Util.class);
    public static void main(String[] args) {
    	String imgBaseStr = getImgBaseStr("C:\\Users\\zhangte\\Desktop\\jihezhifu\\身份证正面.png");
//    	File file = new File("C:\\Users\\zhangte\\Desktop\\jihezhifu\\搜索条件.jpg");
//    	byte[] imageBytes = getContentFromFile("C:\\Users\\zhangte\\Desktop\\jihezhifu\\搜索条件.jpg");
//    	byte[] compressPicForScale = compressPicForScale(imageBytes, 46,"1001");
//    	System.out.println(imageBytes.length);
//    	System.out.println(compressPicForScale.length);
//    	String string = new String(Base64.encodeBase64(compressPicForScale));
    	System.out.println(imgBaseStr);
    	System.err.println(imgBaseStr.length());
        /*String imgFile = "C:\\Users\\zhangte\\Desktop\\jihezhifu\\搜索条件.jpg";//待处理的图片
        String imgbese=getImgStr(imgFile);
        System.out.println(imgbese.length());*/
//        System.out.println(imgbese);
//        String imgFilePath = "C:\\Users\\zhangte\\Desktop\\jihezhifu\\身份证正面1.jpg";//新生成的图片
//        generateImage(imgBaseStr,imgFilePath);

//        String imgFile = "d:\\yinhangka.jpg";//待处理的图片
//        String imgbese=getImgStr(imgFile);
//        System.out.println(imgbese.length());
//        System.out.println(imgbese);
//        String imgFilePath = "d:\\332.jpg";//新生成的图片
//        generateImage(imgbese,imgFilePath);
    }
    /**
     * 将图片转换成Base64编码
     * @param imgFile 待处理图片
     * @return
     */
    public static String getImgStr(String imgFile){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理


        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }

    public static String getImgBaseStr(String imgPath){
    	log.info("imgPath : "+imgPath);
    	byte[] imageBytes = getContentFromFile(imgPath);

    	byte[] compressPicForScale = compressPicForScale(imageBytes, 36,"abcd");
     	log.info("compressPicForScale : "+compressPicForScale);

    	String imgBaseStr = new String(Base64.encodeBase64(compressPicForScale));

    	//String imgBaseStr = getImgStr(imgPath);
    	log.info("imgBaseStr : "+imgBaseStr);
    	log.info("imgBaseStrLength : "+imgBaseStr.length());
        return imgBaseStr;
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr 图片数据
     * @param imgFilePath 保存图片全路径地址
     * @return
     */
    public static boolean generateImage(String imgStr,String imgFilePath){
        //
        if (imgStr == null) //图像数据为空
            return false;

        try
        {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    /**
	 * 压缩图片的方法
	 * @param imageBytes
	 * @param desFileSize
	 * @param imageId
	 * @return
	 */
	public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize, String imageId) {
        if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024) {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize / 1024);
        try {
            while (imageBytes.length > desFileSize * 1024) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
                Thumbnails.of(inputStream)
                        .scale(accuracy)
                        .outputQuality(accuracy)
                        .toOutputStream(outputStream);
                imageBytes = outputStream.toByteArray();
            }
            System.err.printf("【图片压缩】imageId={} | 图片原大小={}kb | 压缩后大小={}kb",
                    imageId, srcSize / 1024, imageBytes.length / 1024);
        } catch (Exception e) {
        	System.err.printf("【图片压缩】msg=图片压缩失败!", e);
        }
        return imageBytes;
    }

    /**
     * 自动调节精度(经验数值)
     *
     * @param size 源图片大小
     * @return 图片压缩质量比
     */
    private static double getAccuracy(long size) {
        double accuracy;
        if (size < 900) {
            accuracy = 0.85;
        } else if (size < 2047) {
            accuracy = 0.6;
        } else if (size < 3275) {
            accuracy = 0.44;
        } else {
            accuracy = 0.4;
        }
        return accuracy;
    }
	/**
	 * 根据文件名称获得字节数组
	 * @param fileName
	 * @return
	 */
   	public static byte[] getContentFromFile(String fileName) {
   		byte[] b = null;
   		try {
   	   		InputStream in = new FileInputStream(fileName);
   	   		b = new byte[in.available()];
   			while (in.read(b) != -1) {
   			}
   			in.close();
   		} catch (IOException e) {
   			e.printStackTrace();
   		} finally {
   		}
   		return b;
   	}

}