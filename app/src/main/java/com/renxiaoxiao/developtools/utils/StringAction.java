package com.renxiaoxiao.developtools.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class StringAction {
	static public boolean isNumber(String strText) {
		boolean hz = false;
		if (strText != null && strText.length() > 0) {
			hz = true;
	        char[] ch = strText.toCharArray();
	        for (int i = 0; i < strText.length(); i++) {
	            if (ch[i] < '0' || ch[i] > '9') {
	            	if (i != 0  || ch[i] != '-') {
		            	hz = false;
		            	break;
	            	}
	            }
	        }
		}
        return hz;
    }
	
	static public int toNumber(String strText) {
		int hz = 0;
		boolean bNegative = false;
		if (strText != null && strText.length() > 0) {
	        char[] ch = strText.toCharArray();
	        if (ch.length > 1) {
	        	if (ch[0] == '-') {
	        		bNegative = true;
	        	}
	        }
	        for (int i = 0; i < strText.length(); i++) {
	            if (ch[i] >= '0' && ch[i] <= '9') {
	            	hz *= 10;
	            	hz += (ch[i] - '0');
	            }
	        }
		}	
		if (bNegative) {
			return -hz;
		}
		return hz;
	}
	
	static public String decode(ByteBuffer buffer, String textCode)  {
		String rs = null;
        try  {
        	buffer.flip();
        	Charset charset = Charset.forName(textCode);
        	CharsetDecoder decoder = charset.newDecoder();
        	CharBuffer charBuffer = decoder.decode(buffer);
            rs = charBuffer.toString();
        } 
        catch (Exception e)  {
			String sText = "StringAction:decode:" + trim(e.getMessage().trim());
			//LogGlobal.log(sText);
        }
        return rs;
	}
	
	static public String findBetween(String strSrc, String strFrom, String strTo) {
		int iFrom = strSrc.indexOf(strFrom);
		if (iFrom >= 0) {
			iFrom += strFrom.length();
			int iTo = strSrc.indexOf(strTo, iFrom);
			if (iTo > iFrom) {
				return strSrc.substring(iFrom, iTo);
			}
		}
		return null;
	}
	
	static public String replaceBetween(String strSrc, String strFrom, String strTo, String r) {
		int iFrom = strSrc.indexOf(strFrom);
		if (iFrom >= 0) {
			iFrom += strFrom.length();
			int iTo = strSrc.indexOf(strTo, iFrom);
			if (iTo > iFrom) {
				return strSrc.substring(0, iFrom) + r + strSrc.substring(iTo, strSrc.length());
			}
		}
		return strSrc;
	}
	
	static public String getBefore(String sText, String symbol) {
		int iPos = sText.indexOf(symbol);
		if (iPos > 0) {
			return sText.substring(0, iPos);
		}
		return null;
	}
	
	static public String getBeforeN(String sText, String symbol) {
		int iPos = sText.indexOf(symbol);
		if (iPos > 0) {
			return sText.substring(0, iPos);
		}
		return sText;
	}
	
	static public String getAfter(String sText, String symbol) {
		int iPos = sText.indexOf(symbol);
		if (iPos >= 0) {
			return sText.substring(iPos + symbol.length(), sText.length());
		}
		return null;
	}
	
	static public String getAfterN(String sText, String symbol) {
		int iPos = sText.indexOf(symbol);
		if (iPos >= 0) {
			return sText.substring(iPos + symbol.length(), sText.length());
		}
		return sText;
	}
	
	static public String[] split(String sText, char symbol) {
		return split(sText, symbol, -1);
	}
	
	static public String[] split(String sText, char symbol, int iMax) {
		String[] lsRs = null;
		char[] lsText = sText.toCharArray();
		int iCount = 0;
		boolean bFound = false;
		for (char cText : lsText) {
			if (cText != symbol) {
				if (bFound == false) {
					iCount++;
					bFound = true;
				}
			}
			else if (bFound){
				bFound = false;
			}
		}
		if (iCount > 0) {
			lsRs = new String[iCount];
			bFound = false;
			int iPos = -1;
			iCount = 0;
			for (int i = 0; i < lsText.length; i++) {
				if (lsText[i] != symbol) {
					if (bFound == false) {
						iPos = i;
						bFound = true;
					}
					if (i == lsText.length - 1 || iCount == iMax - 1) {
						lsRs[iCount] = sText.substring(iPos);
						break;
					}
				}
				else if (bFound){
					lsRs[iCount++] = sText.substring(iPos, i);
					bFound = false;
				}
			}
		}
		return lsRs;
	}
	
	static public String trim(String sText) {
		if (sText != null) {
			return sText.trim();
		}
		return null;
	}
	
	static public String intToFloat(int iInt, int iDiv) {
		if (iDiv > 0) {
			StringBuffer sFloat = new StringBuffer();
			int iHead= iInt;
			if (iInt < 0) {
				iHead = -iInt;
				sFloat.append('-');
			}
			char[] lsTail = new char[iDiv];
			for (int i = 0; i < iDiv; i++) {
				lsTail[iDiv - i - 1] = (char) (iHead % 10 + '0'); 
				iHead = iHead / 10;
			}
			sFloat.append(iHead);
			sFloat.append('.');
			sFloat.append(lsTail);
			return sFloat.toString();
		}
		else {
			return String.valueOf(iInt);
		}
	}
	
	static public String floatToInt(String sNumber, int iMul) {
		if (iMul > 0) {
			StringBuffer sFloat = new StringBuffer(sNumber.length() + iMul);
			char[] lsNum = sNumber.toCharArray();
			int iLeftPoint = iMul;
			boolean bSubLeftPoint = false;
			for (int i = 0; i < lsNum.length; i++) {
				if (bSubLeftPoint) {
					if (iLeftPoint == 0) {
						break;
					}
					iLeftPoint--;
				}
				if (lsNum[i] != '.') {
					sFloat.append(lsNum[i]);
				}
				else {
					bSubLeftPoint = true;
				}
			}
			for (int i = 0; i < iLeftPoint; i++) {
				sFloat.append('0');
			}
			return sFloat.toString();
		}
		return sNumber;		
	}
	
	static public String multi(String sNumber, int iMul) {
		if (iMul > 0) {
			StringBuffer sFloat = new StringBuffer(sNumber.length() + iMul);
			char[] lsNum = sNumber.toCharArray();
			int iLeftPoint = iMul;
			boolean bSubLeftPoint = false;
			for (int i = 0; i < lsNum.length; i++) {
				if (bSubLeftPoint) {
					if (iLeftPoint == 0) {
						sFloat.append('.');
						bSubLeftPoint = false;
					}
					iLeftPoint--;
				}
				if (lsNum[i] != '.') {
					sFloat.append(lsNum[i]);
				}
				else {
					bSubLeftPoint = true;
				}
			}
			for (int i = 0; i < iLeftPoint; i++) {
				sFloat.append('0');
			}
			return sFloat.toString();
		}
		return sNumber;
	}
	
	static public int indexOf(String sSrc, char sFind, int iLoop) {
		int iIndex = 0;
		for (int i = 0; i < iLoop; i++) {
			iIndex = sSrc.indexOf(sFind, iIndex);
			if (iIndex < 0) {
				break;
			}
		}
		return iIndex;
	}
	
	static public int indexOf(String sSrc, String sFind, int iLoop) {
		int iIndex = 0;
		for (int i = 0; i < iLoop; i++) {
			iIndex = sSrc.indexOf(sFind, iIndex);
			if (iIndex < 0) {
				break;
			}
		}
		return iIndex;
	}
	
	static public int hexToInt(String sLine) {
		int i = 0;
		char[] lsObj = sLine.toCharArray();
		for (char obj : lsObj) {
			i *= 16;
			if (obj >= 'a' && obj <= 'f') {
				i += (obj - 'a' + 10);
			}
			else if (obj >= '0' && obj <= '9'){
				i += (obj - '0');
			}
		}
		return i;
	}
	
	static public String toHex(String sLine) {
		byte[] pByte = sLine.getBytes();
		return ByteAction.toHexString(pByte);
	}
	
	static public int length(String s) {
		int i = 0;
		if (s != null) {
			i = s.length();
		}
		return i;
	}
}
