//: com.yulikexuan.cloudlab.sample.api.v1.ApiPaths.java


package com.yulikexuan.cloudlab.sample.api.v1;


public interface ApiPaths {

    String API_PATH_CUSTOMERS = "/api/v1/customers";
    String API_PATH_VARIABLE_ID = "/{id}";
    String API_PATH_CUSTOMER_BY_ID = API_PATH_CUSTOMERS + API_PATH_VARIABLE_ID;

    String API_PATH_CATEGORIES = "/api/v1/categories";
    String API_PATH_CATEGORY_NAME = API_PATH_CATEGORIES + "/{name}";

}///:~
