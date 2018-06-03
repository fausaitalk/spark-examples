/*
 * Copyright @ 2018. 联合数据 粤ICP备17043086号-2 版权所有
 */

package com.udgrp.common

import com.udgrp.constant.Constants
import org.apache.spark.sql.SparkSession

/**
  * Created by kejw on 2017/5/15.
  */
object SparkInit {

  def initSpark: SparkSession = {
    val appName = Constants.APPNAME
    val master = Constants.MASTER
    val timeout = Constants.TIMEOUT
    val heartbeatInterval = Constants.HEARTBEATINTERVAL
    val spark_serializer = Constants.SPARK_SERIALIZER
    val park_executor_extrajavaoptions = Constants.PARK_EXECUTOR_EXTRAJAVAOPTIONS
    System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl")
    System.setProperty("javax.xml.parsers.SAXParserFactory", "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl")
    val sparkSession = SparkSession.builder
      .master(master)
      .appName(appName)
      .config("spark.network.timeout", timeout)
      .config("spark.local.dir","/opt/buffer")
      .config("spark.executor.heartbeatInterval", heartbeatInterval)
      .config("spark.serializer", spark_serializer)
      .config("spark.executor.extraJavaOptions", park_executor_extrajavaoptions)
      .getOrCreate()
    sparkSession
  }
}
