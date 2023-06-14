package com.bree.reportmanagement.Model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@Data
public class Order {
   private Long id;
   private String orderId;
   private List<OrderItem> items;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getOrderId() {
      return orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public List<OrderItem> getItems() {
      return items;
   }

   public void setItems(List<OrderItem> items) {
      this.items = items;
   }
}
