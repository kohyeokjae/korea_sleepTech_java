package chapter10.practice02.repository;

import java.util.Set;

import chapter10.practice02.entity.Item;

public interface ItemRepository {
	// cmd + shift + o: 자동 import 단축키
	void addItem(Item item);
	void removeItem(String itemId);
	Item findById(String itmeId);
	Set<Item> findAll();
}
