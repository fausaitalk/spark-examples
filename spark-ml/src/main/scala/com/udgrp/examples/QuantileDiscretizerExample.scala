package com.udgrp.examples

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.feature.QuantileDiscretizer
import org.apache.spark.sql.SparkSession

/**
  * @author kejw
  * @version V1.0
  * @Project ud-traffic-master
  * @Description: TODO
  * @date 2018/6/3
  */
object QuantileDiscretizerExample extends SparkDriver{

  def main(args: Array[String]): Unit = {
    this.run()
  }
  /**
    * 子类根据自身需求重写该方法
    */
  override def doProcess(spark: SparkSession): Unit = {
    // $example on$
    val data = Array((0, 18.0), (1, 19.0), (2, 8.0), (3, 5.0), (4, 2.2))
    val df = spark.createDataFrame(data).toDF("id", "hour")
      // $example off$
      // Output of QuantileDiscretizer for such small datasets can depend on the number of
      // partitions. Here we force a single partition to ensure consistent results.
      // Note this is not necessary for normal use cases
      .repartition(1)

    // $example on$
    val discretizer = new QuantileDiscretizer()
      .setInputCol("hour")
      .setOutputCol("result")
      .setNumBuckets(3)

    val result = discretizer.fit(df).transform(df)
    result.show()
    // $example off$
  }
}
