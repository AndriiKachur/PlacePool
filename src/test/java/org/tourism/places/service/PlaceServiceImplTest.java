package org.tourism.places.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.placepool.places.dao.PlaceDao;
import org.placepool.places.domain.Identifiable;
import org.placepool.places.domain.PlaceEntity;
import org.placepool.places.service.PlaceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PlaceServiceImplTest {
	private static final Long ID = 1L;
	private static final Long EXISTING_ID = 2L;

	@Mock
	private PlaceDao placeDao;
	@InjectMocks
	private PlaceServiceImpl sut;

	@Before
	public void setUp() {
		doAnswer(new Answer<Identifiable>() {
			@Override
			public Identifiable answer(InvocationOnMock invocation)
					throws Throwable {
				Identifiable place = ((Identifiable<Long>) invocation
						.getArguments()[0]);
				if (place.getId() == null) {
					place.setId(ID);
				}
				return place;
			}
		}).when(placeDao).saveOrUpdate(any(Identifiable.class));

		when(placeDao.get(any(Class.class), any(Serializable.class))).then(
				new Answer<Identifiable<Long>>() {
					@Override
					public Identifiable<Long> answer(InvocationOnMock invocation)
							throws Throwable {
						Long id = (Long) invocation.getArguments()[1];
						PlaceEntity place = new PlaceEntity();
						place.setId(id);
						return place;
					}
				});
	}

	@Test
	public void shouldSaveOrUpdateNewPlace() {
		PlaceEntity place = newPlace(false);
		PlaceEntity saved = sut.saveOrUpdate(place);

		assertEquals(ID, saved.getId());
		assertNotNull(saved.getCreateDate());
	}

	@Test
	public void shouldSaveOrUpdateExistingPlace() {
		PlaceEntity place = newPlace(false);
		place.setId(EXISTING_ID);
		PlaceEntity savedPlace = sut.saveOrUpdate(place);

		assertEquals(EXISTING_ID, savedPlace.getId());
	}

	private PlaceEntity newPlace(boolean hasId) {
		PlaceEntity place = new PlaceEntity();
		place.setDescription("description");
		place.setName("name");
		if (hasId) {
			place.setId(ID);
		}

		return place;
	}

	@Test
	public void shouldGetPublished() {
		sut.getPublished(ID);
		Mockito.verify(placeDao).getPublished(ID);
	}

	@Test
	public void shouldGetNotPublished() {
		sut.getNotPublished(ID);
		Mockito.verify(placeDao).getNotPublished(ID);
	}

	@Test
	public void shouldListPublished() {
		sut.listPublished();
		verify(placeDao).listPublished();
	}

	@Test
	public void shouldListNotPublished() {
		sut.listNotPublished();
		verify(placeDao).listNotPublished();
	}
}
