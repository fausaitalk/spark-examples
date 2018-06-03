package com.udgrp.clustering

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.clustering.BisectingKMeans
import org.apache.spark.sql.SparkSession

/**
 * Created by Gavin on 2018/6/3.
 */
object BisectingKMeansExample extends SparkDriver{

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

    // Trains a bisecting k-means model.
    val bkm = new BisectingKMeans().setK(2).setSeed(1)
    val model = bkm.fit(dataset)

    // Evaluate clustering.
    val cost = model.computeCost(dataset)
    println(s"Within Set Sum of Squared Errors = $cost")

    // Shows the result.
    println("Cluster Centers: ")
    val centers = model.clusterCenters
    centers.foreach(println)
    // $example off$

  }
}
