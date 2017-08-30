package com.zy.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EmulatorCheck {

	private static String[] known_pipes = { "/dev/socket/qemud",
			"/dev/qemu_pipe" };

	private static String[] known_qemu_drivers = { "goldfish" };

	private static String[] known_files = {
		//内存泄漏
			"/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace",
			"/system/bin/qemu-props" };

	private static String[] known_numbers = { "15555215554", "15555215556",
			"15555215558", "15555215560", "15555215562", "15555215564",
			"15555215566", "15555215568", "15555215570", "15555215572",
			"15555215574", "15555215576", "15555215578", "15555215580",
			"15555215582", "15555215584", };

	private static String[] known_device_ids = { "000000000000000" // 默认ID
	};

	private static String[] known_imsi_ids = { "310260000000000" // 默认的 imsi id
	};
	//文卓爷-文卓爷-逍遥-夜神-天天
	private static String[] known_emulator = { "/system/lib/libdroid4x.so",
		"/system/bin/windroyed","/system/bin/microvirtd","/system/bin/nox-prop",
		"/system/bin/ttVM-prop"};

	// 检测“/dev/socket/qemud”，“/dev/qemu_pipe”这两个通道
	private static boolean checkPipes() {
		for (int i = 0; i < known_pipes.length; i++) {
			String pipes = known_pipes[i];
			File qemu_socket = new File(pipes);
			if (qemu_socket.exists()) {
//				Log.v("Result:", "Find pipes!");
				return true;
			}
		}
//		Log.i("Result:", "Not Find pipes!");
		return false;
	}

	// 检测驱动文件内容
	// 读取文件内容，然后检查已知QEmu的驱动程序的列表
	private static Boolean checkQEmuDriverFile() {
		File driver_file = new File("/proc/tty/drivers");
		if (driver_file.exists() && driver_file.canRead()) {
			byte[] data = new byte[1024]; // (int)driver_file.length()
			try {
				InputStream inStream = new FileInputStream(driver_file);
				inStream.read(data);
				inStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String driver_data = new String(data);
			for (String known_qemu_driver : EmulatorCheck.known_qemu_drivers) {
				if (driver_data.indexOf(known_qemu_driver) != -1) {
//					Log.i("Result:", "Find know_qemu_drivers!");
					return true;
				}
			}
		}
//		Log.i("Result:", "Not Find known_qemu_drivers!");
		return false;
	}

	// 检测模拟器上特有的几个文件
	private static Boolean CheckEmulatorFiles() {
		for (int i = 0; i < known_files.length; i++) {
			String file_name = known_files[i];
			File qemu_file = new File(file_name);
			if (qemu_file.exists()) {
//				Log.v("Result:", "Find Emulator Files!");
				if(i==0){
					File file = new File("/system/lib/libbluetooth_jni.so");
					if(!file.exists())return true;
				}else{
					return true;
				}
			}
		}
//		Log.v("Result:", "Not Find Emulator Files!");
		return false;
	}

	// 检测模拟器默认的电话号码
	private static Boolean CheckPhoneNumber(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String phonenumber = telephonyManager.getLine1Number();

		for (String number : known_numbers) {
			if (number.equalsIgnoreCase(phonenumber)) {
//				Log.v("Result:", "Find PhoneNumber!");
				return true;
			}
		}
//		Log.v("Result:", "Not Find PhoneNumber!");
		return false;
	}

	// 检测设备IDS 是不是 “000000000000000”
	private static Boolean CheckDeviceIDS(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String device_ids = telephonyManager.getDeviceId();

		for (String know_deviceid : known_device_ids) {
			if (know_deviceid.equalsIgnoreCase(device_ids)) {
//				Log.v("Result:", "Find ids: 000000000000000!");
				return true;
			}
		}
//		Log.v("Result:", "Not Find ids: 000000000000000!");
		return false;
	}

	// 检测imsi id是不是“310260000000000”
	private static Boolean CheckImsiIDS(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String imsi_ids = telephonyManager.getSubscriberId();

		for (String know_imsi : known_imsi_ids) {
			if (know_imsi.equalsIgnoreCase(imsi_ids)) {
//				Log.v("Result:", "Find imsi ids: 310260000000000!");
				return true;
			}
		}
//		Log.v("Result:", "Not Find imsi ids: 310260000000000!");
		return false;
	}

	// 检测手机上的一些硬件信息
	private static Boolean CheckEmulatorBuild(Context context) {
		String BOARD = android.os.Build.BOARD;
		String BOOTLOADER = android.os.Build.BOOTLOADER;
		String BRAND = android.os.Build.BRAND;
		String DEVICE = android.os.Build.DEVICE;
		String HARDWARE = android.os.Build.HARDWARE;
		String MODEL = android.os.Build.MODEL;
		String PRODUCT = android.os.Build.PRODUCT;
		if (BOARD == "unknown" || BOOTLOADER == "unknown" || BRAND == "generic"
				|| DEVICE == "generic" || MODEL == "sdk" || PRODUCT == "sdk"
				|| HARDWARE == "goldfish") {
//			Log.v("Result:", "Find Emulator by EmulatorBuild!");
			return true;
		}
//		Log.v("Result:", "Not Find Emulator by EmulatorBuild!");
		return false;
	}

	// 检测手机运营商家
	private static boolean CheckOperatorNameAndroid(Context context) {
		String szOperatorName = ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperatorName();

		if (szOperatorName.toLowerCase().equals("android") == true) {
//			Log.v("Result:", "Find Emulator by OperatorName!");
			return true;
		}
//		Log.v("Result:", "Not Find Emulator by OperatorName!");
		return false;
	}
	
	// 检测手机cpu
	private static boolean isEmulator(Context context) {
		String result = "";
		try {
			String[] args = { "/system/bin/cat", "/proc/cpuinfo" };
			ProcessBuilder cmd = new ProcessBuilder(args);
			Process process = cmd.start();
			StringBuffer sb = new StringBuffer();
			String readLine = "";
			BufferedReader responseReader = new BufferedReader(
					new InputStreamReader(process.getInputStream(), "utf-8"));
			while ((readLine = responseReader.readLine()) != null) {
				sb.append(readLine);
			}
			responseReader.close();
			result = sb.toString().toLowerCase();
		} catch (IOException ex) {
		}
		return (!result.contains("arm")) || (result.contains("intel"))|| (result.contains("amd"));
	}
	
	
	// 检测手机特定模拟器文件
	private static boolean haveEmulatorFile() {
		for (int i = 0; i < known_emulator.length; i++) {
			String file_name = known_emulator[i];
			File qemu_file = new File(file_name);
			if (qemu_file.exists()) {
//					Log.v("Result:", "Find Emulator Files!");
				return true;	
			}
		}
//			Log.v("Result:", "Not Find Emulator Files!");
		return false;
	}
	
	//检测电池温度，需注册广播           备用
//	public static boolean batteryStatus(){
//			IntentFilter intentFilter = new IntentFilter( 
//			Intent.ACTION_BATTERY_CHANGED); 
//			Intent batteryStatusIntent = registerReceiver(null, intentFilter);
//
//			    int voltage = batteryStatusIntent.getIntExtra("voltage", 99999);
//			    int temperature = batteryStatusIntent.getIntExtra("temperature", 99999);
//			    if (((voltage == 0) && (temperature == 0))
//			            || ((voltage == 10000) && (temperature == 0))) {
//			        //这是通过电池的伏数和温度来判断是真机还是模拟器
//			    }
//	}
	public static int checked(Context context){
		if (CheckDeviceIDS(context))return 1;
//		if (CheckEmulatorBuild(context))return 2;
//		if (CheckEmulatorFiles())return 3;
		if (CheckImsiIDS(context))return 4;
//		if (CheckOperatorNameAndroid(context))return 5;
		if (CheckPhoneNumber(context))return 6;
		if (checkPipes())return 7;
		if (checkQEmuDriverFile())return 8;
//		if (isEmulator(context))return 9;
		if (haveEmulatorFile())return 10;
//		System.out.println(i);
		return 0;
	}
}
