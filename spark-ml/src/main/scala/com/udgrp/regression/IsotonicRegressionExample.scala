package com.udgrp.regression

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.regression.IsotonicRegression
import org.apache.spark.sql.SparkSession

/**
 * Created by Gavin on 2018/6/3.
 */
object IsotonicRegressionExample extends SparkDriver {

  def main(args: Array[String]) {
    this.run()
  }

  /**
   * 子类根据自身需求重写该方法
   */
  override def doProcess(spark: SparkSession): Unit = {

    // $example on$
    // Loads data.
    val dataset = spark.read.format("libsvm")
      .load("spark-ml/data/mllib/sample_isotonic_regression_libsvm_data.txt")

    // Trains an isotonic regression model.
    val ir = new IsotonicRegression()
    val model = ir.fit(dataset)

    println(s"Boundaries in increasing order: ${model.boundaries}\n")
    println(s"Predictions associated with the boundaries: ${model.predictions}\n")

    // Makes predictions.
    model.transform(dataset).show()
    // $example off$

  }
}
