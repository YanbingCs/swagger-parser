package com.proxzone.cloud;


import io.swagger.models.*;
import io.swagger.models.parameters.Parameter;
import io.swagger.parser.SwaggerParser;
import io.swagger.v3.parser.util.ClasspathHelper;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author mayanbin@proxzone.com
 * @version 1.0
 * @date 18-9-5 下午1:12
 */

public class SwaggerParserJavaApplication {

    public static void main(String[] args) {
       // SpringApplication.run(SwaggerParserJavaApplication.class, args);
     //   Swagger swagger = new SwaggerParser().parse("petstore-swagger.yml");

        String swaggerAsString = ClasspathHelper.loadFileFromClasspath("petstore-swagger.yml");
        // read a swagger description from the petstore
        Swagger swagger = new SwaggerParser().parse(swaggerAsString);

        String host = swagger.getHost();
        String basePath = swagger.getBasePath();
        Info info = swagger.getInfo();
        String title = info.getTitle();
        String description = info.getDescription();

        System.out.println("\n===============================================");
        System.out.println("host:: [" + host + "]");
        System.out.println("basePath:: [" + basePath + "]");
        System.out.println("title:: [" + title + "]");
        System.out.println("description:: [" + description + "]");
        System.out.println("===============================================");
        Map<String, Path> paths = swagger.getPaths();
        Set<String> keys = paths.keySet();
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            String key = iterator.next();
            Path path = paths.get(key);
            System.out.println("url:" + key);

            Map<HttpMethod, Operation> operationMap = path.getOperationMap();
            Set<HttpMethod> operationsKeySet = operationMap.keySet();
            for (Iterator<HttpMethod> operIterator = operationsKeySet.iterator(); operIterator.hasNext();) {
                HttpMethod httpMethod = operIterator.next();
                 Operation operation = operationMap.get(httpMethod);
                System.out.println("http:" + httpMethod);
                System.out.println(" tags :"+operation.getTags());
                System.out.println("description:	" + operation.getDescription());
                System.out.println("  operationId : "+operation.getOperationId());
                System.out.println(" summary:	" + operation.getSummary());
                System.out.println("  consumes:  "+operation.getConsumes());
                System.out.println("   produces:	" + operation.getProduces());
                List<Parameter> parameterList = operation.getParameters();
                for (int i=0;i<parameterList.size();i++){
                    System.out.println("  in:  "+parameterList.get(i).getIn());
                    System.out.println("  name:  "+parameterList.get(i).getName());
                    System.out.println("  description:  "+parameterList.get(i).getDescription());
                    System.out.println("  required:  "+parameterList.get(i).getRequired());

                }
                Map<String, Response> responseMap = operation.getResponses();
                System.out.println("response:" + responseMap);
                System.out.println(" security" + operation.getSecurity());










            }
        }

        System.out.println("===============================================\n");
    }
}
