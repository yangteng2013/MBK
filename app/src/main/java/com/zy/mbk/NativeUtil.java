package com.zy.mbk;

/**
 * Created by funly on 2017/8/24.
 */

public class NativeUtil {
    private static native String getSha1StringNative();
    public static String getSha1String(){
        return  getSha1StringNative()+"";
    }
    static{
        System.loadLibrary("mbkjni");
    }
}
