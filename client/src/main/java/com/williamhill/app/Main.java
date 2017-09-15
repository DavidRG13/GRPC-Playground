package com.williamhill.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamhill.json.EmployeeJson;
import com.williamhill.protobuf.EmployeeClient;
import com.williamhill.protobuf.EmployeeService.Employee;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
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

        long start = System.currentTimeMillis();
        HttpGet httpGet = new HttpGet("http://localhost:8080/employee/12");
        httpGet.addHeader(HttpHeaders.ACCEPT, "application/x-protobuf");
        CloseableHttpResponse execute = httpclient.execute(httpGet);
        Employee employee = Employee.parseFrom(execute.getEntity().getContent());
        System.out.println("getProto = " + (System.currentTimeMillis() - start));
        //System.out.println("employee = " + employee);

        ObjectMapper mapper = new ObjectMapper();

        start = System.currentTimeMillis();
        HttpGet get = new HttpGet("http://localhost:8080/employee/12");
        get.addHeader(HttpHeaders.ACCEPT, "application/json");
        CloseableHttpResponse execute2 = httpclient.execute(get);
        EmployeeJson employee2 = mapper.readValue(execute2.getEntity().getContent(), EmployeeJson.class);
        System.out.println("getJson = " + (System.currentTimeMillis() - start));
        //System.out.println("employee2 = " + employee2);

        System.out.println("------JSON------");
        ArrayList<Long> responseTimesJson = new ArrayList<>(30_000);
        for (int i = 0; i < 30_000; i++) {
            HttpGet get1 = new HttpGet("http://localhost:8080/employee/12");
            get1.addHeader(HttpHeaders.ACCEPT, "application/json");
            CloseableHttpResponse execute5 = httpclient.execute(get1);
            EmployeeJson employee8 = mapper.readValue(execute5.getEntity().getContent(), EmployeeJson.class);
            responseTimesJson.add(System.currentTimeMillis() - start);
        }
        responseTimesJson.stream().sorted().collect(Collectors.groupingBy(x -> x))
            .forEach((responseTime, times) -> System.out.println("responseTime " + responseTime + " ms " + times.size() + " times"));


        start = System.currentTimeMillis();
        HttpPost post = new HttpPost("http://localhost:8080/employee");
        post.setEntity(new ByteArrayEntity(arun.toByteArray()));
        post.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-protobuf");
        httpclient.execute(post);
        System.out.println("postProto = " + (System.currentTimeMillis() - start));

        EmployeeJson employeeJson = new EmployeeJson("Arun", "Kumar", 10, 100000);

        start = System.currentTimeMillis();
        HttpPost post2 = new HttpPost("http://localhost:8080/employee");
        post2.setEntity(new StringEntity(mapper.writeValueAsString(employeeJson)));
        post2.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpclient.execute(post2);
        System.out.println("postJson = " + (System.currentTimeMillis() - start));


        EmployeeClient employeeClient = new EmployeeClient("localhost", 8980);
        start = System.currentTimeMillis();
        Employee employee1 = employeeClient.getEmployeeWithId(12);
        System.out.println("GRPC = " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        Employee employee6 = employeeClient.getEmployeeWithId(12);
        System.out.println("GRPC = " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        Employee employee3 = employeeClient.getEmployeeWithId(12);
        System.out.println("GRPC = " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        Employee employee4 = employeeClient.getEmployeeWithId(12);
        System.out.println("GRPC = " + (System.currentTimeMillis() - start));
        //System.out.println("employee1 = " + employee1);

        System.out.println("------GPRC------");
        start = System.currentTimeMillis();
        ArrayList<Long> responseTimeGprc = new ArrayList<>(30_000);
        for (int i = 0; i < 30_000; i++) {
            final long start2 = System.currentTimeMillis();
            Employee employee7 = employeeClient.getEmployeeWithId(12);
            responseTimeGprc.add(System.currentTimeMillis() - start2);
        }
        final long end = System.currentTimeMillis() - start;

        responseTimeGprc.stream().sorted().collect(Collectors.groupingBy(x -> x))
            .forEach((responseTime, times) -> System.out.println(responseTime + " ms " + times.size() + " times"));

        System.out.println("GRPC finished in " + end + " ms");

        httpclient.close();

    }
}
