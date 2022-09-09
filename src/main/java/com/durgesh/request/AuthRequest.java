package com.durgesh.request;

import lombok.Data;
 

@Data 
public class AuthRequest {
   
    private String email;
     
   
    private String password;
 
    // getters and setters are not shown...
}