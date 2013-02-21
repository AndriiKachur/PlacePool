package org.tourism.places.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.placepool.places.dao.GenericDao;
import org.placepool.places.domain.PlaceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/appServlet/servlet-context.xml" })
public class GenericDaoImplntegrationTest {

	@Autowired
	private GenericDao<Long> genericDao;

	@Test
	public void testContext() {
		assertNotNull(genericDao);
	}

	@Test
	public void testPersist() {
		PlaceEntity ent = new PlaceEntity();
		ent.setName(new Object().toString());
		genericDao.persist(ent);
		assertNotNull(ent.getId());
	}

	@Test
	public void testDelete() {
		PlaceEntity ent = new PlaceEntity();
		ent.setName(new Object().toString());
		genericDao.persist(ent);
		Long id = ent.getId();
		assertNotNull(id);
		PlaceEntity saved = (PlaceEntity) genericDao.get(PlaceEntity.class, id);
		assertEquals(ent.getId(), saved.getId());
		assertEquals(ent.getName(), saved.getName());
		genericDao.delete(saved);
		PlaceEntity deleted = (PlaceEntity) genericDao.get(PlaceEntity.class, id);
		assertNull(deleted);
	}

	@Test
	public void testUpdate() {
		PlaceEntity ent = new PlaceEntity();
		String name1 = new Object().toString();
		ent.setName(name1);
		genericDao.persist(ent);
		String name2 = "name2";
		ent.setName(name2);
		genericDao.update(ent);
		PlaceEntity updated = (PlaceEntity) genericDao.get(PlaceEntity.class, ent.getId());
		assertEquals(name2, updated.getName());
	}

}
