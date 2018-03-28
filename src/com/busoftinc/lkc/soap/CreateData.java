package com.busoftinc.lkc.soap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

public class CreateData {
	private static final String WSDL_ADDRESS = "http://berp.busoftinc.com:9808/ADInterface/services/ModelADService";
	private static final String LKC_ORDER = "lkc_order";
	private static final String LKC_OUTLET = "lkc_outlet";
	private static final String LKC_SOAL = "lkc_soal";
	private static final String LKC_SOALOUTLET = "lkc_soaloutlet";
	private static final String soapCredential = ""
			+ "<adin:ADLoginRequest> " +
              " <adin:user>mdlzAdmin</adin:user> " +
              " <adin:pass>mdlzAdmin</adin:pass> " +
              " <adin:lang>192</adin:lang> " +
              " <adin:ClientID>1000001</adin:ClientID> " +
              " <adin:RoleID>1000002</adin:RoleID> " +
              " <adin:OrgID>1000001</adin:OrgID> " +
              " <adin:WarehouseID>1000229</adin:WarehouseID> " +
              " <adin:stage>0</adin:stage> " +
              " </adin:ADLoginRequest>" 
			+ "";
	
	private static final String soapLkcOrderHead = ""
			+ "<adin:ModelCRUD>"
			+ "<adin:serviceType>ws_lkc_order</adin:serviceType> " +
               "<adin:TableName>LKC_ORDER</adin:TableName> " +
               "<adin:RecordID>0</adin:RecordID> " +
               "<adin:Action>Create</adin:Action> " +
               "<adin:DataRow>" +
               "#row_data#" +
               "</adin:DataRow>" +
               "</adin:ModelCRUD>"
			+ "";
	private static final String soapLkcOutletHead = ""
			+ "<adin:ModelCRUD>"
			+ "<adin:serviceType>ws_lkc_outlet</adin:serviceType> " +
               "<adin:TableName>LKC_OUTLET</adin:TableName> " +
               "<adin:RecordID>0</adin:RecordID> " +
               "<adin:Action>Create</adin:Action> " +
               "<adin:DataRow>" +
               "#row_data#" +
               "</adin:DataRow>" +
               "</adin:ModelCRUD>"
			+ "";
	
	private static final String soapLkcSoalHead = ""
			+ "<adin:ModelCRUD>"
			+ "<adin:serviceType>ws_lkc_soal</adin:serviceType> " +
               "<adin:TableName>LKC_SOAL</adin:TableName> " +
               "<adin:RecordID>0</adin:RecordID> " +
               "<adin:Action>Create</adin:Action> " +
               "<adin:DataRow>" +
               "#row_data#" +
               "</adin:DataRow>" +
               "</adin:ModelCRUD>"
			+ "";	
	
	private static final String soapLkcSoalOutletHead = ""
			+ "<adin:ModelCRUD>"
			+ "<adin:serviceType>ws_lkc_soaloutlet</adin:serviceType> " +
               "<adin:TableName>LKC_SOALOUTLET</adin:TableName> " +
               "<adin:RecordID>0</adin:RecordID> " +
               "<adin:Action>Create</adin:Action> " +
               "<adin:DataRow>" +
               "#row_data#" +
               "</adin:DataRow>" +
               "</adin:ModelCRUD>"
			+ "";
	
	public boolean doImport(String type, String data) throws Exception{
		boolean result = false;
		
		String soapXml = ""
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:adin=\"http://3e.pl/ADInterface\"> " +
					"<soapenv:Header/> " +
					"<soapenv:Body> " +
					"<adin:createData> " +
					"<adin:ModelCRUDRequest>"
				+ "#data#"
				+ "#login#"
				+ "</adin:ModelCRUDRequest>"
				+ "</adin:createData> " +
					"</soapenv:Body> " +
					"</soapenv:Envelope>"
				+ "";
		if (type.equalsIgnoreCase(LKC_ORDER)){
			soapXml = soapXml.replaceAll("#login#", soapCredential).replaceAll("#data#", soapLkcOrderHead).replaceAll("#row_data#", data);
		} else if (type.equalsIgnoreCase(LKC_OUTLET)){
			soapXml = soapXml.replaceAll("#login#", soapCredential).replaceAll("#data#", soapLkcOutletHead).replaceAll("#row_data#", data);
		} else if (type.equalsIgnoreCase(LKC_SOAL)){
			soapXml = soapXml.replaceAll("#login#", soapCredential).replaceAll("#data#", soapLkcSoalHead).replaceAll("#row_data#", data);
		}else if (type.equalsIgnoreCase(LKC_SOALOUTLET)){
			soapXml = soapXml.replaceAll("#login#", soapCredential).replaceAll("#data#", soapLkcSoalOutletHead).replaceAll("#row_data#", data);
		}
		
		
        java.net.URL url = new java.net.URL(WSDL_ADDRESS);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("SOAPAction", WSDL_ADDRESS);
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
        conn.setDoOutput(true);
        
        java.io.OutputStreamWriter wr = new java.io.OutputStreamWriter(conn.getOutputStream());
        wr.write(soapXml);
        wr.flush();
        
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        String xmlResult = "";
        while ((line = rd.readLine()) != null) {
        	xmlResult += line + "\n";
        }

        if (xmlResult.toLowerCase().contains("recordid")){
        	result = true;
        }
        
		return result;
	}
}
