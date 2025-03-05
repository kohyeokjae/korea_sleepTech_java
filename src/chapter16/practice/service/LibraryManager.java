package chapter16.practice.service;

import java.util.ArrayList;
import java.util.List;

import chapter16.practice.entity.Book;
import chapter16.practice.entity.Category;
import chapter16.practice.entity.Item;

public class LibraryManager implements Manageable {
	// 데이터 저장소(repository 역할)
	private List<Item> items = new ArrayList<Item>();
	
	public LibraryManager() {
		items.add(new Book("1", "자바", "책1", "홍길동", "한양", Category.FANTASY));
		items.add(new Book("1", "자바", "책1", "홍길동", "한양", Category.FICTION));
		items.add(new Book("1", "자바", "책1", "홍길동", "한양", Category.HISTORY));
		items.add(new Book("1", "자바", "책1", "홍길동", "한양", Category.NONFICTION));
		items.add(new Book("1", "자바", "책1", "홍길동", "한양", Category.FANTASY));
		items.add(new Book("1", "자바", "책1", "홍길동", "한양", Category.SCIENCE));
		items.add(new Book("1", "자바", "책1", "홍길동", "한양", Category.FICTION));
	}
	@Override
	public List<Item> searchByCategory(Category category) {
		List<Item> result = new ArrayList<Item>();
		
		try {
			for (Item item : items) {
				// 다운 캐스팅이 가능한지 확인
				if (item instanceof Book && ((Book) item).getCategory() == category) {
					// 검색하고자 하는 카테고리와 일치
					result.add(item);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}
