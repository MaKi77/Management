package com.rest.domain.device.control;

import java.math.BigInteger;
import java.util.List;
import java.util.Map.Entry;

import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import com.rest.domain.device.entity.DeviceEntity;
import com.rest.infrastructure.LogInterceptor;

public class DeviceRepository {

	private static final String LIKE_WILDCARD = "%";
	
	@PersistenceContext(unitName = "NetworkManagement")
	private EntityManager entityManager;

	public DeviceEntity getDeviceById(int id) {
		return entityManager.find(DeviceEntity.class, id);
	}

	@Interceptors(LogInterceptor.class)
	public int save(DeviceEntity deviceEntity) {
		entityManager.persist(deviceEntity);
		return deviceEntity.getId();
	}

	public void deleteDevice(int id) {
		DeviceEntity device = getDeviceById(id);
		entityManager.remove(device);
	}

	public List<DeviceEntity> queryDeviceByQueryParams(MultivaluedMap<String, String> queryParameters) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeviceEntity> criteria = criteriaBuilder.createQuery(DeviceEntity.class);
		Root<DeviceEntity> deviceRoot = criteria.from(DeviceEntity.class);
		
		criteria.select(deviceRoot);
		for (Entry<String, List<String>> values : queryParameters.entrySet()) {
			Predicate equalPredicate = createLikePredicate(criteriaBuilder, deviceRoot, values);
			criteria.where(equalPredicate);
		}

		return entityManager.createQuery(criteria).getResultList();
	}

	private Predicate createLikePredicate(CriteriaBuilder criteriaBuilder, Root<DeviceEntity> deviceRoot,
			Entry<String, List<String>> likeQueryParam) {
		return criteriaBuilder.like(deviceRoot.get(likeQueryParam.getKey()), createLikePattern(likeQueryParam.getValue().get(0)));
	}

	private String createLikePattern(String attributeValue) {
		return LIKE_WILDCARD + attributeValue + LIKE_WILDCARD;
	}
	
	public void assignIdentifier(DeviceEntity deviceEntity) {
		String typeIds = "(SELECT typeId FROM deviceentity WHERE type = " + deviceEntity.getType().ordinal() + ")";
		BigInteger typeID = (BigInteger) entityManager.createNativeQuery(
				"SELECT MIN(T1.typeId) + 1 FROM " + 
				typeIds + " AS T1 LEFT JOIN " + typeIds + 
				"AS T2 ON T2.typeId = T1.typeId + 1 WHERE T2.typeID IS NULL").getSingleResult();
		deviceEntity.setTypeId(typeID.intValue());
		deviceEntity.setIdentifier(deviceEntity.getType().toString() + "_" + typeID.intValue());
	}

}
