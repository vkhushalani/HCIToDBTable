package HCIToTable.rest.services.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import HCIToTable.rest.services.model.RBPGroups;

@Transactional
@Component
public class RBPGroupsServiceImp implements RBPGroupsService {

	@PersistenceContext
	 EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RBPGroups> findAll() {
		Query query = em.createNamedQuery("RBPGroups.findAll");
		 List<RBPGroups> groups = query.getResultList();

	        return groups;
	}

	@Override
	@Transactional
	public RBPGroups create(RBPGroups group) {
		em.persist(group);
		 return group;
	}

	@Override
	@Transactional
	public RBPGroups update(RBPGroups group) {
		em.merge(group);
		 return group;
	}

	@Override
	public RBPGroups findById(Long id) {
		RBPGroups group = em.find(RBPGroups.class, id);
        return group;
	}


	@Override
	@Transactional
	public void deleteById(Long id) {
		RBPGroups group = em.find(RBPGroups.class, id);
		em.remove(group);

	}

	@Override
	@Transactional
	public void deleteByObject(RBPGroups group) {
		em.remove(group);

	}

	@Override
	public int updateAll() {
		Query query = em.createNamedQuery("RBPGroups.updateAll")
				.setParameter("today", true)
				.setParameter("next", false);
		 return query.executeUpdate();
	}

	@Override
	public int deleteFlagged() {
		Query query = em.createNamedQuery("RBPGroups.deleteFlagged")
				.setParameter("today", true)
				.setParameter("next", false);
		 return query.executeUpdate();
	}
	
	@Override
	public int deleteTodayFlagged() {
		Query query = em.createNamedQuery("RBPGroups.deleteTodayFlagged")
						.setParameter("today", false);
		 return query.executeUpdate();
	}
	
	@Override
	public RBPGroups findByKey(String uId, String role, Integer grp) {
		 try{
		Query query = em.createNamedQuery("RBPGroups.findByKey")
				.setParameter("grp",grp)
				.setParameter("uId", uId)
				.setParameter("role", role);
		
		       
		        return (RBPGroups) query.getSingleResult();
		    } catch(NoResultException e) {
		        return null;
		    }
		

	        
	}

}
