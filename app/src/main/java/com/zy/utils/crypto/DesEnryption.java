package com.zy.utils.crypto;

/*字符�? DESede(3DES) 加密*/ 
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DesEnryption {
    private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish
    //keybyte为加密密钥，长度�?24字节
    //src为被加密的数据缓冲区（源�?
    public DesEnryption(){
//    	Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }
    /**
     * 
     * 描述 : 3des加密. <br/>
     * <p>
     * 
     * @param keybyte 密鈅
     * @param src 数据�?
     * @return
     */
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
       try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

	// keybyte为加密密钥，长度�?24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			byte[] c2=c1.doFinal(src);
			return c2;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * 描述 : 进行3des加密. <br/>
	 * <p>
	 * 
	 * @param str 要加密的数据
	 * @param key 加密的密�?
	 * @return
	 */
   	public static  byte[] getencodeString(String str,String key){
            byte[] encoded=null;
            byte[] keyBytes=key.getBytes();
				try {
					encoded = encryptMode(keyBytes, str.getBytes("utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
	            return encoded;
	}
   	/**
   	 * 
   	 * 描述 : 进行3des解密. <br/>
   	 * <p>
   	 * 
   	 * @param str 数据�?
   	 * @param key 密钥
   	 * @return
   	 */
   	public static  String getDecodeString(byte[] str,byte[] key){
   		byte[] decode;;
   		decode=decryptMode(key, str);
   		try {
			return new String(decode,"utf-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
   		return "";
   	}
}


