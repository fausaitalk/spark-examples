package com.udgrp.examples

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession

/**
  * @author kejw
  * @version V1.0
  * @Project ud-traffic-master
  * @Description: TODO
  * @date 2018/6/3
  */
object VectorAssemblerExample extends SparkDriver {

  def main(args: Array[String]): Unit = {
    this.run()
  }

  /**
    * 子类根据自身需求重写该方法
    */
  override def doProcess(spark: SparkSession): Unit = {

    // $example on$
    val dataset = spark.createDataFrame(
      Seq((0, 18, 1.0, Vectors.dense(0.0, 10.0, 0.5), 1.0))
    ).toDF("id", "hour", "mobile", "userFeatures", "clicked")

    val assembler = new VectorAssembler()
      .setInputCols(Array("hour", "mobile", "userFeatures"))
      .setOutputCol("features")

    val output = assembler.transform(dataset)
    println("Assembled columns 'hour', 'mobile', 'userFeatures' to vector column 'features'")
    output.select("features", "clicked").show(false)
    // $example off$
  }
}
