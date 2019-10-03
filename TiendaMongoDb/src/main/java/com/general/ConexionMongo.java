package com.general;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class ConexionMongo {
	 static String array_productos[] = {"Silla", "Mesa", "Pata"};
	 static float array_precios[] = {29.99f, 199.99f, 54.98f}; 
	 static int array_unidades[] = {40, 39, 100};
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// Connecting To The MongoDb Server Listening On A Default Port (i.e. 27017).
        MongoClient mongoClntObj = new MongoClient("localhost", 27017);

        // Get MongoDb Database. If The Database Doesn't Exists, MongoDb Will Automatically Create It For You
        DB dbObj = mongoClntObj.getDB("tienda");

        // Get MongoDb Collection. If The Collection Doesn't Exists, MongoDb Will Automatically Create It For You
        DBCollection collectionObj = dbObj.getCollection("productos");

        /**** INSERT OPERATION ****/
        // Creating The MongoDb Documents To Store Key-Value Pair
        BasicDBObject documentObj;
        String address[];
        for(int i = 0 ; i < array_productos.length ; i++) {
            documentObj = new BasicDBObject();              
            documentObj.append("nombres", array_productos[i]);             
            documentObj.append("precio", array_precios[i]);               
            documentObj.append("unidades", array_unidades[i]);              
           collectionObj.insert(documentObj);
           
           
        }
        
        DBCursor cursorObj = collectionObj.find();
        try {
            while(cursorObj.hasNext()) {
                System.out.println(cursorObj.next());
            }
        } finally {
            cursorObj.close();
        }
	}

}
