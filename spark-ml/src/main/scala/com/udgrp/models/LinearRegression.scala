package com.udgrp.models

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @author kejw
  * @version V1.0
  * @Project ud-traffic-master
  * @Description: TODO
  * @date 2018/6/2
  */
object LinearRegression extends SparkDriver {

  def main(args: Array[String]): Unit = {
    this.run()
  }

  /**
    * 子类根据自身需求重写该方法
    */
  override def doProcess(ss: SparkSession): Unit = {
    import ss.implicits._
    val train = ss.read.textFile("C:\\unionwork\\testdata\\traindata\\line_regression_hour.csv").map { row =>
      val cols = row.split(",")
      (cols(2).toDouble, cols(3).toDouble, cols(4).toDouble, cols(5).toDouble, cols(6).toDouble, cols(7).toDouble, cols(8).toDouble, cols(9).toDouble, cols(10).toDouble, cols(11).toDouble, cols(12).toDouble, cols(13).toDouble, cols(16).toDouble)
    }.toDF("season", "yr", "mnth", "hr", "holiday", "weekday", "workingday", "weathersit", "temp", "atemp", "hum", "windspeed", "cnt")

    val inputCols = Array("season", "yr", "mnth", "hr", "holiday", "weekday", "workingday", "weathersit", "temp", "atemp", "hum", "windspeed")

    // 根据inputCols指定列提取特征值放到features中
    val assembler = new VectorAssembler().setInputCols(inputCols).setOutputCol("features")
    val inputVec = assembler.transform(train)

    val lr1 = new LinearRegression()
    val lr2 = lr1.setFeaturesCol("features").setLabelCol("cnt").setFitIntercept(true) // 是否拟合截距，默认true

    val lr3 = lr2.setMaxIter(10).setRegParam(0.3).setElasticNetParam(0.8)

    val lr = lr3
    val model = lr.fit(inputVec)

    // 准备预测数据
    val predictDf = train.randomSplit(Array(0.9, 0.1), seed = 11L)(1)
    val predictInput = buildData(predictDf)
    val predict = model.transform(predictInput)
    println("输出预测结果:")
    predict.selectExpr("cnt", "prediction", "features").show(1000)

  }
  def buildData(df: DataFrame): DataFrame = {
    val inputCols = Array("season","yr","mnth","hr","holiday","weekday","workingday","weathersit","temp","atemp","hum","windspeed")

    // 根据inputCols指定列提取特征值放到features中
    val assembler = new VectorAssembler().setInputCols(inputCols).setOutputCol("features")
    assembler.transform(df)
  }
}
