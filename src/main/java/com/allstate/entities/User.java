package com.allstate.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
public class User {
    private int id;
    private String email;
}
