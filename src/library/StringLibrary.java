package library;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringLibrary {
	
		/*
	    Message Digest (MD) là một loại hàm băm 1 chiều đặc biệt
	    Class MessageDigest hổ trợ 2 thuật toán Message Digest phổ biến là MD5 và SHA
	    Class này cung cấp phương thức "getInstance()" để tạo đối tượng MessageDigest,
	    phương thức này nhận 1 giá trị tham số đầu vào kiểu String chỉ ra "tên thuật toán" dành cho MD 
	    các giá trị này là : MD2 , MD5 ,SHA-1 ...
	    Ví dụ : MessageDigest md = MessageDigest.getInstace(“MD5”); 
	    + void update(byte[] input) : tạo lại digest bởi mảng byte chỉ ra.
	
	   */
	
	public static String MD5(String str ){ //truyen vao tham so la chuoi nguoi dung nhap vao
		//update lai chuoi ma hao cua nguoi dung ve dang MD5
		String result = null;
		try {
		MessageDigest md =	MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		//khi update xong chuyen ve duoi dang chuoi so
		BigInteger bigInteger = new BigInteger(1, md.digest());//1 la tra ve 1 chuoi so dang number
		result = bigInteger.toString(16); //truyen vao 16 de dong bo voi mysql(so ki tu bang nhau)
		//thu in ra
		//System.out.println(bigInteger);
		//System.out.println(result);
		
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
		public static void main(String[] args) {
			System.out.println(MD5("123456"));
		}
	
	
	
}
