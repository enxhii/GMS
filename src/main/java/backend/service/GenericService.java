package backend.service;

import java.util.List;

public abstract interface GenericService<T> {
	public List<T> listAll();

	public void add(T entity);

	public void update(T entity);

	public void delete(T entity);

	public void deleteById(int entityId);

	public T getById(int id);

}
