package backend.service;

import java.util.List;

public abstract interface GenericService<T> {
	public List<T> listAll();

	public boolean add(T entity);

	public boolean update(T entity);

	public boolean delete(T entity);

	public boolean deleteById(int entityId);

	public T getById(int id);

}
