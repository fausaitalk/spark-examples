package com.udgrp.classification

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession

/**
 * Created by Gavin on 2018/6/3.
 */
object MulticlassLogisticRegressionWithElasticNetExample extends SparkDriver{

  def main(args: Array[String]) {
    this.run()
  }
  /**
   * 子类根据自身需求重写该方法
   */
  override def doProcess(spark: SparkSession): Unit = {

    // $example on$
    // Load training data
    val training = spark
      .read
      .format("libsvm")
      .load("spark-ml/data/mllib/sample_multiclass_classification_data.txt")

    val lr = new LogisticRegression()
      .setMaxIter(10)
      .setRegParam(0.3)
      .setElasticNetParam(0.8)

    // Fit the model
    val lrModel = lr.fit(training)

    // Print the coefficients and intercept for multinomial logistic regression
    println(s"Coefficients: \n${lrModel.coefficientMatrix}")
    println(s"Intercepts: ${lrModel.interceptVector}")
    // $example off$
  }
}
