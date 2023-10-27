

package com.example.demo.service;

import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BooksDto;
import com.example.demo.dto.IndividualPaymentDetailDto;
import com.example.demo.dto.PaymentDetailsDto;
import com.example.demo.dto.PaymentResponseDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;

@Service
public interface BookService {

	String getAllNames();

	LinkedHashMap<String, BooksDto>saveBooks(List<BooksDto> dtos);
	
	List<BooksDto> searchByBookName(String name);
	
	List<ProductDto> getBookWithImage();
	
	PaymentResponseDto getPaymentHistory();
	
	IndividualPaymentDetailDto getDetailedPaymentInfo(String transactionId);
	
	Map<Integer,PaymentResponseDto> getPaymentHistoryMothWise();
	
	List<UserDto> getAllUser();
	
}
