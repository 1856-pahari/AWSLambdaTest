package com.handy.aws.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.core.ResponseInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.gson.Gson;
import java.util.List;

public class InventoryFindFunction implements RequestHandler<HttpQuerystringRequest, String> {

	@Override
    public String handleRequest(HttpQuerystringRequest request, Context context) {
        context.getLogger().log("Input: " + request);
        
        String idAsString = request.getQueryStringParameters().get("id");
        Integer productId = Integer.parseInt(idAsString);
        
        Product product = getProductById(productId);
        
        return product.toString();
    }

	private Product getProductById(int prodId) {
		//Declare the region we are working in
        Region region = Region.US_EAST_2;
        
        //Obtain a reference to the S3Client type so that we can work with the s3 buckets in that region
        S3Client s3client = S3Client.builder().region(region).build();
        
        //Next we specify exactly which bucket we want to access in that region and what object using its key
        //that we are accessing within that bucket
        ResponseInputStream<?> objectData = s3client.getObject(GetObjectRequest.builder()
        		.bucket("handy-inventory-data92")
        		.key("handy-tool-catalog.json")
        		.build());
        
        //with the ref to our textfile, we create an inputstream reader and a buffered Reader
        InputStreamReader isr = new InputStreamReader(objectData);
        BufferedReader reader = new BufferedReader(isr);
        
//        String outputString = null;
//        try {
//        	//We only need one call to readline bcoz we know the text has only a single line
//        	outputString = reader.readLine();
//        	reader.close();
//        }
//        catch(IOException ie) {
//        	context.getLogger().log("An exception was generated when attempting to readline()");
//        }
//        //After reading the text into the string , we return the string to the client
//        return outputString;
        
        Product[] products = null;
        
        Gson gson = new Gson();
        products = gson.fromJson(reader, Product[].class);
        
        for(Product prod : products) {
        	if(prod.getId()==prodId) {
        		return prod;
        	}
        }
        return null;
	}

}
