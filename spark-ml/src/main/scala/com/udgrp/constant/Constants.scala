/*
 * Copyright @ 2018. 联合数据 粤ICP备17043086号-2 版权所有
 */
package com.udgrp.constant

import java.io.File

import com.typesafe.config.ConfigFactory

/**
  * Created by kejw on 2017/5/23.
  */
object Constants {

  /**
    * 测试环境
    */
  val conf = ConfigFactory.load("config.properties")
  /**
    * 生产环境
    */
 // val conf = ConfigFactory.parseFile(new File("config.properties"))

  /**
    * SPARK配置参数
    */
  val APPNAME = conf.getString("appName")
  val MASTER = conf.getString("master")
  val TIMEOUT = conf.getString("spark.network.timeout")
  val HEARTBEATINTERVAL = conf.getString("spark.executor.heartbeatInterval")
  val SPARK_SERIALIZER = conf.getString("spark.serializer")
  val PARK_EXECUTOR_EXTRAJAVAOPTIONS = conf.getString("spark.executor.extraJavaOptions")

  /**
    * JDBC配置参数
    */
  val DRIVER = conf.getString("driver")
  val USERNAME = conf.getString("userName")
  val PASSWORD = conf.getString("password")
  val URL = conf.getString("url")

  /**
    * SQL SERVER JDBC配置参数
    */
  val MSSQL_DRIVER = conf.getString("mssql_driver")
  val MSSQL_USERNAME = conf.getString("mssql_username")
  val MSSQL_PASSWORD = conf.getString("mssql_password")
  val MSSQL_URL = conf.getString("mssql_url")

  /**
    * ODBC配置参数
    */
  val ORACLE_DRIVER = conf.getString("oracle_driver")
  val ORACLE_USERNAME = conf.getString("oracle_userName")
  val ORACLE_PASSWORD = conf.getString("oracle_password")
  val ORACLE_URL = conf.getString("oracle_url")
  val ORACLE_DBTABLE = conf.getString("oracle_dbtable")
  /**
    * 业务参数
    */
  val EXLISTDATA_PATH = conf.getString("exlistdata")
  val GPSDATA_PATH = conf.getString("gpsdata")
  val GPSDATA_DATE = conf.getString("gpsdata_date")
  val GPS_INTR_TIME = conf.getInt("gps_intr_time")
  val STA_SPEED = conf.getDouble("sta_speed")
  val RESIDENCE_TIME = conf.getInt("residence_time")
  val SEPARATE_DISTANCE = conf.getDouble("separate_distance")
  val OVER_SPEED = conf.getDouble("over_speed")

  /**
    * shp地图文件按路径
    */
  val CITYS_SHP = conf.getString("citys_shp")
}
