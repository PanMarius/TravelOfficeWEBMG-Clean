package com.inqoo.travelofficeweb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerNameDetails {
    private String firstName;
    private String lastName;
    private int age;
    private String pesel;
    private String phone;
}
