package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.BooksDto;
import com.example.demo.dto.IndividualPaymentDetailDto;
import com.example.demo.dto.PaymentDetailsDto;
import com.example.demo.dto.PaymentResponseDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ResultObject;
import com.example.demo.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	static int a;
	private final RestTemplate template;

	@Override
	public String getAllNames() {
		// TODO Auto-generated method stub
		return "sndcmnsd, sjdbf, sdb";
	}

	@Override
	public LinkedHashMap<String, BooksDto> saveBooks(List<BooksDto> dtos) {
//		dtos.stream().sorted(Comparator.comparing(BooksDto::getBookName))
//				.collect(Collectors.toMap(BooksDto::getBookName, x -> x)).forEach((a,b)->System.out.println(a+":{ "+b+" }"));

//		Optional.ofNullable(dtos).map(x -> Optional.ofNullable(x)).get().get().stream().sorted().map(x -> {
//			System.out.println(x.getBookName() + ":{" + x + "}");
//			return x;
//		}).collect(Collectors.toMap(BooksDto::getBookName, x -> x, (a, b) -> b, LinkedHashMap::new));

//		if (x.isPresent())
//			return true;
//		else
//			throw new NotNullException(LocalDateTime.now(), "Object is null", true);

		return dtos.stream().map(x -> Optional.ofNullable(x)).filter(x -> x.isPresent()).map(x -> x.get())
				.sorted(Comparator.comparing(BooksDto::getBookName)).map(x -> {
					System.out.println(x.getBookName() + ":{" + x + "}");
					return x;
				}).collect(Collectors.toMap(BooksDto::getBookName, x -> x, (a, b) -> b, LinkedHashMap::new));

//		return Optional.ofNullable(dtos.stream().sorted(Comparator.comparing(BooksDto::getBookName)).map(x -> {
//			System.out.println(x.getBookName() + ":{" + x + "}");
//			return x;
//		}).collect(Collectors.toMap(BooksDto::getBookName, x -> x, (a, b) -> b, LinkedHashMap::new)))
//				.orElseThrow(() -> new RuntimeException("Value cannot be null"));

//		return dtos.stream().sorted(Comparator.comparing(BooksDto::getBookName)).map(x -> {
//			System.out.println(x.getBookName() + ":{" + x + "}");
//			return x;
//		}).collect(Collectors.toMap(BooksDto::getBookName, x -> x, (a, b) -> b, LinkedHashMap::new));
	}

	@Override
	public List<BooksDto> searchByBookName(String name) {

		return IntStream.rangeClosed(1, 50).mapToObj(x -> x).map(x -> {
			return BooksDto.builder().author("KSBhagawan" + x)
					.bookName((int) Math.floor(Math.random() * 100) + "sjhbd" + x)
					.edition((x % 2 == 0) ? "second" : "first").serlNo(x).build();
		}).filter(x -> x.getBookName().contains(name)).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getBookWithImage() {
		a = 0;
//		Optional.ofNullable(template.getForObject("https://dummyjson.com/products", ResultObject.class).getProducts().stream().map(x->x.getImages().get(0)).map(x->{
//			a++;
//			return ProductDto.builder().bookName("sdnbmfsdf").author("msnbfvkhasbvf")
//					.edition("Edition0" + (int) Math.floor(Math.random() * 10)).images(x)
//					.serlNo(a).build();
//		}).collect(Collectors.toList())).orElse(List.of(ProductDto.builder().bookName("sm vsf").author("nsdksdv")
//				.edition("Edition0" + (int) Math.floor(Math.random() * 10)).images(List.of()).serlNo(0)
//				.build()));
//		return null;

		return Optional.ofNullable(template.getForObject("https://dummyjson.com/products", ResultObject.class)
				.getProducts().stream().map(x -> List.of(x.getBrand(), x.getTitle(), x.getImages().get(0))).map(x -> {
					a++;
					return ProductDto.builder().bookName(x.get(1)).author(x.get(0)).serlNo(a).images(x.get(2))
							.edition("Edition0" + (int) Math.floor(Math.random() * 10)).build();
				}).collect(Collectors.toList()))
				.orElse(List.of(ProductDto.builder().bookName("sm vsf").author("nsdksdv")
						.edition("Edition0" + (int) Math.floor(Math.random() * 10)).images("").serlNo(a).build()));

//		return Optional.ofNullable(template.getForObject("https://dummyjson.com/products", ResultObject.class)
//				.getProducts().stream().map(x -> x.getImages().get(0)).map(x -> {
//					a++;
//					return ProductDto.builder().bookName("ashvbdhvdsv")
//							.author("KSBhagawan" + ((int) Math.floor(Math.random() * 1000)))
//							.edition("Edition0" + (int) Math.floor(Math.random() * 10)).images(x).serlNo(a).build();
//				}).collect(Collectors.toList()))
//				.orElse(List.of(ProductDto.builder().bookName("sm vsf").author("nsdksdv")
//						.edition("Edition0" + (int) Math.floor(Math.random() * 10)).images("").serlNo(a).build()));

	}

	@Override
	public IndividualPaymentDetailDto getDetailedPaymentInfo(String transactionId) {
		Optional<IndividualPaymentDetailDto> findFirst = getPayments().stream()
				.filter(x -> x.getTransactionId().equalsIgnoreCase(transactionId.trim())).map(x -> {
					return IndividualPaymentDetailDto.builder().amount(x.getAmount()).dateTime(x.getDateTime())
							.from(Map.of(x.getFrom(), x.getFrom().toLowerCase() + "@ybl.co"))
							.to(Map.of(x.getTo(), x.getTo().toLowerCase() + "@ybl.co")).status(x.isStatus())
							.transactionId(transactionId).bankName(x.getBankName()).method(x.getMethod())
							.appTransactionId(transactionId + (int) Math.floor(Math.random() * 1000)).build();
				}).findFirst();

		return Optional.ofNullable(findFirst.isEmpty() ? null : findFirst.get())
				.orElse(IndividualPaymentDetailDto.builder().amount(0.0).appTransactionId("")
						.dateTime(LocalDateTime.now()).from(Map.of("", "")).to(Map.of("", "")).appTransactionId("")
						.status(false).build());
	}

	@Override
	public PaymentResponseDto getPaymentHistory() {
		return PaymentResponseDto.builder().detailsDtos(getPayments()).totalAmount(
				getPayments().stream().map(x -> x.getAmount()).collect(Collectors.summarizingDouble(x -> x)).getSum())
				.build();
	}

	public List<String> senderNames() {
		return List.of("Gourav Manya", "Umesh Bhai", "Sarthak", "Shubham123", "Shubham22", "Arbhaz", "Govinda Ambekar",
				"Kane Williumson", "Abhijeet", "Abhishek", "Sagar Gojare", "Pranali", "Ravi", "Jayesh", "Jayadev",
				"Shimla", "Snehal", "Trupti", "Mayuri", "Anshul Patil", "Harish", "Komal Utekar", "Pooja", "Aishwarya");
	}

	public List<PaymentDetailsDto> getPayments() {
		return IntStream.rangeClosed(0, 22).mapToObj(x -> x).map(t -> {
			return PaymentDetailsDto.builder().amount(Math.sqrt(t) * 20)
					.dateTime(LocalDateTime.now().minusMonths(t > 5 ? 0 : t))
					.method(t == 10 || t == 17 ? "debited" : "credited")
					.bankName(t == 10 || t == 17 ? "ICICI BANK...0012" : "CANARA BANK...0012")
					.from(t == 10 || t == 17 ? "NavajRH" : senderNames().get(t)).transactionId("1234" + t)
					.to(t == 10 || t == 17 ? senderNames().get(t) : "NavajRH").status(t == 17 ? false : true).build();
		}).collect(Collectors.toList());
	}

	@Override
	public Map<Integer, PaymentResponseDto> getPaymentHistoryMothWise() {

		Map<Integer, PaymentResponseDto> map = new LinkedHashMap<Integer, PaymentResponseDto>();
		getPaymentHistory().getDetailsDtos().stream()
				.collect(Collectors.groupingBy(x -> x.getDateTime().getMonth().getValue())).forEach((key, val) -> {
					map.put(key,
							PaymentResponseDto.builder().detailsDtos(val).totalAmount(val.stream()
									.map(x -> x.getAmount()).collect(Collectors.summarizingDouble(x -> x)).getSum())
									.build());
				});

		return map;
	}

	@Override
	public List<UserDto> getAllUser() {
		a = 0;

		return senderNames().stream().map(x -> {
			a++;
			return UserDto.builder().userName(x).upiId(x.toLowerCase().replaceAll(" ", "").concat("@ybl.co"))
					.userId(x.toUpperCase().replaceAll(" ", "") + "00" + a * a).build();
		}).collect(Collectors.toList());
	}
}
