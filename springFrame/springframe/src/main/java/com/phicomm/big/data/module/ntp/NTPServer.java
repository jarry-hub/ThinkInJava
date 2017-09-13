package com.phicomm.big.data.module.ntp;

public interface NTPServer {
	
	long getTime();

    void synchronizeTime();

}
