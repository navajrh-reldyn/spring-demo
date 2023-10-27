package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Products {

	private int id;
	private String title;
	private String description;
	private long price;
	private double discountPercentage;
	private double rating;
	private int stock;
	private String brand;
	private String category;
	private String thumbnail;
	private List<String> images;
}
//{
//"id": 1,
//"title": "iPhone 9",
//"description": "An apple mobile which is nothing like apple",
//"price": 549,
//"discountPercentage": 12.96,
//"rating": 4.69,
//"stock": 94,
//"brand": "Apple",
//"category": "smartphones",
//"thumbnail": "https://i.dummyjson.com/data/products/1/thumbnail.jpg",
//"images": [
//"https://i.dummyjson.com/data/products/1/1.jpg",
//"https://i.dummyjson.com/data/products/1/2.jpg",
//"https://i.dummyjson.com/data/products/1/3.jpg",
//"https://i.dummyjson.com/data/products/1/4.jpg",
//"https://i.dummyjson.com/data/products/1/thumbnail.jpg"
//]
//}