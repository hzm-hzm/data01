package xin.hlao.tool;

import java.io.File;
import java.io.IOException;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class PictureTool {
	
	public static String savePicture(MultipartFile file)throws Exception {
		
//		如果没有就选择默认图片
		if(file.isEmpty()) {
			return "/image0/regist/default.jpg";
		}
		
//		获取图片名
		String filename=file.getOriginalFilename();
//		图片大小
		int size=(int) file.getSize();
		System.out.println(filename+"======>"+size);
		
//		保存的图片名称
		String savename=RandomTool.getRandomString(5)+"_"+filename;
		
//		利用图片名来哈希打散
		int hcode=filename.hashCode();
		String hex=Integer.toHexString(hcode);
		
//		获取user文件的绝对路径
		String root=ResourceUtils.getURL("static/image/user").getPath();
		File dirFire=new File(root, hex.charAt(0)+"/"+hex.charAt(1));
//		如果没有就自动生成
		dirFire.mkdirs();
		
		File dest=new File(dirFire,savename);
		if(dest.getParentFile().exists()) {
			dest.getParentFile().mkdir();
		}
		try {
			file.transferTo(dest);
			return "/image/user/"+hex.charAt(0)+"/"+hex.charAt(1)+"/"+savename;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		
	}
	
public static String saveTopicPicture(MultipartFile file)throws Exception {
		
//		如果没有就选择默认图片
		if(file.isEmpty()) {
			return "null,";
		}
		
//		获取图片名
		String filename=file.getOriginalFilename();
//		图片大小
		int size=(int) file.getSize();
		System.out.println(filename+"======>"+size);
		
//		保存的图片名称
		String savename=RandomTool.getRandomString(5)+"_"+filename;
		
//		利用图片名来哈希打散
		int hcode=filename.hashCode();
		String hex=Integer.toHexString(hcode);
		
//		获取user文件的绝对路径
		String root=ResourceUtils.getURL("static/image/topic").getPath();
		File dirFire=new File(root, hex.charAt(0)+"/"+hex.charAt(1));
//		如果没有就自动生成
		dirFire.mkdirs();
		
		File dest=new File(dirFire,savename);
		if(dest.getParentFile().exists()) {
			dest.getParentFile().mkdir();
		}
		try {
			file.transferTo(dest);
			return "/image/topic/"+hex.charAt(0)+"/"+hex.charAt(1)+"/"+savename;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		
	}
	
	
	
	
	
}
