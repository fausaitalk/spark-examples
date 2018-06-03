package com.udgrp.examples

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.feature.Bucketizer
import org.apache.spark.sql.SparkSession

/**
  * @author kejw
  * @version V1.0
  * @Project ud-traffic-master
  * @Description: TODO
  * @date 2018/6/2
  */
object BucketizerExample extends SparkDriver{
  def main(args: Array[String]): Unit = {
    this.run()
  }
  /**
    * 子类根据自身需求重写该方法
    */
  override def doProcess(spark: SparkSession): Unit = {

    // $example on$
    val splits = Array(Double.NegativeInfinity, -0.5, 0.0, 0.5, Double.PositiveInfinity)

    val data = Array(-999.9, -0.5, -0.3, 0.0, 0.2, 999.9)
    val dataFrame = spark.createDataFrame(data.map(Tuple1.apply)).toDF("features")

    val bucketizer = new Bucketizer()
      .setInputCol("features")
      .setOutputCol("bucketedFeatures")
      .setSplits(splits)

    // Transform original data into its bucket index.
    val bucketedData = bucketizer.transform(dataFrame)

    println(s"Bucketizer output with ${bucketizer.getSplits.length-1} buckets")
    bucketedData.show()
    // $example off$
  }
}
