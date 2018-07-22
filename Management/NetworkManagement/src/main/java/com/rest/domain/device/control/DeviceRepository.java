package com.rest.domain.device.control;

import java.math.BigInteger;
import java.util.Arrays;
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

import org.apache.commons.collections4.map.HashedMap;
import com.rest.domain.device.entity.CardEntity;
import com.rest.domain.device.entity.CardType;
import com.rest.domain.device.entity.DeviceEntity;
import com.rest.infrastructure.LogInterceptor;

public class DeviceRepository {

	private static final String LIKE_WILDCARD = "%";
	private static final String TYPE_ID_COLUMN = "typeId";
	private static final String DEVICE_ENTITY_TABLE = "deviceentity";
	private static final String SELECT_TYPE_IDS_SUBQUERY = 
			"(SELECT {TYPE_ID_COLUMN} FROM {DEVICE_ENTITY_TABLE} WHERE type = {ENUM_TYPE_ORDINAL})";
	private static final String SELECT_MIN_AVAILABLE_TYPE_ID_QUERY =
			"SELECT MIN(T1.{TYPE_ID_COLUMN}) + 1 " +
			"FROM {SELECT_TYPE_IDS_SUBQUERY} AS T1 LEFT JOIN {SELECT_TYPE_IDS_SUBQUERY} AS T2 " +
			"ON T2.{TYPE_ID_COLUMN} = T1.{TYPE_ID_COLUMN} + 1 " +
			"WHERE T2.{TYPE_ID_COLUMN} IS NULL";
	
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
		return criteriaBuilder.like(deviceRoot.get(likeQueryParam.getKey()), createLikePatternForAttribute(likeQueryParam.getValue().get(0)));
	}

	private String createLikePatternForAttribute(String attributeValue) {
		return LIKE_WILDCARD + attributeValue + LIKE_WILDCARD;
	}
	
	public int queryMinAvailableTypeId(DeviceEntity deviceEntity) {
		Integer enum_type_ordinal = deviceEntity.getType().ordinal();
		String selectMinAvailableTypeIdQuery = SELECT_MIN_AVAILABLE_TYPE_ID_QUERY
				.replace("{SELECT_TYPE_IDS_SUBQUERY}", SELECT_TYPE_IDS_SUBQUERY)
				.replace("{TYPE_ID_COLUMN}", TYPE_ID_COLUMN)
				.replace("{DEVICE_ENTITY_TABLE}", DEVICE_ENTITY_TABLE)
				.replace("{ENUM_TYPE_ORDINAL}", enum_type_ordinal.toString());
		
		BigInteger typeID = (BigInteger) entityManager.createNativeQuery(selectMinAvailableTypeIdQuery).getSingleResult();
		return typeID.intValue();
	}
}
