package com.busoftinc.lkc.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;

import com.busoftinc.lkc.soap.CreateData;

public class Reader {
	
	private String filename;
	private String type;
	
	public Reader(String file, String type){
		this.filename = file;
		this.type = type;
	}
	
	public boolean read(){
		boolean status = false;
		
		 	String csvFile = this.filename;
	        BufferedReader br = null;
	        String line = "";

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {
	            	transform(line);
	            }
	            status = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		
		return status;
	}
	
	private void transform(String data) throws Exception{
		String dt[] = data.split(";");
		String row_data = "";
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (this.type.equalsIgnoreCase("lkc_order")){
			sb.append("<adin:field column=\"LKC_ID\">");
			sb.append("<adin:val>");
			sb.append(dt[0]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"CREATEDATE\">");
			sb.append("<adin:val>");
			try{
			sb.append(sdf2.format(sdf1.parseObject(dt[1])));
			}catch(Exception e){}
			sb.append("</adin:val>");
			sb.append("</adin:field>");

			sb.append("<adin:field column=\"UPDATEDATE\">");
			sb.append("<adin:val>");
			try{
			sb.append(sdf2.format(sdf1.parseObject(dt[3])));
			}catch(Exception e){}
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"AKHIR_ITERASI\">");
			sb.append("<adin:val>");
			sb.append(dt[5]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"SALES_REP\">");
			sb.append("<adin:val>");
			sb.append(dt[6]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"PERTEMUAN\">");
			sb.append("<adin:val>");
			sb.append(dt[7]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"TOTAL_NILAI\">");
			sb.append("<adin:val>");
			sb.append(dt[8]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"AVG_NILAI\">");
			sb.append("<adin:val>");
			sb.append(dt[9]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"PRESTASI\">");
			sb.append("<adin:val>");
			sb.append(dt[10]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"KEKUATAN\">");
			sb.append("<adin:val>");
			sb.append(dt[11]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"KEMAMPUAN\">");
			sb.append("<adin:val>");
			sb.append(dt[12]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"TINDAK_LANJUT\">");
			sb.append("<adin:val>");
			sb.append(dt[13]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"StartDate\">");
			sb.append("<adin:val>");
			try{
			sb.append(sdf2.format(sdf1.parseObject(dt[14])));
			}catch(Exception e){}
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"COMPLETEDATE\">");
			sb.append("<adin:val>");
			try{
			sb.append(sdf2.format(sdf1.parseObject(dt[15])));
			}catch(Exception e){}
			sb.append("</adin:val>");
			sb.append("</adin:field>");
		}else if (this.type.equalsIgnoreCase("lkc_outlet")){
			sb.append("<adin:field column=\"LKC_ID\">");
			sb.append("<adin:val>");
			sb.append(dt[0]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");		
			
			sb.append("<adin:field column=\"LKC_OUTLETNO\">");
			sb.append("<adin:val>");
			sb.append(dt[1]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"STORE_ID\">");
			sb.append("<adin:val>");
			sb.append(dt[3]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"STORE_NAME\">");
			sb.append("<adin:val>");
			sb.append(dt[4]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"StartDate\">");
			sb.append("<adin:val>");
			try{
			sb.append(sdf2.format(sdf1.parseObject(dt[5])));
			}catch(Exception e){}
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"COMPLETEDATE\">");
			sb.append("<adin:val>");
			try{
			sb.append(sdf2.format(sdf1.parseObject(dt[6])));
			}catch(Exception e){}
			sb.append("</adin:val>");
			sb.append("</adin:field>");			
		}else if (this.type.equalsIgnoreCase("lkc_soal")){
			sb.append("<adin:field column=\"LKC_ID\">");
			sb.append("<adin:val>");
			sb.append(dt[1]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"ID_MOD_SOAL\">");
			sb.append("<adin:val>");
			sb.append(dt[2]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"ID_MOD_POINT\">");
			sb.append("<adin:val>");
			sb.append(dt[3]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");			
		}else if (this.type.equalsIgnoreCase("lkc_soaloutlet")){
			sb.append("<adin:field column=\"LKC_OUTLET_ID\">");
			sb.append("<adin:val>");
			sb.append(dt[1]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"ID_MOD_SOAL\">");
			sb.append("<adin:val>");
			sb.append(dt[2]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");
			
			sb.append("<adin:field column=\"ID_MOD_POINT\">");
			sb.append("<adin:val>");
			sb.append(dt[3]);
			sb.append("</adin:val>");
			sb.append("</adin:field>");			
		}
		
		CreateData n = new CreateData();
		n.doImport(this.type, sb.toString());
	}

}
