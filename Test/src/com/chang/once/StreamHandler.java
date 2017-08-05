package com.chang.once;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamHandler extends Thread {
	InputStream m_inputStream;
	String m_type;
	
	public StreamHandler(InputStream is, String type) {
		this.m_inputStream = is;
		this.m_type = type;
	}
	
	@Override
	public void run() {
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try{
			//���ñ��뷽ʽ�������������ʱ��������
			isr = new InputStreamReader(m_inputStream, "GBK");
			br = new BufferedReader(isr);
			String line=null;
			while ( (line = br.readLine()) != null) {
				System.out.println("PRINT > " + m_type + " : " + line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
		 	try {
	            br.close();
	            isr.close();
		    } catch (IOException ex) {
		    	Logger.getLogger(StreamHandler.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
	}
}
