// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import weka.core.Attribute;
// import weka.core.DenseInstance;
// import weka.core.Instance;
// import weka.core.Instances;
// import weka.classifiers.Classifier;
// import weka.classifiers.trees.J48;
// import weka.filters.Filter;
// import weka.filters.unsupervised.attribute.StringToNominal;
// import java.io.IOException;
// import java.io.StringReader;
// import weka.core.converters.ArffLoader.ArffReader;

// @WebServlet("/classify")
// public class ItemClassifierServlet extends HttpServlet {

//     private Classifier classifier;
//     private Instances dataStructure;

//     @Override
//     public void init() throws ServletException {
//         try {
//             // ARFF data as a string
//             String arffData = "@relation item_classification\n\n" +
//                     "@attribute category {Electronics, Clothing, Furniture}\n" +
//                     "@attribute name string\n" +
//                     "@attribute features string\n\n" +
//                     "@data\n" +
//                     "Electronics, iPhone X, \"Brand: Apple, RAM:8GB, Model: iPhone X, Colour: Silver\"\n" +
//                     "Clothing, Top, \"Brand: Nike, Size: Large, Colour: Black\"\n" +
//                     "Furniture, IKEA Sofa, \"Brand: IKEA, Type: Sofa, Colour: Brown\"\n" +
//                     "Electronics, Console, \"Brand: Sony, Colour: gold, Model: play station 5\"\n" +
//                     "Furniture, IKEA Bookcase, \"Brand: IKEA, Type: Bookcase, Colour: white\"\n" +
//                     "Clothing, Shoes, \"Brand: Nike, Size:7, Colour: blue\"\n" +
//                     "Clothing, Jacket, \"Brand: Next, Size: Medium, Colour: blue\"\n" +
//                     "Furniture, DFS Chair, \"Brand: DFS, Type: chair, Colour: white\"\n" +
//                     "Electronics, Air Pods, \"Brand: Apple, Colour: white\"\n" +
//                     "Electronics, Samsung S20, \"Brand: Samsung, RAM:6GB, Model:S20, Colour: green\"\n" +
//                     "Electronics, Samsung S10, \"Brand: Samsung, RAM:3GB, Model:S10, Colour: black\"";

//             // Load ARFF data from string
//             ArffReader reader = new ArffReader(new StringReader(arffData));
//             Instances data = reader.getData();

//             // Apply StringToNominal filter
//             StringToNominal filter = new StringToNominal();
//             filter.setAttributeRange("first-last");
//             filter.setInputFormat(data);
//             Instances filteredData = Filter.useFilter(data, filter);

//             // Set class index
//             filteredData.setClassIndex(0); // Set category as class index (assuming it is the first attribute)

//             // Build classifier
//             classifier = new J48();
//             classifier.buildClassifier(filteredData);

//             // Store data structure for later use
//             dataStructure = filteredData;
//         } catch (Exception e) {
//             throw new ServletException("Failed to initialize model", e);
//         }
//     }

//     @Override
//     protected void doPost(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         String name = request.getParameter("name");
//         String features = request.getParameter("features");

//         try {
//             // Use known values for testing
//             // Optionally, you can use user-provided values in production
//             // String name = "iPhone X";
//             // String features = "Brand: Apple, RAM:8GB, Model: iPhone X, Colour: Silver";

//             // Create a new instance with known values
//             Instance newInstance = createInstance(name, features);

//             // Apply the same StringToNominal filter to the new instance
//             StringToNominal filter = new StringToNominal();
//             filter.setAttributeRange("first-last");
//             filter.setInputFormat(dataStructure);
//             Instances structureWithNewInstance = new Instances(dataStructure, 0);
//             structureWithNewInstance.add(newInstance);
//             Instances filteredNewInstance = Filter.useFilter(structureWithNewInstance, filter);

//             // Classify the new instance
//             double labelIndex = classifier.classifyInstance(filteredNewInstance.firstInstance());
//             String predictedCategory = dataStructure.classAttribute().value((int) labelIndex);

//             // Forward to result JSP
//             request.setAttribute("productName", name);
//             request.setAttribute("productFeatures", features);
//             request.setAttribute("predictedCategory", predictedCategory);
//             request.getRequestDispatcher("result.jsp").forward(request, response);

//         } catch (Exception e) {
//             e.printStackTrace();
//             throw new ServletException("Error processing classification", e);
//         }
//     }

//     private Instance createInstance(String name, String features) throws Exception {
//         Instance instance = new DenseInstance(dataStructure.numAttributes());
//         instance.setDataset(dataStructure);

//         // Set values for attributes
//         Attribute nameAttr = dataStructure.attribute("name");
//         Attribute featuresAttr = dataStructure.attribute("features");

//         // For string attributes, directly set the value
//         instance.setValue(nameAttr, name);
//         instance.setValue(featuresAttr, features);

//         return instance;
//     }
// }
