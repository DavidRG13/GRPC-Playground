package com.williamhill.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamhill.protobuf.EmployeeJson;
import com.williamhill.protobuf.EmployeeProto.Employee;
import java.io.IOException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Main {

    public static void main(String[] args) throws IOException {
        Employee.Builder empbuild = Employee.newBuilder();
        empbuild.setFirstname("Arun");
        empbuild.setLastname("Kumar");
        empbuild.setId(10);
        empbuild.setSalary(100000);

        Employee arun = empbuild.build();

        //try {
        //    // write
        //    FileOutputStream output = new FileOutputStream("Employee.ser");
        //    arun.writeTo(output);
        //    output.close();
        //
        //    // read
        //    Employee empFromFile = Employee.parseFrom(new FileInputStream("Employee.ser"));
        //    System.out.println(empFromFile);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        CloseableHttpClient httpclient = HttpClients.createDefault();

        long start = System.nanoTime();
        //HttpGet httpGet = new HttpGet("http://localhost:8080/foo");
        //httpGet.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-protobuf");
        //CloseableHttpResponse execute = httpclient.execute(httpGet);
        //Employee employee = Employee.parseFrom(execute.getEntity().getContent());
        //System.out.println("getProto = " + (System.nanoTime() - start));
        //System.out.println("employee = " + employee);

        ObjectMapper mapper = new ObjectMapper();

        start = System.nanoTime();
        HttpGet get = new HttpGet("http://localhost:8080/foo");
        get.addHeader(HttpHeaders.ACCEPT, "application/json");
        CloseableHttpResponse execute2 = httpclient.execute(get);
        EmployeeJson employee2 = mapper.readValue(execute2.getEntity().getContent(), EmployeeJson.class);
        System.out.println("getJson = " + (System.nanoTime() - start));
        System.out.println("employee2 = " + employee2);

        //start = System.nanoTime();
        //HttpPost post = new HttpPost("http://localhost:8080/foo");
        //post.setEntity(new ByteArrayEntity(arun.toByteArray()));
        //post.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-protobuf");
        //httpclient.execute(post);
        //System.out.println("postProto = " + (System.nanoTime() - start));

        EmployeeJson employeeJson = new EmployeeJson("Arun", "Kumar", 10, 100000);

        start = System.nanoTime();
        HttpPost post2 = new HttpPost("http://localhost:8080/foo");
        post2.setEntity(new StringEntity(mapper.writeValueAsString(employeeJson)));
        post2.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpclient.execute(post2);
        System.out.println("postJson = " + (System.nanoTime() - start));

        httpclient.close();

    }
}
