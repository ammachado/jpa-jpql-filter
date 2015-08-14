package javax.persistence.filter;

/**
 * An interface that defines a filterable component.
 * 
 * @author Michel Risucci
 */
public interface Filterable {

	/**
	 * Filters any defined entity with fetch range.
	 * 
	 * @param filter
	 * @param offset
	 * @param limit
	 * @return
	 */
	<T> PageFilter<T> filter(Filter<T> filter, int offset, int limit);

	/**
	 * Filters any defined entity without fetch range.
	 * 
	 * @param filter
	 * @param offset
	 * @param limit
	 * @return
	 */
	<T> PageFilter<T> filter(Filter<T> filter);

}