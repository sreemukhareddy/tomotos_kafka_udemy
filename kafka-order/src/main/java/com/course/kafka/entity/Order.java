package com.course.kafka.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {

	@Column(nullable = false, length = 20)
	private String creditCardNumber;

	@Column(nullable = false)
	private LocalDateTime orderDateTime;

	@Id
	@GeneratedValue
	private int orderId;

	@Column(nullable = false, length = 200)
	private String orderLocation;

	@Column(nullable = false, length = 20)
	private String orderNumber;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;

}
