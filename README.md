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
![1](https://github.com/alaayakine/Tp-Rdd/assets/106708512/582e012b-7442-45af-98b6-27591f6c44f9)
![2](https://github.com/alaayakine/Tp-Rdd/assets/106708512/4b22b739-353e-4bca-beb0-9555069941ac)
![3](https://github.com/alaayakine/Tp-Rdd/assets/106708512/d850f217-c6ab-4c11-8e01-54e1331d065f)
![4](https://github.com/alaayakine/Tp-Rdd/assets/106708512/675bf64b-7aa9-4856-af74-e6790116774b)
![5](https://github.com/alaayakine/Tp-Rdd/assets/106708512/481cb79f-4da0-43c8-85ac-b966ac5707dc)
![6](https://github.com/alaayakine/Tp-Rdd/assets/106708512/1bcce105-0f8a-46af-aba6-6a64d204d872)
![7](https://github.com/alaayakine/Tp-Rdd/assets/106708512/df5aa871-322e-461f-a75c-ee3bfb8d9bfa)
![8](https://github.com/alaayakine/Tp-Rdd/assets/106708512/8290b530-6236-49ae-9a23-445007ff1ca4)
![9](https://github.com/alaayakine/Tp-Rdd/assets/106708512/2eaa6137-24e7-41a4-951d-f26288c1ca6a)
![10](https://github.com/alaayakine/Tp-Rdd/assets/106708512/575fc440-19fe-47a0-a27e-35f737e309d4)
![11](https://github.com/alaayakine/Tp-Rdd/assets/106708512/a8269e81-0f27-4cb2-95d3-fb2fbef964e8)
![12](https://github.com/alaayakine/Tp-Rdd/assets/106708512/253c47e0-8dc0-4047-815a-6e0b25bf8a6a)











