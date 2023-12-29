package org.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Scanner;

public class EX2q2 {
    public static void main(String[] args) {

        SparkConf spark = new SparkConf().setAppName("ex1").setMaster("local[*]");
        JavaSparkContext context=new JavaSparkContext(spark);
        String filePath = "C:/Users/PC/Documents/workspace/SID/tpRdd/src/main/resources/ventes.txt";
        Scanner scanner=new Scanner(System.in);
        JavaRDD<String> lines  = context.textFile(filePath);
        JavaPairRDD<Tuple2<String, String>, Double> ventesProduitsParVilleEtAnnee = lines.mapToPair(line -> {
            String[] parts = line.split(",");
            String ville = parts[1];
            String date = parts[0];
            String annee = date.split("-")[0];
            double prix = Double.parseDouble(parts[3]);
            return new Tuple2<>(new Tuple2<>(ville, annee), prix);
        });
        JavaPairRDD<Tuple2<String, String>, Double> totalVentesProduitsParVilleEtAnnee = ventesProduitsParVilleEtAnnee.reduceByKey((x, y) -> x + y);
        totalVentesProduitsParVilleEtAnnee.foreach(tuple -> System.out.println(tuple._1() + ": " + tuple._2()));



    }
}
