package org.example;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.rdd.RDD;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SparkConf spark = new SparkConf().setAppName("ex1").setMaster("local[*]");
        JavaSparkContext context=new JavaSparkContext(spark);
        JavaRDD<String> rdd = context.parallelize(Arrays.asList(
                new String[]{"Alaa yakine", "hicham elmodni", "Mehdi kari"}
        ));

        // Apply flatMap transformation
        JavaRDD<String> flatMapRdd = rdd.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
        JavaRDD<String> filter1=flatMapRdd.filter(s -> s.length()>4);
        JavaRDD<String> filter2=flatMapRdd.filter(s -> s.length()>5);
        JavaRDD<String> filter3=flatMapRdd.filter(s -> s.length()<5);
        JavaRDD<String> unionf1f2=filter1.union(filter2);
        JavaRDD<String> mapUninF1F2=unionf1f2.map(s -> s.toUpperCase());
        JavaRDD<String> mapF3=filter3.map(s -> s.toLowerCase());
        JavaRDD<String> reduceMU= context.parallelize(Arrays.asList(mapUninF1F2.reduce((s, s2) -> s.concat(s2))));
        JavaRDD<String> reduceMF= context.parallelize(Arrays.asList(mapF3.reduce((s, s2) -> s.concat(s2))));
        JavaRDD<String> UR1R2=reduceMU.union(reduceMF);
        JavaRDD<String> sort=UR1R2.sortBy(String::length,true,1);








        System.out.println("Original RDD: " + rdd.collect());
        System.out.println("flatmap Result: " + flatMapRdd.collect());
        System.out.println("filter1 Result: " + filter1.collect());
        System.out.println("filter2 Result: " + filter2.collect());
        System.out.println("filter3 Result: " + filter3.collect());
        System.out.println("union filter1 and filter 2: " + unionf1f2.collect());
        System.out.println("map le resultat de union: " + mapUninF1F2.collect());
        System.out.println("map le resultat de filter 3: " + mapF3.collect());
        System.out.println("le 1 ere reduce : "+reduceMU.collect());
        System.out.println("le 2 eme reduce : "+reduceMF.collect());
        System.out.println("UNION de 1 ere reduce et la  2 eme reduce : "+UR1R2.collect());
        System.out.println("sortByLenght : "+sort.collect());












    }
}