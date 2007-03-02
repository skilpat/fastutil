package test.it.unimi.dsi.fastutil.objects;

import it.unimi.dsi.fastutil.objects.AbstractReference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceArrayMap;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;

import java.util.Map.Entry;

import junit.framework.TestCase;

public class Reference2ReferenceArrayMapTest extends TestCase {

	public void testMap() {
		for( int i = 0; i <= 2; i++ ) {
			final Reference2ReferenceArrayMap<Object,Object> m = i == 0 ? new Reference2ReferenceArrayMap<Object,Object>() : new Reference2ReferenceArrayMap<Object,Object>( i );
			Integer one = new Integer( 1 ), two = new Integer( 2 ), three = new Integer( 3 );
			assertEquals( null, m.put( one, one ) );
			assertEquals( 1, m.size() );
			assertTrue( m.containsKey( one ) );
			assertTrue( m.containsValue( one ) );
			assertEquals( null, m.put( two, two  ) );
			assertTrue( m.containsKey( two ) );
			assertTrue( m.containsValue( two ) );
			assertEquals( 2, m.size() );
			assertEquals( one, m.put( one, three ) );
			assertTrue( m.containsValue( three ) );
			assertEquals( null, m.remove( three ) );
			assertEquals( null, m.put( three, three  ) );
			assertTrue( m.containsKey( three ) );

			assertEquals( new ReferenceOpenHashSet<Object>( new Object[] { one, two, three } ), new ReferenceOpenHashSet<Object>( m.keySet().iterator() ) );
			assertEquals( new ReferenceOpenHashSet<Object>( new Object[] { three, two, three } ), new ReferenceOpenHashSet<Object>( m.values().iterator() ) );

			for( Entry<Object, Object> e: m.entrySet() ) assertEquals( e.getValue(), m.get( e.getKey() ) );

			assertTrue( m.entrySet().contains( new AbstractReference2ReferenceMap.BasicEntry<Object,Object>( one, three ) ) );
			assertFalse( m.entrySet().contains( new AbstractReference2ReferenceMap.BasicEntry<Object,Object>( one, new Integer( 3 ) ) ) );
			assertFalse( m.entrySet().contains( new AbstractReference2ReferenceMap.BasicEntry<Object,Object>( new Integer( 1 ), three ) ) );
			assertTrue( m.entrySet().contains( new AbstractReference2ReferenceMap.BasicEntry<Object,Object>( two, two ) ) );
			assertFalse( m.entrySet().contains( new AbstractReference2ReferenceMap.BasicEntry<Object,Object>( one, two ) ) );
			assertFalse( m.entrySet().contains( new AbstractReference2ReferenceMap.BasicEntry<Object,Object>( two, one ) ) );
			assertTrue( m.entrySet().contains( new AbstractReference2ReferenceMap.BasicEntry<Object,Object>( three, three ) ) );
			assertFalse( m.entrySet().contains( new AbstractReference2ReferenceMap.BasicEntry<Object,Object>( new Integer( 3 ), two ) ) );

			assertEquals( three, m.remove( three ) );
			assertEquals( 2, m.size() );
			assertEquals( three, m.remove( one ) );
			assertEquals( 1, m.size() );
			assertFalse( m.containsKey( one ) );
			assertEquals( two, m.remove( two ) );
			assertEquals( 0, m.size() );
			assertFalse( m.containsKey( one ) );
		}
	}
	
	public void testClone() {
		Reference2ReferenceArrayMap<Integer, Integer> m = new Reference2ReferenceArrayMap<Integer, Integer>();
		assertEquals( m, m.clone() );
		m.put( new Integer( 0 ), new Integer( 1 ) );
		assertEquals( m, m.clone() );
		m.put( new Integer( 0 ), new Integer( 2 ) );
		assertEquals( m, m.clone() );
		Integer one;
		m.put( one = new Integer( 1 ), new Integer( 2 ) );
		assertEquals( m, m.clone() );
		m.remove( one );
		assertEquals( m, m.clone() );
	}

}
