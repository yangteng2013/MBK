//
// Created by funly on 2017/8/24.
//
#include "com_zy_mbk_NativeUtil.h"
/*
 * Class:     com_zy_mbk_NativeUtil
 * Method:    getSha1StringNative
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_zy_mbk_NativeUtil_getSha1StringNative
  (JNIEnv *env, jobject thiz){
        return (*env)->NewStringUTF(env,"C2:D7:BA:D1:CE:52:AD:F0:86:CE:9F:6F:41:34:A8:25:A1:39:90:BF");
  }