package com;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.SerializationHelper;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToNominal;

import java.io.BufferedReader;
import java.io.StringReader;

public class CreateModel {
    public static void main(String[] args) {
        try {
            // ARFF data as a string
            String arffData = 
                "@relation item_category\n\n" +
                "@attribute item_category {Electronics, Clothing, Furniture}\n" +
                "@attribute item_name string\n" +
                "@attribute brand string\n" +
                "@attribute ram string\n" +
                "@attribute model string\n" +
                "@attribute colour string\n" +
                "@attribute size string\n" +
                "@attribute type string\n\n" +
                "@data\n" +
                "Electronics, \"iPhone X\", Apple, 8GB, \"iPhone X\", Silver, ?, ?\n" +
                "Clothing, \"Top\", Nike, ?, ?, Black, Large, ?\n" +
                "Furniture, \"IKEA Sofa\", IKEA, ?, ?, Brown, ?, Sofa\n" +
                "Electronics, \"Console\", Sony, ?, \"play station 5\", gold, ?, ?\n" +
                "Furniture, \"IKEA Bookcase\", IKEA, ?, ?, white, ?, Bookcase\n" +
                "Clothing, \"Shoes\", Nike, ?, ?, blue, 7, ?\n" +
                "Clothing, \"Jacket\", Next, ?, ?, blue, Medium, ?\n" +
                "Furniture, \"DFS Chair\", DFS, ?, ?, white, ?, chair\n" +
                "Electronics, \"Air Pods\", Apple, ?, ?, white, ?, ?\n" +
                "Electronics, \"Samsung S20\", Samsung, 6GB, S20, green, ?, ?\n" +
                "Electronics, \"Samsung S10\", Samsung, 3GB, S10, black, ?, ?\n";

            // Read the ARFF data from the string
            BufferedReader reader = new BufferedReader(new StringReader(arffData));
            Instances data = new Instances(reader);
            reader.close();

            // Convert string attributes to nominal attributes
            StringToNominal filter = new StringToNominal();
            filter.setAttributeRange("2,3,4,5,6,7,8"); // Specify the indices of string attributes
            filter.setInputFormat(data);
            data = Filter.useFilter(data, filter);

            // Set class attribute (category)
            if (data.classIndex() == -1) {
                data.setClassIndex(0);
            }

            // Build classifier
            Classifier classifier = new J48();
            classifier.buildClassifier(data);

            // Specify the model save path
            String modelPath = "item_category.model";

            // Save the model
            SerializationHelper.write(modelPath, classifier);

            System.out.println("Model successfully created and saved to: " + modelPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
