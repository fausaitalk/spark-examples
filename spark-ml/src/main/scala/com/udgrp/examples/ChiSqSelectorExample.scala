package com.udgrp.examples

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.feature.ChiSqSelector
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession

/**
  * @author kejw
  * @version V1.0
  * @Project ud-traffic-master
  * @Description: TODO
  * @date 2018/6/3
  */
object ChiSqSelectorExample extends SparkDriver{
  def main(args: Array[String]): Unit = {
    this.run()
  }
  /**
    * 子类根据自身需求重写该方法
    */
  override def doProcess(spark: SparkSession): Unit = {
    import spark.implicits._

    // $example on$
    val data = Seq(
      (7, Vectors.dense(0.0, 0.0, 18.0, 1.0), 1.0),
      (8, Vectors.dense(0.0, 1.0, 12.0, 0.0), 0.0),
      (9, Vectors.dense(1.0, 0.0, 15.0, 0.1), 0.0)
    )

    val df = spark.createDataset(data).toDF("id", "features", "clicked")

    val selector = new ChiSqSelector()
      .setNumTopFeatures(1)
      .setFeaturesCol("features")
      .setLabelCol("clicked")
      .setOutputCol("selectedFeatures")

    val result = selector.fit(df).transform(df)

    println(s"ChiSqSelector output with top ${selector.getNumTopFeatures} features selected")
    result.show()
    // $example off$
  }
}
