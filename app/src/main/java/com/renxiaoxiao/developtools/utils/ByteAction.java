package com.renxiaoxiao.developtools.utils;

public class ByteAction {
	//BIG-ENDIAN
	//LITTLE-ENDIAN
	static final private char HEX_DIGEST[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	
	static public void set(byte[] b, byte v) {
		for (int i = 0; i < b.length; i++) {
			b[i] = v;
		}
	}
	
	static public byte[] get(byte[] bSrc, int iOff, int iCount) {
		byte[] bDes = new byte[iCount];
		System.arraycopy(bSrc, iOff, bDes, 0, iCount);
		return bDes;
	}
	
	static public int copyByte(byte[] pSrc, int iSrc, byte[] pDes, int iDes, int iLen) {
		int iSrcLeft = pSrc.length - iSrc;
		int iCopyLen = iSrcLeft;
		if (iCopyLen > iLen) {
			iCopyLen = iLen;
		}
		System.arraycopy(pSrc, iSrc, pDes, iDes, iCopyLen);
		return iCopyLen;
	}
	
	static public String toHexString(byte[] bData) {
		return toHexString(bData, 0, bData.length);
	}
	
	static public String toHexString(byte[] bData, int iOff, int iCount) {
		StringBuilder sb = new StringBuilder(iCount * 2 + 1);
		for (int i = iOff; i < iOff + iCount; i++) {
			sb.append(HEX_DIGEST[((bData[i] >> 4) & 0x0f)]);
			sb.append(HEX_DIGEST[(bData[i] & 0x0f)]);
		}

		return sb.toString();
	}
	
	static public byte HexDigistToByte(char c) {
		byte r = 0;
		if (c >= '0' && c <= '9') {
			r = (byte) (c - '0');
		}
		else if (c >= 'A' && c <= 'F') {
			r = (byte) (c - 'A' + 10);
		}
		else if (c >= 'a' && c <= 'f') {
			r = (byte) (c - 'a' + 10);
		}
		return r;
	}
	
	static public byte[] fromHexString(String sHex) {
		int iHex = sHex.length() / 2;
		byte[] bHex = new byte[iHex];
		char[] lsHex = sHex.toCharArray();
		for (int i = 0; i < iHex; i++) {
			bHex[i] = (byte) (((HexDigistToByte(lsHex[2*i]) << 4) & 0xf0) + HexDigistToByte(lsHex[2*i+1]));
		}
		return bHex;
	}
	
	static public String toHexStringMidSpace(byte[] b) {
		StringBuilder sb = new StringBuilder();
		if (b.length > 0) {
			sb.append(HEX_DIGEST[((b[0] >> 4) & 0x0f)]);
			sb.append(HEX_DIGEST[(b[0] & 0x0f)]);
			
			for (int i = 1; i < b.length; i++) {
				sb.append(' ');
				sb.append(HEX_DIGEST[((b[i] >> 4) & 0x0f)]);
				sb.append(HEX_DIGEST[(b[i] & 0x0f)]);
			}
		}
		return sb.toString();
	}

	static private int byteToInt(byte[] bBuf, int iOffset, int iCount, boolean bBig) {   
		int iData = 0;
		if (bBuf != null && iOffset >= 0 && iCount > 0) {
			if (iCount + iOffset > bBuf.length) {
				iCount = bBuf.length - iOffset;
			}
			if (bBig) {
				for (int i = 0; i < iCount; i++) {
					iData <<= 8;
					iData += ((int)(bBuf[iCount + iOffset - i - 1]) & 0xff);	
				}			
			}
			else {
				for (int i = 0; i < iCount; i++) {
					iData <<= 8;
					iData +=  ((int)(bBuf[i + iOffset]) & 0xff);
				}
			}
		}
		return iData;
    }
	
	static private void intToByte(int iData, byte[] bBuf, int iOffset, int iCount, boolean bBigEdian) {    
		if (bBuf != null && iOffset >= 0 && iCount > 0) {
			if (iCount + iOffset > bBuf.length) {
				iCount = bBuf.length - iOffset;
			}
			if (bBigEdian) {
				for (int i = 0; i < iCount; i++) {
					bBuf[i + iOffset] = (byte)(iData & 0xff);
					iData >>= 8;
				}
			}
			else {
				for (int i = 0; i < iCount; i++) {
					bBuf[iCount + iOffset - i - 1] = (byte)(iData & 0xff);
					iData >>= 8;
				}
			}
		}
    }
	
	static public byte[] intToByteB(int n) {   
       byte[] b = new byte[4];   
       intToByte(n, b, 0, 4, true);
       return b;   
    } 

	static public void intToByteB(int n, byte[] b, int i, int c) {    
		intToByte(n, b, i, c, true);
    } 
	
	static public void intToByteB(int n, byte[] b, int i) {    
		intToByte(n, b, i, 4, true);
    } 
	
	static public void intToByteB(int n, byte[] b) {    
		intToByte(n, b, 0, 4, true);
    } 
	
	static public int byteToIntB(byte[] bBuf, int iOffset, int iCount) {   
		return byteToInt(bBuf, iOffset, iCount, true);
    }
	
	static public int byteToIntB(byte[] b) {   
		return byteToInt(b, 0, 4, true);
    }
	
	static public int byteToIntB(byte[] b, int i) {   
		return byteToInt(b, i, 4, true);
    }
	
	static public byte[] intToByteL(int n) {   
       byte[] b = new byte[4];   
       intToByte(n, b, 0, 4, false);
       return b;   
    } 
	
	static public void intToByteL(int n, byte[] b) {    
		intToByte(n, b, 0, 4, false);
    } 
	
	static public void intToByteL(int n, byte[] b, int i) {    
		intToByte(n, b, i, 4, false);
    } 
	
	static public int byteToIntL(byte[] b) {   
		return byteToInt(b, 0, 4, false);
    }
	
	static public int byteToIntL(byte[] b, int i) {   
		return byteToInt(b, i, 4, false);
    }
	
	static public int byteToIntL(byte[] b, int i, int c) {   
		return byteToInt(b, i, c, false);
    }
	
	static public byte[] append(byte[] a, byte[] b) {
		byte[] r = new byte[a.length + b.length];
		System.arraycopy(a, 0, r, 0, a.length);
		System.arraycopy(b, 0, r, a.length, b.length);
		return r;
	}
}
