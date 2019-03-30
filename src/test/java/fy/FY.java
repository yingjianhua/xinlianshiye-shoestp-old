package fy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class FY {
	private static String[] Language={"zh_CN", "en", "ar", "fr", "ru", "es"};
	private static String path="E:/JAVA/workspace/wzdl/src/main/webapp/js/";
	private static String fileName="zh_CN.js";
	private static tranlatesTest test = new tranlatesTest();
	public static void main(String[] args) {
		for (String string : Language) {
		try {
			test.getJsonOject(path+fileName, string);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		//FY.readFile(fileName);
	}
	public static void readFile(String fileName){
		File file=new File(path+fileName);
		BufferedReader reader=null;
		String temp=null;
		int line=1;
		for (String string : Language) {
			fileName=string+".js";
		try{
				reader=new BufferedReader(new FileReader(file));
				while((temp=reader.readLine())!=null){
					//System.out.println("line"+line+":"+temp);
					if(temp.contains(":")&&temp.contains("\"")){
						String Translation=temp.substring(temp.indexOf("\"")+1, temp.lastIndexOf("\""));
						
						temp=temp.replace(Translation, "123");
					}
					writeListToFile(temp,path+fileName);
					line++;
				}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(reader!=null){
				try{
					reader.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	}
	private static void writeListToFile(String content,String pathFileName) {
		File file = new File(pathFileName);// 要写入的文件路径
		if (!file.exists()) {// 判断文件是否存在
			try {
				file.createNewFile();// 如果文件不存在创建文件
				//System.out.println("文件"+file.getName()+"不存在已为您创建!");
			} catch (IOException e) {
				System.out.println("创建文件异常!");
				e.printStackTrace();
			}
		} else {
			//System.out.println("文件"+file.getName()+"已存在!");
		}
		
			FileOutputStream fos = null;
			PrintStream ps = null;
			try {
				fos = new FileOutputStream(file,true);// 文件输出流	追加
				ps = new PrintStream(fos);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String string  = content + "\r\n";// +换行
			ps.print(string); // 执行写操作
			ps.close();	// 关闭流
			
		
		//System.out.println("文件写入完毕!");
	}
/*	
	//生成文件路径
	private static String path = "E:/JAVA/workspace/wzdl/src/main/webapp/js/";
	//文件路径+名称
	private static String filenameTemp;
	//文件名称
	private static String fileName; 
	public static void main(String[] args) throws IOException {
		filenameTemp="zh_CN.js";
		Map<Integer, String> map = FY.readFileByLines(path+filenameTemp);
		System.out.println(map);
		Map<String,String > map1 = new LinkedHashMap<String,String>();
		int num=0;
		for (Integer line: map.keySet()) {
			//System.out.println("Key=" + line + "    Value=" + map.get(line));
			
			if (map.get(line).endsWith("={")) {
				map1.put(map.get(line).substring(0, map.get(line).indexOf("=")),"={");
			}
			
			if (map.get(line).endsWith(":{")) {
				map1.put(map.get(line).substring(0, map.get(line).indexOf(":")), ":{");
			}
			
			if(map.get(line).endsWith("},")){
				map1.put("@@"+num, map.get(line));
				num++;
			}
			
			if(map.get(line).endsWith("}")){
				map1.put("##__"+num,map.get(line));
				num++;
			}
			
			if (map.get(line).endsWith("\",")) {
				if (map.get(line).contains(":")) {
					map1.put(
							map.get(line).substring(0, map.get(line).indexOf("\"")),
							map.get(line).substring(map.get(line).indexOf("\"")));
				}
			}
		}
		for(String Key:map1.keySet()){
			//System.out.println("Key=" + Key + "    Value=" + map1.get(Key));
			fileName="en"+".js";
			
			if(map1.get(Key).contains("={")){
				FY.writeListToFile(Key+map1.get(Key), path+fileName);
			}
			
			if(map1.get(Key).contains(":{")){
				FY.writeListToFile(Key+map1.get(Key), path+fileName);		
			}
			
			if(Key.contains("@@")){
				FY.writeListToFile(map1.get(Key), path+fileName);
			}
			
			if(Key.contains("##__")){
				FY.writeListToFile(map1.get(Key), path+fileName);
			}
			if(map1.get(Key).endsWith("\",")){
				FY.writeListToFile(Key+map1.get(Key), path+fileName);
			}
		}
	}
	*//**
	 * 读取文件内容
	 * @param fileName
	 * @return
	 *//*
	public static Map<Integer, String> readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		Map<Integer, String> map = new LinkedHashMap<Integer ,String>();
		try {
			System.out.println("以行为单位读取文件内容，一次读一行");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读一行，读入null时文件结束
			while ((tempString = reader.readLine()) != null) {
				// 把当前行号显示出来
				map.put(line, tempString);
//				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return map;
	}

	*//**
	 * 创建文件并向文件写入内容
	 * @param content
	 * @param pathFileName
	 *//*
	private static void writeListToFile(String content,String pathFileName) {
		File file = new File(pathFileName);// 要写入的文件路径
		if (!file.exists()) {// 判断文件是否存在
			try {
				file.createNewFile();// 如果文件不存在创建文件
				//System.out.println("文件"+file.getName()+"不存在已为您创建!");
			} catch (IOException e) {
				System.out.println("创建文件异常!");
				e.printStackTrace();
			}
		} else {
			//System.out.println("文件"+file.getName()+"已存在!");
		}
		
			FileOutputStream fos = null;
			PrintStream ps = null;
			try {
				fos = new FileOutputStream(file,true);// 文件输出流	追加
				ps = new PrintStream(fos);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String string  = content + "\r\n";// +换行
			ps.print(string); // 执行写操作
			ps.close();	// 关闭流
			
		
		//System.out.println("文件写入完毕!");
	}
	
	 * public String readToString(String fileName) { String encoding = "UTF-8";
	 * File file = new File(fileName); Long filelength = file.length(); byte[]
	 * filecontent = new byte[filelength.intValue()]; try { FileInputStream in =
	 * new FileInputStream(file); in.read(filecontent); in.close(); } catch
	 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e)
	 * { e.printStackTrace(); } try { return new String(filecontent, encoding);
	 * } catch (UnsupportedEncodingException e) { System.err.println(
	 * "The OS does not support " + encoding); e.printStackTrace(); return null;
	 * } }
	 */
}
