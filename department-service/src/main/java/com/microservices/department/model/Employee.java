package com.microservices.department.model;

//By Default class will be final it won't be having setters only getters will be there
public record Employee(Long id, Long departmentId,String name,int age,String position) {

}
