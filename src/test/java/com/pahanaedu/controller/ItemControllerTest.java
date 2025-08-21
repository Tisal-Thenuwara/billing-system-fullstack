package com.pahanaedu.controller;

import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.model.Item;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemControllerTest {

	private ItemController itemController;
	private ItemDAO itemDAOMock;

	@BeforeEach
	void setUp() {
		itemController = new ItemController();
		itemDAOMock = mock(ItemDAO.class);

		// Inject mock DAO via reflection
		try {
			var field = ItemController.class.getDeclaredField("dao");
			field.setAccessible(true);
			field.set(itemController, itemDAOMock);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testAddItemSuccess() {
		Item item = new Item();
		when(itemDAOMock.add(item)).thenReturn(true);

		Response response = itemController.add(item);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(item, response.getEntity());
	}

	@Test
	void testAddItemFailure() {
		Item item = new Item();
		when(itemDAOMock.add(item)).thenReturn(false);

		Response response = itemController.add(item);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		assertEquals("{error:Could not add item}", response.getEntity());
	}

	@Test
	void testUpdateItemSuccess() {
		Item item = new Item();
		when(itemDAOMock.update(item)).thenReturn(true);

		Response response = itemController.update(1, item);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(item, response.getEntity());
		assertEquals(1, item.getItemId()); // ID should be set before update
	}

	@Test
	void testUpdateItemFailure() {
		Item item = new Item();
		when(itemDAOMock.update(item)).thenReturn(false);

		Response response = itemController.update(1, item);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		assertEquals("{error:Could not update item}", response.getEntity());
	}

	@Test
	void testDeleteItemSuccess() {
		when(itemDAOMock.delete(1)).thenReturn(true);

		Response response = itemController.delete(1);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals("{message:Deleted}", response.getEntity());
	}

	@Test
	void testDeleteItemFailure() {
		when(itemDAOMock.delete(1)).thenReturn(false);

		Response response = itemController.delete(1);

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		assertEquals("{error:Could not delete item}", response.getEntity());
	}

	@Test
	void testGetItemFound() {
		Item item = new Item();
		when(itemDAOMock.get(1)).thenReturn(item);

		Response response = itemController.get(1);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(item, response.getEntity());
	}

	@Test
	void testGetItemNotFound() {
		when(itemDAOMock.get(1)).thenReturn(null);

		Response response = itemController.get(1);

		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
		assertEquals("{error:Not found}", response.getEntity());
	}

	@Test
	void testListItems() {
		List<Item> items = List.of(new Item(), new Item());
		when(itemDAOMock.listAll()).thenReturn(items);

		List<Item> result = itemController.list();

		assertEquals(items, result);
	}
}
