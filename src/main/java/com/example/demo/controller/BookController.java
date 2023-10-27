package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BooksDto;
import com.example.demo.dto.IndividualPaymentDetailDto;
import com.example.demo.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/book-service")
public class BookController {

	private final BookService service;

	@GetMapping("/all-names")
	public ResponseEntity<?> name() {
		System.out.println("REquest is enterd into controller");
		return new ResponseEntity<>(service.getAllNames(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/all-names/{name}")
	public ResponseEntity<?> getDto(@PathVariable String name) {
		System.out.println("Request is enterd single book api");
		return new ResponseEntity<>(
				BooksDto.builder().author("KSBhagawan").bookName("ramayanatruth").edition("first").serlNo(2).build(),
				HttpStatus.OK);
	}

	@GetMapping("/all-names/type/{name}")
	public ResponseEntity<?> getDtoo(@PathVariable String name) {
		System.out.println("all books details api call entered...");

//		return new ResponseEntity<>(IntStream.rangeClosed(1, 50).mapToObj(x -> x).map(x -> {
//			return x > 48 && x < 50 ? null
//					: BooksDto.builder().author("KSBhagawan" + x)
//							.bookName(Math.floor(Math.random() * 100) + "sjhbd" + x)
//							.edition((x % 2 == 0) ? "second" : "first").serlNo(x).build();
//		}).collect(Collectors.toList()), HttpStatus.OK);
		return new ResponseEntity<>(IntStream.rangeClosed(1, 50).mapToObj(x -> x).map(x -> {
			return BooksDto.builder().author("KSBhagawan" + x).bookName(Math.floor(Math.random() * 100) + "sjhbd" + x)
					.edition((x % 2 == 0) ? "second" : "first").serlNo(x).build();
		}).collect(Collectors.toList()), HttpStatus.OK);

	}

	@PostMapping("/add-books")
	public ResponseEntity<?> saveBooks(@RequestBody List<BooksDto> dtos) {
		System.out.println(dtos.size() + " books added to storage");

		return new ResponseEntity<>(service.saveBooks(dtos), HttpStatus.ACCEPTED);
	}

	@PostMapping("/add-book")
	public ResponseEntity<?> saveBook(@RequestBody BooksDto dto) {
		System.out.println("request entered single book api");

		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	@PostMapping("/get-name/{name}")
	public ResponseEntity<?> validatePost(@PathVariable String name, @RequestBody BooksDto dto) {
		return new ResponseEntity<>(name, HttpStatus.ACCEPTED);
	}

	@GetMapping("/search-book")
	public ResponseEntity<?> searchBook(@RequestParam String name) {
		System.out.println("Search api entered...");
		return new ResponseEntity<>(service.searchByBookName(name), HttpStatus.OK);
	}

	@GetMapping("/with-image")
	public ResponseEntity<?> getBookWithImage() {
		System.out.println("Book image api entered ....");
		return new ResponseEntity<>(service.getBookWithImage(), HttpStatus.OK);
	}

	@GetMapping("/payment-history")
	public ResponseEntity<?> getPaymentsHistory() {
		System.out.println("Payment history api called ...");
		return new ResponseEntity<>(service.getPaymentHistory(), HttpStatus.OK);
	}

	@GetMapping("/payment-history/detailed")
	public ResponseEntity<?> getDerailedPaymentHistory(@RequestParam String transactionId) {
		System.out.println("Payment details api.....");
		IndividualPaymentDetailDto detailedPaymentInfo = service.getDetailedPaymentInfo(transactionId);
		return new ResponseEntity<>(detailedPaymentInfo,
				detailedPaymentInfo.getAppTransactionId().equals("") == true ? HttpStatus.NOT_FOUND
						: HttpStatus.ACCEPTED);
	}

	@GetMapping("/payment-history/moth-wise")
	public ResponseEntity<?> getMonthWisePaymentHistory() {
		System.out.println("Payment details month wise api.....");
		return new ResponseEntity<>(service.getPaymentHistoryMothWise(), HttpStatus.OK);
	}
	
	@GetMapping("/user-list")
	public ResponseEntity<?> getAllUsers() {
		System.out.println("User's list api.....");
		return new ResponseEntity<>(service.getAllUser(), HttpStatus.OK);
	}

}
