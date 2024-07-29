package com;

import weka.core.Instances;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.classifiers.Classifier;
import weka.core.SerializationHelper;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToNominal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

@WebServlet("/classifyItem")
public class ClassifyItemServlet extends HttpServlet {
    private Classifier classifier;
    private Instances header;
    private StringToNominal filter;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Load the model
            String modelPath = getServletContext().getRealPath("/WEB-INF/item_category.model");
            classifier = (Classifier) SerializationHelper.read(modelPath);

            // Prepare the header with the same structure as the training data
            String arffHeader =
                "@relation item_category\n\n" +
                "@attribute item_category {Electronics, Clothing, Furniture}\n" +
                "@attribute item_name string\n" +
                "@attribute brand string\n" +
                "@attribute ram string\n" +
                "@attribute model string\n" +
                "@attribute colour string\n" +
                "@attribute size string\n" +
                "@attribute type string\n\n" +
                "@data\n";

            header = new Instances(new BufferedReader(new StringReader(arffHeader)));
            header.setClassIndex(0);

            // Set up the StringToNominal filter
            filter = new StringToNominal();
            filter.setAttributeRange("2,3,4,5,6,7,8"); // Specify the indices of string attributes
            filter.setInputFormat(header);
        } catch (Exception e) {
            throw new ServletException("Failed to initialize servlet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Collect input from the web form
            String itemName = request.getParameter("item_name");
            String brand = request.getParameter("brand");
            String ram = request.getParameter("ram");
            String model = request.getParameter("model");
            String colour = request.getParameter("colour");
            String size = request.getParameter("size");
            String type = request.getParameter("type");

            // Create an instance for the input data
            Instance instance = new DenseInstance(8);
            instance.setDataset(header);
            instance.setValue(1, itemName);
            instance.setValue(2, brand);
            instance.setValue(3, ram != null ? ram : ""); // Handle null values
            instance.setValue(4, model != null ? model : ""); // Handle null values
            instance.setValue(5, colour != null ? colour : ""); // Handle null values
            instance.setValue(6, size != null ? size : ""); // Handle null values
            instance.setValue(7, type != null ? type : ""); // Handle null values

            // Apply the filter
            Instances data = new Instances(header);
            data.add(instance);
            data = Filter.useFilter(data, filter);
            Instance filteredInstance = data.firstInstance();

            // Classify the instance
            double classIndex = classifier.classifyInstance(filteredInstance);
            String predictedCategory = header.classAttribute().value((int) classIndex);

            // Send the response
            response.setContentType("text/plain");
            response.getWriter().write("Predicted Category: " + predictedCategory);
        } catch (Exception e) {
            throw new ServletException("Failed to classify item", e);
        }
    }
}
