package com.course.kafka.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.course.kafka.api.server.request.OrderItemRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "order_items")
public class OrderItem {

	@Column(nullable = false, length = 200)
	private String itemName;

	@Id
	@GeneratedValue
	private int orderItemId;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false)
	private int quantity;
	
	@JoinColumn(name = "order_id")
	@ManyToOne
	private Order order;
	
	public OrderItem(OrderItemRequest orderItemRequest) {
		this.itemName = orderItemRequest.getItemName();
		this.price = orderItemRequest.getPrice();
		this.quantity = orderItemRequest.getQuantity();
	}
}
