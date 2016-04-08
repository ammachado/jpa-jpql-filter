package javax.persistence.filter.core;

/**
 * @author Michel Risucci
 */
public class Order extends VolatilePath {

	/**
	 * @author Michel Risucci
	 */
	public enum Direction {
		ASC {
			@Override
			public Direction invert() {
				return DESC;
			}
		},
		DESC {
			@Override
			public Direction invert() {
				return ASC;
			}
		};

		/**
		 * Creates an order clause, using a {@link String} relativePath.
		 * 
		 * @param relativePath
		 * @return
		 */
		public Order createOrder(String path) {
			return new Order(path, this);
		}

		/**
		 * Gets the inverted order, according to current instance.
		 * 
		 * @return
		 */
		public abstract Direction invert();
	}

	private Direction direction;

	/*
	 * Constructors
	 */

	/**
	 * 
	 */
	private Order(String fullRelativePath, Direction direction) {

		if (fullRelativePath.contains(".")) {
			int valueDotIndex = fullRelativePath.lastIndexOf('.');

			// Relative path without value field name;
			this.relativePath = fullRelativePath.substring(0, valueDotIndex);
			// Relative path parts according to separator REGEX;
			this.relativePathParts = relativePath.split(SEPARATOR_REGEX);
			// Value field name (only last word);
			this.valueFieldName = fullRelativePath.substring(valueDotIndex + 1);
		} else {
			this.valueFieldName = fullRelativePath;
		}

		this.direction = direction;
	}

	/**
	 * @param fullRelativePath
	 * @param direction
	 * @return
	 */
	public static Order by(String fullRelativePath, Direction direction) {
		return new Order(fullRelativePath, direction);
	}

	/**
	 * @param fullRelativePath
	 * @return
	 */
	public static Order byAscending(String fullRelativePath) {
		return by(fullRelativePath, Direction.ASC);
	}

	/**
	 * @param fullRelativePath
	 * @return
	 */
	public static Order byDescending(String fullRelativePath) {
		return by(fullRelativePath, Direction.DESC);
	}

	/*
	 * Implementations
	 */

	@Override
	protected String getJpqlClause() {
		return getRealPath() + " " + direction.name() + " ";
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

}