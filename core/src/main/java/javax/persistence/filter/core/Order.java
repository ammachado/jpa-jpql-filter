package javax.persistence.filter.core;

/**
 * @author Michel Risucci
 */
public class Order extends VolatilePath {

	/**
	 * @author Michel Risucci
	 */
	public enum Direction {
		ASC, DESC;

		/**
		 * Creates an order clause, using a {@link String} path.
		 * 
		 * @param path
		 * @return
		 */
		public Order createOrder(String path) {
			return new Order(path, this);
		}
	}

	private Direction direction;

	/*
	 * Constructors
	 */

	/**
	 * 
	 */
	private Order(String path, Direction direction) {
		this.path = path;
		this.direction = direction;
	}

	/**
	 * @param path
	 * @return
	 */
	public static Order ascending(String path) {
		return new Order(path, Direction.ASC);
	}

	/**
	 * @param path
	 * @return
	 */
	public static Order descending(String path) {
		return new Order(path, Direction.DESC);
	}

	/*
	 * Getters and Setters
	 */

	/**
	 * @return
	 */
	public Direction getDirection() {
		return direction;
	}

	/*
	 * Implementations
	 */

	@Override
	protected String getClause() {
		return getRealPath() + " " + direction.name() + " ";
	}

}