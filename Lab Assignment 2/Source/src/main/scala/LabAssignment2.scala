

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by Ahmed Albishri on 01-Feb-17.
  */
object LabAssignment2 {

  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir","C:\\hadoop");

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    val input=sc.textFile("input")

    //Transformation #1 To split the input file into lines for every space..
    val spl1 = input.flatMap(line=>{line.split(" ")}).cache()

    //Transformation #2 create new RDD from the spl1 RDD by filtering words with length >10
    val lengthofword = spl1.filter(x => x.length() > 10).cache()

    //Transformation #3 create new RDD from lengthofword RDD with distinct values only
    val distvalues = lengthofword.distinct().cache()

    //Action #1 count values in distvalues RDD
    val countdata = distvalues.count()

    //Action #2 take first 3 values in distvalues RDD & convert into list
    val takrtest = distvalues.take(3).toList

    println (countdata)
    println (takrtest)

    //Action #3 save distvalues RDD in TextFile "output"
    distvalues.saveAsTextFile("output")

  }

}