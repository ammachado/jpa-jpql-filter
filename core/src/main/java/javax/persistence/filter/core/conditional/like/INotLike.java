package javax.persistence.filter.core.conditional.like;

/**
 * @author Michel Risucci
 */
public class INotLike extends ILike {

	/**
	 * Protected Constructor: needed for inherited classes.
	 * 
	 * @param path
	 * @param percentPosition
	 * @param value
	 */
	protected INotLike(String path, PercentPosition percentPosition, String value) {
		super(path, percentPosition, value);
	}

	/**
	 * @param path
	 * @param value
	 */
	public INotLike(String path, String value) {
		super(path, PercentPosition.AROUND, value);
	}

	@Override
	protected String mountClausePart(int index) {
		return "UPPER(" + getRealPath() + ") NOT LIKE UPPER(:" + getQueryParamName(index) + ")";
	}

}