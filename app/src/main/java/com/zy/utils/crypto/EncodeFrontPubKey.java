package com.zy.utils.crypto;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;



public class EncodeFrontPubKey {
	/**
	 * 
	 * 描述 : 进行RSA加密. <br/>
	 * <p>
	 * 
	 * @param symmetryKey 要加密的密钥
	 * @param frontPubKey 前置证书提供的公钥
	 * @return
	 */
	 public static final String KEY_STORE = "PKCS12"; 
	/**
	 * 
	 * 描述 : RSA公钥加密. <br/>
	 * <p>
	 * 
	 * @param symmetryKey 要加密的字符串
	 * @param frontPubKey 用来加密的公钥
	 * @return 加密后的字符串
	 */
	public static String encryptForConfig(String symmetryKey,String frontPubKey){
		try {
			byte[] key=Base64Coder.decode(frontPubKey);
			KeyFactory rsaKeyFac=KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec=new X509EncodedKeySpec(key);
			RSAPublicKey pbk=(RSAPublicKey) rsaKeyFac.generatePublic(keySpec);
			Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, pbk);
			byte[] encDate=cipher.doFinal(symmetryKey.getBytes());
			return Base64Coder.encodeLines(encDate);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} 
		return "";
	}
	/**
	 * RSA加密数据
	 * @param input 加密数据
	 * @param RSAKey 公钥
	 */
	public static  String rsaGetPublicKey(String input,String RSAKey){
		try {
			String str="";
			InputStream inStream;
			//流形式
			String certificateFile=RSAKey;
			byte[] bs=Base64Coder.decodeLines(certificateFile);
			inStream = new ByteArrayInputStream(bs); 
			CertificateFactory cff = CertificateFactory.getInstance("X.509");
			Certificate cf = cff.generateCertificate(inStream);
			PublicKey pk = cf.getPublicKey();           // 得到证书文件携带的公钥
			Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // 定义算法：RSA
			c.init(Cipher.ENCRYPT_MODE, pk);     
			byte[] b=input.getBytes();
			byte[] msg = c.doFinal(b); // 加密后的数据
			str=Base64Coder.encodeLines(msg);
			str=str.replace("\n", "");
//			Log.e(input, str);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 
	 * 描述 : 用私钥进行加密. <br/>
	 * <p>
	 * 
	 * @param symmetryKey 要加密的数据源
	 * @param privatekey 从证书中提取到的私钥
	 * @return 加密后的字符串
	 */
	public static String encryptByPrivateKey(byte[] symmetryKey,PrivateKey privatekey){
		try {
			RSAPrivateKey prk=(RSAPrivateKey) privatekey;
			Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, prk);
			byte[] encDate=cipher.doFinal(symmetryKey);
			return Base64Coder.encodeLines(encDate);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return "";
	}
	/** 
	*   使用MD5对字节数组进行摘要计算 
	*   @param   str    需要加密的源字节数组
	*   @return   经过加密后的结果字节数组 
	*/ 
	public static  byte[]   getMD5(String   str)   {
		MessageDigest messageDigest = null; 
		try {   
            messageDigest = MessageDigest.getInstance("MD5");   
            messageDigest.reset();  
            messageDigest.update(str.getBytes("UTF-8"));   
        } catch (NoSuchAlgorithmException e) {   
            System.exit(-1);   
        } catch (UnsupportedEncodingException e) {       
            e.printStackTrace();   
        }   
       byte[] byteArray = messageDigest.digest();  
       return byteArray;
	}
	//获取pfx
	/**
	 * 
	 * 描述 : 将证书用流写入. <br/>
	 * <p>
	 * 
	 * @param keyStore 证书
	 * @param password 密码
	 * @return
	 */
	public static KeyStore getKeyStore(InputStream keyStore,String password){
		KeyStore ks=null;
		try {
			InputStream is=keyStore;
			ks=KeyStore.getInstance(KEY_STORE);
			ks.load(is,password.toCharArray());
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ks;
	}
	//获取证书的私钥
	/**
	 * 
	 * 描述 : 从证书中获取私钥. <br/>
	 * <p>
	 * 
	 * @param keystore 证书
	 * @param password 密码
	 * @return 私钥
	 */
	public static PrivateKey getPrivateKey(InputStream keystore,String password){
		PrivateKey key=null;
		try {
			KeyStore ks=getKeyStore(keystore, password);
			String keyAlia=null;
			@SuppressWarnings("rawtypes")
			Enumeration enums=ks.aliases();
			while (enums.hasMoreElements()) {
				String keyAlias=(String) enums.nextElement();
				keyAlia=keyAlias;
			}
			key=(PrivateKey) ks.getKey(keyAlia, password.toCharArray());
			return key;
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return key;
	}
	public static PublicKey getPublicKey(InputStream keyStore,String password){
		PublicKey key=null;
		try {
			KeyStore ks=getKeyStore(keyStore, password);
			String keyAlia=null;
			@SuppressWarnings("rawtypes")
			Enumeration enums;
			enums = ks.aliases();
			while (enums.hasMoreElements()) {
				String keyAlias=(String) enums.nextElement();
				keyAlia=keyAlias;
			}
			Certificate cert = ks.getCertificate(keyAlia);
			key = cert.getPublicKey();		
			} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return key;
	}
}
