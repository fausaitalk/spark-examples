package com.udgrp.examples

import com.udgrp.common.SparkDriver
import org.apache.spark.ml.feature.Word2Vec
import org.apache.spark.ml.linalg.Vector
import org.apache.spark.sql.{Row, SparkSession}

/**
  * @author kejw
  * @version V1.0
  * @Project ud-traffic-master
  * @Description: TODO
  * @date 2018/6/2
  */
object Word2VecExample extends SparkDriver{
  def main(args: Array[String]): Unit = {
    this.run()
  }
  /**
    * 子类根据自身需求重写该方法
    */
  override def doProcess(spark: SparkSession): Unit = {
    // $example on$
    // Input data: Each row is a bag of words from a sentence or document.
    val documentDF = spark.createDataFrame(Seq(
      "Hi I heard about Spark".split(" "),
      "I wish Java could use case classes".split(" "),
      "Logistic regression models are neat".split(" ")
    ).map(Tuple1.apply)).toDF("text")

    // Learn a mapping from words to Vectors.
    val word2Vec = new Word2Vec()
      .setInputCol("text")
      .setOutputCol("result")
      .setVectorSize(30)
      .setMinCount(0)
    val model = word2Vec.fit(documentDF)

    val result = model.transform(documentDF)
    result.collect().foreach { case Row(text: Seq[_], features: Vector) =>
      println(s"Text: [${text.mkString(", ")}] => \nVector: $features\n") }
    // $example off$
  }
}
