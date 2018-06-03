/*
 * Copyright @ 2018. 联合数据 粤ICP备17043086号-2 版权所有
 */
package com.udgrp.common

import java.util.concurrent.Executors

import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

/**
  * Created by kejw on 2017/5/15.
  */
trait SparkDriver extends Serializable {
  @transient val logger = LoggerFactory.getLogger(classOf[SparkDriver])
  val nThreads = Runtime.getRuntime().availableProcessors()
  val excutorpools = Executors.newCachedThreadPool


  def run(): Unit = {
    driverRunner
  }

  private def driverRunner: Unit = {
    val sparkSession = SparkInit.initSpark
    logger.info("spark config inited")
    logger.info("start to process the data ...")
    doProcess(sparkSession)
    sparkSession.stop()
    logger.info("sparkContext stop successfully")
  }

  /**
    * 子类根据自身需求重写该方法
    */
  def doProcess(spark: SparkSession)

}
