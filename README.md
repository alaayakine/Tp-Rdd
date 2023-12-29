# Spark Transformation and Action Example

This Spark program demonstrates a series of transformations and actions on a `JavaRDD<String>`.

## Overview

The following steps outline the transformations and actions applied to the initial RDD:

## 1. Spark Configuration and Context Initialization

```java
SparkConf spark = new SparkConf().setAppName("tp1").setMaster("local[*]");
JavaSparkContext context = new JavaSparkContext(spark);
```

## 2. Création d'un RDD
```java
JavaRDD<String> rdd = context.parallelize(Arrays.asList(
        new String[]{"Alaa yakine", "hicham elmodni", "Mehdi kari"}
        ));
```
## 3. Application de la Transformation flatMap
```java
JavaRDD<String> flatMapRdd = rdd.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
```
Application de la transformation flatMap pour diviser chaque ligne en mots et aplatir le résultat.

## 4. Application des Transformations filter
```java
JavaRDD<String> filter1 = flatMapRdd.filter(s -> s.length() > 4);
        JavaRDD<String> filter2 = flatMapRdd.filter(s -> s.length() > 5);
        JavaRDD<String> filter3 = flatMapRdd.filter(s -> s.length() < 5);
```
Application de trois transformations filter pour filtrer les chaînes en fonction de leur longueur.
## 5. Application de la Transformation union
```java
JavaRDD<String> unionf1f2 = filter1.union(filter2);
```
Application de la transformation union pour fusionner les résultats de filter1 et filter2.
## 6. Application des Transformations map
```java
JavaRDD<String> mapUninF1F2 = unionf1f2.map(s -> s.toUpperCase());
JavaRDD<String> mapF3 = filter3.map(s -> s.toLowerCase());
```
Application des transformations map pour convertir les chaînes en majuscules (mapUninF1F2) et en minuscules (mapF3).
## 7. Application des Transformations reduce
```java
 JavaRDD<String> reduceMU = context.parallelize(Arrays.asList(mapUninF1F2.reduce((s, s2) -> s.concat(s2))));
        JavaRDD<String> reduceMF = context.parallelize(Arrays.asList(mapF3.reduce((s, s2) -> s.concat(s2))));

```
Application de deux transformations reduce pour concaténer les chaînes dans mapUninF1F2 et mapF3.
## 8. Application de la Transformation union à Nouveau
```java
 JavaRDD<String> UR1R2 = reduceMU.union(reduceMF);
```
Application de la transformation union pour fusionner les résultats des deux opérations reduce.
## 9. Application de la Transformation sortBy
```java
 JavaRDD<String> sort = UR1R2.sortBy(String::length, true, 1);

```
Application de la transformation sortBy pour trier les éléments dans UR1R2 en fonction de la longueur de la chaîne par ordre croissant.
## 10. Affichage des Résultats
```java
 System.out.println("RDD d'origine : " + rdd.collect());
        System.out.println("Résultat de flatMap : " + flatMapRdd.collect());
        System.out.println("Résultat de filter1 : " + filter1.collect());
        System.out.println("Résultat de filter2 : " + filter2.collect());
        System.out.println("Résultat de filter3 : " + filter3.collect());
        System.out.println("Résultat de unionf1f2 : " + unionf1f2.collect());
        System.out.println("Résultat de mapUninF1F2 : " + mapUninF1F2.collect());
        System.out.println("Résultat de mapF3 : " + mapF3.collect());
        System.out.println("Résultat de reduceMU : " + reduceMU.collect());
        System.out.println("Résultat de reduceMF : " + reduceMF.collect());
        System.out.println("Résultat de UR1R2 : " + UR1R2.collect());
        System.out.println("Résultat de sortByLength : " + sort.collect());
```


