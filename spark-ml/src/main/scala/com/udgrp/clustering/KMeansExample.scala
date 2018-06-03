package com.udgrp.clustering

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.sql.SparkSession

/**
 * Created by Gavin on 2018/6/3.
 */
object KMeansExample extends SparkDriver{

  def main(args: Array[String]) {
    this.run()
  }
  /**
   * 子类根据自身需求重写该方法
   */
  override def doProcess(spark: SparkSession): Unit = {

    // $example on$
    // Loads data.
    val dataset = spark.read.format("libsvm").load("spark-ml/data/mllib/sample_kmeans_data.txt")

    // Trains a k-means model.
    val kmeans = new KMeans().setK(2).setSeed(1L)
    val model = kmeans.fit(dataset)

    // Evaluate clustering by computing Within Set Sum of Squared Errors.
    val WSSSE = model.computeCost(dataset)
    println(s"Within Set Sum of Squared Errors = $WSSSE")

    // Shows the result.
    println("Cluster Centers: ")
    model.clusterCenters.foreach(println)
    // $example off$
  }
}
