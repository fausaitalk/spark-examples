package com.udgrp.examples

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.feature.PCA
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession

/**
  * @author kejw
  * @version V1.0
  * @Project ud-traffic-master
  * @Description: TODO
  * @date 2018/6/2
  */
object PCAExample extends SparkDriver {

  def main(args: Array[String]): Unit = {
    this.run()
  }

  /**
    * 子类根据自身需求重写该方法
    */
  override def doProcess(spark: SparkSession): Unit = {
    // $example on$
    val data = Array(
      Vectors.sparse(5, Seq((1, 1.0), (3, 7.0))),
      Vectors.dense(2.0, 0.0, 3.0, 4.0, 5.0),
      Vectors.dense(4.0, 0.0, 0.0, 6.0, 7.0)
    )
    val df = spark.createDataFrame(data.map(Tuple1.apply)).toDF("features")
    df.show(false)

    val pca = new PCA()
      .setInputCol("features")
      .setOutputCol("pcaFeatures")
      .setK(3)
      .fit(df)

    val result = pca.transform(df)//.select("pcaFeatures")
    result.show(false)
    // $example off$
  }
}
