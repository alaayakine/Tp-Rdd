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
![12](https://github.com/alaayakine/Tp-Rdd/assets/106708512/79ee9134-ffc4-4170-9a22-203bfa9e43d5)
![11](https://github.com/alaayakine/Tp-Rdd/assets/106708512/c75e068f-4a5a-4d19-8b00-30b5ed2c4971)
![10](https://github.com/alaayakine/Tp-Rdd/assets/106708512/b2267e80-cdcf-4736-9edf-865f312bc4bb)
![9](https://github.com/alaayakine/Tp-Rdd/assets/106708512/422a2bc2-a5ff-4e4d-80b3-f16a29768acc)
![8](https://github.com/alaayakine/Tp-Rdd/assets/106708512/e02f5997-64e1-447a-9cd0-b5b81134ed03)
![7](https://github.com/alaayakine/Tp-Rdd/assets/106708512/971ede35-5b15-41bf-a201-f7d70d03efe1)
![6](https://github.com/alaayakine/Tp-Rdd/assets/106708512/92b160a7-e0c4-45c9-97de-11a22db4c367)
![5](https://github.com/alaayakine/Tp-Rdd/assets/106708512/ba9fa1f8-e1fe-4735-bea4-dd18bcfdbf0b)
![4](https://github.com/alaayakine/Tp-Rdd/assets/106708512/e7f3589c-d763-4092-a163-5880d32339b0)
![3](https://github.com/alaayakine/Tp-Rdd/assets/106708512/4957f97a-4207-44f7-985c-afe167d367a3)
![2](https://github.com/alaayakine/Tp-Rdd/assets/106708512/e68dfa13-1adc-4a28-a63d-50f821b71e99)
![1](https://github.com/alaayakine/Tp-Rdd/assets/106708512/b94b5d2c-9fd4-4a2b-9dc7-059d1196385b)

