package model;

public record Bill(int order_id, String clientName, String productName, int quantity, int price) {
}
