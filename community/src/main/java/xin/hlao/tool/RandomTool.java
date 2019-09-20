package xin.hlao.tool;

import java.util.Random;

public class RandomTool {
	public static String getRandomString(int length) { 
		         //定义字符
		         StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
		         StringBuffer sb = new StringBuffer(); 
		         Random r = new Random(); 
		         int range = buffer.length(); 
		         //循环字符长度
		         for (int i = 0; i < length; i ++) { 
		             //生成随机字符
		             sb.append(buffer.charAt(r.nextInt(range))); 
		         } 
		         return sb.toString(); 
		     }
		     public static void main(String[] args) {
		         
		         System.out.println(getRandomString(10));
		     }
}
