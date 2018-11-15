package 备份.irille;

import java.io.File;

import irille.pub.FileTools;

public class TestCreateOrderInfo {

	public static void main(String[] args) {
		File f = new File(FileTools.BASE_JAR);
		System.out.println(f.exists());
		System.out.println(FileTools.BASE_JAR);
	}
}
