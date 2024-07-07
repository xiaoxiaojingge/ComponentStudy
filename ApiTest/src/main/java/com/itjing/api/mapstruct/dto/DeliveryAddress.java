package com.itjing.api.mapstruct.dto;

import lombok.Data;

@Data
public class DeliveryAddress {

	private String firstName;

	private String lastName;

	private int height;

	private String street;

	private int zipCode;

	private int houseNumber;

	private String description;

}