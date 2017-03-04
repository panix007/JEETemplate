package com.ntahr.common.dataaccess.genericdao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ntahr.common.dataaccess.genericdao.DaoBase;
import com.ntahr.common.dataaccess.objects.Customer;

public class DaoBaseTest {

	EntityManager mockEntityManager = null;
	EntityManager entityManager = null;
	DaoBase<Customer> daoBase = null;

	@Before
	public void setUp() throws Exception {
		mockEntityManager = Mockito.mock(EntityManager.class);
		daoBase = new DaoBase<Customer>(Customer.class, mockEntityManager);
		Assert.assertTrue(true);

	}

	@Test
	public void testSetEntityManager() {
		daoBase.setEntityManager(mockEntityManager);
		;
		Assert.assertNotNull(daoBase.getEntityManager());
	}

	@Test
	public void testGetEntityManager() {
		Assert.assertNotNull(daoBase.getEntityManager());
	}

	@Test
	public void testLoadAll() {
		TypedQuery<Customer> mockQuery = getTypedQuery();
		TypedQuery<Object> mockTypedQuery = null;
		mockTypedQuery = Mockito.mock(mockQuery.getClass());
		List<Object> testList = new ArrayList<Object>();
		testList.add(new Customer("John", "jdoe@gmail.com", "9998887770", "HP11 2UE"));
		org.mockito.Mockito
				.when(mockEntityManager.createQuery(org.mockito.Mockito.anyString(), org.mockito.Mockito.any()))
				.thenReturn(mockTypedQuery);
		org.mockito.Mockito.when(mockTypedQuery.getResultList()).thenReturn(testList);
		List<Customer> customers = daoBase.loadAll();
		Assert.assertEquals("John", customers.get(0).getName());
		Assert.assertEquals("jdoe@gmail.com", customers.get(0).getEmail());
		Assert.assertEquals("9998887770", customers.get(0).getPhone());
		Assert.assertEquals("HP11 2UE", customers.get(0).getAddress());

	}

	private TypedQuery<Customer> getTypedQuery() {
		return new TypedQuery<Customer>() {

			@Override
			public int executeUpdate() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getMaxResults() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int getFirstResult() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Map<String, Object> getHints() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Set<Parameter<?>> getParameters() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Parameter<?> getParameter(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> Parameter<T> getParameter(String name, Class<T> type) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Parameter<?> getParameter(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> Parameter<T> getParameter(int position, Class<T> type) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isBound(Parameter<?> param) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public <T> T getParameterValue(Parameter<T> param) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getParameterValue(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getParameterValue(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public FlushModeType getFlushMode() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public LockModeType getLockMode() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> T unwrap(Class<T> cls) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<Customer> getResultList() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Customer getSingleResult() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setMaxResults(int maxResult) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setFirstResult(int startPosition) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setHint(String hintName, Object value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> TypedQuery<Customer> setParameter(Parameter<T> param, T value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setParameter(Parameter<Calendar> param, Calendar value,
					TemporalType temporalType) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setParameter(Parameter<Date> param, Date value, TemporalType temporalType) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setParameter(String name, Object value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setParameter(String name, Calendar value, TemporalType temporalType) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setParameter(String name, Date value, TemporalType temporalType) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setParameter(int position, Object value) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setParameter(int position, Calendar value, TemporalType temporalType) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setParameter(int position, Date value, TemporalType temporalType) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setFlushMode(FlushModeType flushMode) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TypedQuery<Customer> setLockMode(LockModeType lockMode) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Test
	public void testSave() {
		Customer customer = new Customer();
		daoBase.save(customer);
		org.mockito.Mockito.verify(mockEntityManager, org.mockito.Mockito.times(1)).persist(customer);
		org.mockito.Mockito.verify(mockEntityManager, org.mockito.Mockito.times(1)).flush();
	}

	@Test
	public void testDelete() {
		Customer customer = new Customer();
		daoBase.delete(customer);
		org.mockito.Mockito.verify(mockEntityManager, org.mockito.Mockito.times(1)).remove(customer);
	}

	@Test
	public void testUpdate() {
		Customer customer = new Customer();
		daoBase.update(customer);
		org.mockito.Mockito.verify(mockEntityManager, org.mockito.Mockito.times(1)).merge(customer);
	}

}
