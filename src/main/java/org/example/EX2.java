package org.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class EX2 {
    public static void main(String[] args) {

        SparkConf spark = new SparkConf().setAppName("ex1").setMaster("local[*]");
        JavaSparkContext context=new JavaSparkContext(spark);
        String filePath = "C:/Users/PC/Documents/workspace/SID/tpRdd/src/main/resources/ventes.txt";
        JavaRDD<String> lines  = context.textFile(filePath);
        JavaPairRDD<String, Double> ventesParVille = lines.mapToPair(line -> {
            String[] parts = line.split(",");
            String ville = parts[1];
            double prix = Double.parseDouble(parts[3]);
            return new Tuple2<>(ville, prix);
        });
        JavaPairRDD<String, Double> totalVentesParVille = ventesParVille.reduceByKey((x, y) -> x + y);
        totalVentesParVille.foreach(tuple -> System.out.println(tuple._1() + ": " + tuple._2()));



    }
}
