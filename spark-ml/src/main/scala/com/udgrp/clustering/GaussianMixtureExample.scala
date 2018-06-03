package com.udgrp.clustering

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.clustering.GaussianMixture
import org.apache.spark.sql.SparkSession

/**
 * Created by Gavin on 2018/6/3.
 */
object GaussianMixtureExample extends SparkDriver {

  def main(args: Array[String]) {
    this.run()
  }

  /**
   * 子类根据自身需求重写该方法
   */
  override def doProcess(spark: SparkSession): Unit = {

    // $example on$
    // Loads data
    val dataset = spark.read.format("libsvm").load("spark-ml/data/mllib/sample_kmeans_data.txt")

    // Trains Gaussian Mixture Model
    val gmm = new GaussianMixture()
      .setK(2)
    val model = gmm.fit(dataset)

    // output parameters of mixture model model
    for (i <- 0 until model.getK) {
      println(s"Gaussian $i:\nweight=${model.weights(i)}\n" +
        s"mu=${model.gaussians(i).mean}\nsigma=\n${model.gaussians(i).cov}\n")
    }
    // $example off$

  }
}
