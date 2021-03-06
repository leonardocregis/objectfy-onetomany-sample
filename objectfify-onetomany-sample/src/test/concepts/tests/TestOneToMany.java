package concepts.tests;

import static com.google.devrel.training.conference.service.OfyService.ofy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;

import com.google.devrel.training.conference.service.Inside;
import com.google.devrel.training.conference.service.WithCollection;

public class TestOneToMany {

	private static final String TESTE = "Teste";
	private LocalServiceTestHelper helper;

	
	@Before
	public void setUp() throws Exception {

		if (helper == null) {
			LocalDatastoreServiceTestConfig localDatastoreService = new LocalDatastoreServiceTestConfig();
			localDatastoreService.setApplyAllHighRepJobPolicy();
			helper = new LocalServiceTestHelper(localDatastoreService);
		}
		helper.setUp();
	}

	/** */
	@Test
	public void queryForStringInList() throws Exception {
		ofy().factory().register(WithCollection.class);
		ofy().factory().register(Inside.class);
		
		Inside in = new Inside();
		Key<Inside> key = ofy().factory().allocateId(Inside.class);
		in.id = key.getId();
		ofy().save().entity(in).now();
		long idIn = in.id;
		in = null;
		Key<Inside> inKey = Key.create(Inside.class,idIn);
		in = ofy().load().key(inKey).now();
		assertNotNull(in);
		assertNotNull(in.id);
		
		Key<WithCollection> keyWc = ofy().factory().allocateId(WithCollection.class);
		WithCollection wc = new WithCollection();
		long wcId = keyWc.getId();
		wc.id = wcId;
		wc.getStuff().add(inKey);
		wc.getBeum().add(TESTE);
		ofy().save().entity(wc).now();
		wc = null;
		wc = ofy().load().key(keyWc).now();
		assertNotNull(wc);
		assertNotNull(wc.id);

		Key<WithCollection> withCollectionKey = Key.create(WithCollection.class,wcId);
		assertEquals(keyWc, withCollectionKey);
		
		Iterable<WithCollection> list = ofy().load().type(WithCollection.class).filter("beum", TESTE).list();

		Iterator<WithCollection> it = list.iterator();
		assertEquals(it.hasNext(), true);
		System.out.println(it.hasNext());
		WithCollection aux = it.next();
		System.out.println("result ="+aux);
		assertNotNull(aux);
		
		list = ofy().load().type(WithCollection.class).filter("stuff", in).list();
		assertNotNull(list);
	}
}
