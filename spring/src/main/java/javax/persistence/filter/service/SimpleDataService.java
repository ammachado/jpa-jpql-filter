package javax.persistence.filter.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.utils.ReflectionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class SimpleDataService<E, ID extends Serializable, R extends JpaRepository<E, ID>> implements DataService<E, ID> {

	private Class<E> type;

	@Autowired
	protected R repository;

	@Override
	public List<E> findAll() {
		return repository.findAll();
	}

	@Override
	public List<E> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public List<E> findAll(Iterable<ID> ids) {
		return repository.findAll(ids);
	}

	@Override
	@Transactional
	public List<E> save(Iterable<E> entities) {
		return repository.save(entities);
	}

	@Override
	@Transactional
	public void deleteInBatch(Iterable<E> entities) {
		repository.deleteInBatch(entities);
	}

	@Override
	@Transactional
	public void deleteAllInBatch() {
		repository.deleteAllInBatch();
	}

	@Override
	public E getOne(ID id) {
		return repository.getOne(id);
	}

	@Override
	public List<E> findAll(Example<E> example) {
		return repository.findAll(example);
	}

	@Override
	public List<E> findAll(Example<E> example, Sort sort) {
		return repository.findAll(example, sort);
	}

	@Override
	public Page<E> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	@Transactional
	public E save(E entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public E saveAndFlush(E entity) {
		entity = save(entity);
		repository.flush();
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public E saveAndCommit(E entity) {
		return saveAndFlush(entity);
	}

	@Override
	public E findOne(ID id) {
		return repository.findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return repository.exists(id);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	@Transactional
	public void delete(ID id) {
		repository.delete(id);
	}

	@Override
	@Transactional
	public void deleteAndFlush(ID id) {
		delete(id);
		repository.flush();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteAndCommit(ID id) {
		deleteAndFlush(id);
	}

	@Override
	@Transactional
	public void delete(Iterable<? extends E> entities) {
		repository.delete(entities);
	}

	@Override
	@Transactional
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	public E findOne(Example<E> example) {
		return repository.findOne(example);
	}

	@Override
	public Page<E> findAll(Example<E> example, Pageable pageable) {
		return repository.findAll(example, pageable);
	}

	@Override
	public long count(Example<E> example) {
		return repository.count(example);
	}

	@Override
	public boolean exists(Example<E> example) {
		return repository.exists(example);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@SuppressWarnings("unchecked")
	protected Class<E> getEntityType() {
		if (type == null) {
			type = (Class<E>) ReflectionUtils.getParameterType(this, 0);
		}
		return type;
	}

}
