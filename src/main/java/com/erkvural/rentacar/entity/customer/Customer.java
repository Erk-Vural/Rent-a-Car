package com.erkvural.rentacar.entity.customer;

import com.erkvural.rentacar.core.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor

@Entity
@Table(name = "CUSTOMERS")
public class Customer extends User {
}
