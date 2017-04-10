package com.goutham.springboot.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.goutham.springboot.dao.FileDao;
import com.goutham.springboot.entity.File;

@Repository
public class FileDaoImp implements FileDao {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public void addFile(File file) {
		em.persist(file);
	}

	@Override
	public File getFileById(Long id) {
		return em.find(File.class, id);
	}

	@Override
	public List<File> getFile() {
		String hql = "SELECT f FROM File f";
		return em.createQuery(hql, File.class).getResultList();
	}

	@Override
	public List<File> getFileByUserId(String userId) {
		String hql = "SELECT f FROM File f WHERE f.userId =: userId";
		return em.createQuery(hql, File.class).
				setParameter("userId", userId).getResultList();
	}

	@Override
	public List<File> getByCondition(File file) {
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<File> criteriaQuery = criteriaBuilder.createQuery(File.class);
		Root<File> root = criteriaQuery.from(File.class);
		
		if (file.getName() != null) {
			conditions.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + file.getName() + "%"));
		}
		
		if (file.getComment() != null) {
			conditions.add(criteriaBuilder.like(root.get("comment").as(String.class), "%" + file.getComment() + "%"));
		}
		
		if (file.getAdmin() != null) {
			conditions.add(criteriaBuilder.equal(root.get("admin").as(String.class), file.getAdmin()));
		}
		
		Predicate[] predicates = new Predicate[conditions.size()];
		criteriaQuery.where(conditions.toArray(predicates));
		
		return em.createQuery(criteriaQuery).getResultList();
	}

	
}
