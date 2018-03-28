package com.busoftinc.lkc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.busoftinc.lkc.csv.Reader;

public class Main {
	
	public static void main(String[] a) {
		String[] cls = null;
		System.out.println("==== LKC INTEGRATOR ============");
		System.out.println("==== DON'T CLOSE THIS WINDOW ===");
		System.out.println("================================");
		while (true){
			try {
				process();
				Thread.sleep(60000);
				String[] c = {"cmd","/c","cls"};
				Runtime.getRuntime().exec(c);
				System.out.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void process() {
		Reader n = null;
		try {
			File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
			String loc_dir = jarDir.getAbsolutePath();

			File folder = new File(loc_dir);
			File[] listOfFiles = folder.listFiles();
			String filename = "";
			String filename2 = "";
			String type = "";
			File backup = null;
			File proc = null;
			boolean proc_status = true;
			for (int i = 0; i < listOfFiles.length; i++) {
				type = "";
				if (listOfFiles[i].isFile()) {
					filename = listOfFiles[i].getName().toLowerCase();
					//System.out.println("filename : " + filename);
					if (filename.endsWith("csv")) {
						System.out.println("Processing file : " + filename);
						
						try{
							filename2 = filename.split("_")[1].toLowerCase();	
						}catch(Exception e){
							System.out.println("invalid filename : " + filename + ", " + e.getMessage());
							backup = new File(loc_dir+"\\error");
							if (!backup.exists()){
								backup.mkdir();
							}
							
							proc = new File(loc_dir+"\\"+filename);
							proc.renameTo(new File(loc_dir+"\\error\\"+proc.getName()+"."+new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime())));
							continue;
						}
						
						if ("order.csv".equalsIgnoreCase(filename2)){
							type = "lkc_order";
						}else if ("outlet.csv".equalsIgnoreCase(filename2)){
							type = "lkc_outlet";
						}else if ("soal.csv".equalsIgnoreCase(filename2)){
							type = "lkc_soal";
						}else if ("soaloutlet.csv".equalsIgnoreCase(filename2)){
							type = "lkc_soaloutlet";
						}
						if (!"".equalsIgnoreCase(type)){
							n = new Reader(loc_dir+"\\"+filename,type);
							proc_status = n.read();
							
							if (proc_status){
								backup = new File(loc_dir+"\\backup");
								if (!backup.exists()){
									backup.mkdir();
								}
							
								proc = new File(loc_dir+"\\"+filename);
								proc.renameTo(new File(loc_dir+"\\backup\\"+proc.getName()+"."+new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime())));
							}else{
								backup = new File(loc_dir+"\\error");
								if (!backup.exists()){
									backup.mkdir();
								}
							
								proc = new File(loc_dir+"\\"+filename);
								proc.renameTo(new File(loc_dir+"\\error\\"+proc.getName()+"."+new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime())));								
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
