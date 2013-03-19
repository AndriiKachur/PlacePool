package org.tourism.places.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.placepool.places.dao.PlaceDao;
import org.placepool.places.domain.PlaceEntity;
import org.placepool.places.domain.PlaceInfoEntity;
import org.placepool.places.domain.PlaceInfoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/appServlet/servlet-context.xml" })
public class PlaceDaoImplntegrationTest {

	@Autowired
	private PlaceDao placeDao;

	@Test
	public void testContext() {
		assertNotNull(placeDao);
	}

	@Test
	public void testPersist() {
		PlaceEntity ent = new PlaceEntity();
		ent.setName(new Object().toString());

		PlaceInfoEntity info = new PlaceInfoEntity();
		info.setType(PlaceInfoType.CONTACT);
		info.setValue(new Object().toString());
		info.setPlace(ent);

		ent.getInfo().add(info);

		placeDao.persist(ent);
		assertNotNull(ent.getId());

		ent = (PlaceEntity) placeDao.get(ent.getClass(), ent.getId());
		assertTrue(ent.getInfo().size() > 0);
		assertNotNull(ent.getInfo().get(0).getId());

		info = (PlaceInfoEntity) placeDao.get(info.getClass(), info.getId());
		assertNotNull(info.getPlace());
	}

	@Test
	public void testDelete() {
		PlaceEntity ent = new PlaceEntity();
		ent.setName(new Object().toString());

		placeDao.persist(ent);
		Long id = ent.getId();
		assertNotNull(id);

		PlaceEntity saved = (PlaceEntity) placeDao.get(PlaceEntity.class, id);
		assertEquals(ent.getId(), saved.getId());
		assertEquals(ent.getName(), saved.getName());

		placeDao.delete(saved);
		PlaceEntity deleted = (PlaceEntity) placeDao.get(PlaceEntity.class, id);
		assertNull(deleted);
	}

	@Test
	public void testUpdate() {
		PlaceEntity ent = new PlaceEntity();
		String name1 = new Object().toString();
		ent.setName(name1);

		placeDao.persist(ent);
		String name2 = "name2";
		ent.setName(name2);
		placeDao.update(ent);
		PlaceEntity updated = (PlaceEntity) placeDao.get(PlaceEntity.class, ent.getId());
		assertEquals(name2, updated.getName());
	}

}
