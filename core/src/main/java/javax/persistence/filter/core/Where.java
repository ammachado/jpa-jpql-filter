package javax.persistence.filter.core;

import java.util.Collection;

import javax.persistence.TypedQuery;
import javax.persistence.filter.core.conditional.Between;
import javax.persistence.filter.core.conditional.In;
import javax.persistence.filter.core.conditional.IsNotNull;
import javax.persistence.filter.core.conditional.IsNull;
import javax.persistence.filter.core.conditional.NotIn;
import javax.persistence.filter.core.conditional.exact.Equal;
import javax.persistence.filter.core.conditional.exact.Exact;
import javax.persistence.filter.core.conditional.exact.Exact.Operation;
import javax.persistence.filter.core.conditional.exact.GreaterThan;
import javax.persistence.filter.core.conditional.exact.GreaterThanOrEqual;
import javax.persistence.filter.core.conditional.exact.LesserThan;
import javax.persistence.filter.core.conditional.exact.LesserThanOrEqual;
import javax.persistence.filter.core.conditional.exact.NotEqual;
import javax.persistence.filter.core.conditional.like.EndsWith;
import javax.persistence.filter.core.conditional.like.IEndsWith;
import javax.persistence.filter.core.conditional.like.IEqual;
import javax.persistence.filter.core.conditional.like.ILike;
import javax.persistence.filter.core.conditional.like.ILikeAny;
import javax.persistence.filter.core.conditional.like.INotLike;
import javax.persistence.filter.core.conditional.like.IStartsWith;
import javax.persistence.filter.core.conditional.like.Like;
import javax.persistence.filter.core.conditional.like.LikeAny;
import javax.persistence.filter.core.conditional.like.NotLike;
import javax.persistence.filter.core.conditional.like.StartsWith;

/**
 * @author Michel Risucci
 */
public abstract class Where extends VolatilePath {

	protected static final Join DEFAULT_JUNCTION = Join.INNER;

	protected Object[] values;

	/*
	 * Constructors
	 */

	/**
	 * @param fullRelativePath
	 * @param values
	 */
	protected Where(String fullRelativePath, Object[] values) {
		this(fullRelativePath, null, values);
	}

	/**
	 * @param fullRelativePath
	 * @param join
	 * @param values
	 */
	protected Where(String fullRelativePath, Join[] joins, Object[] values) {
		super(fullRelativePath);
		this.values = values;
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Exact equal(String path, Object value) {
		return new Equal(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Exact notEqual(String path, Object value) {
		return new NotEqual(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Exact lesserThan(String path, Object value) {
		return new LesserThan(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Exact lesserThanOrEqual(String path, Object value) {
		return new LesserThanOrEqual(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Exact greaterThan(String path, Object value) {
		return new GreaterThan(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Exact greaterThanOrEqual(String path, Object value) {
		return new GreaterThanOrEqual(path, value);
	}

	/**
	 * @param path
	 * @param initialValue
	 * @param finalValue
	 * @return
	 */
	public static Where between(String path, Object initialValue, Object finalValue) {
		return new Between(path, initialValue, finalValue);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Where like(String path, String value) {
		return new Like(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Where iLike(String path, String value) {
		return new ILike(path, value);
	}

	/**
	 * @param path
	 * @param values
	 * @return
	 */
	public static Where likeAny(String path, Collection<String> values) {
		return new LikeAny(path, values.toArray(new String[values.size()]));
	}

	/**
	 * @param path
	 * @param values
	 * @return
	 */
	public static Where likeAny(String path, String[] values) {
		return new LikeAny(path, values);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Where notLike(String path, String value) {
		return new NotLike(path, value);
	}

	/**
	 * @param path
	 * @param values
	 * @return
	 */
	public static Where iLikeAny(String path, Collection<String> values) {
		return new ILikeAny(path, values.toArray(new String[values.size()]));
	}

	/**
	 * @param path
	 * @param values
	 * @return
	 */
	public static Where iLikeAny(String path, String[] values) {
		return new ILikeAny(path, values);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Where iNotLike(String path, String value) {
		return new INotLike(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Where iEqual(String path, String value) {
		return new IEqual(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Where startsWith(String path, String value) {
		return new StartsWith(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Where iStartsWith(String path, String value) {
		return new IStartsWith(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Where endsWith(String path, String value) {
		return new EndsWith(path, value);
	}

	/**
	 * @param path
	 * @param value
	 * @return
	 */
	public static Where iEndsWith(String path, String value) {
		return new IEndsWith(path, value);
	}

	/**
	 * @param path
	 * @param values
	 * @return
	 */
	public static Where in(String path, Collection<?> values) {
		return new In(path, values.toArray());
	}

	/**
	 * @param path
	 * @param values
	 * @return
	 */
	public static Where in(String path, Object[] values) {
		return new In(path, values);
	}

	/**
	 * @param path
	 * @param values
	 * @return
	 */
	public static Where notIn(String path, Collection<?> values) {
		return new NotIn(path, values.toArray());
	}

	/**
	 * @param path
	 * @param values
	 * @return
	 */
	public static Where notIn(String path, Object[] values) {
		return new NotIn(path, values);
	}

	/**
	 * @param path
	 * @return
	 */
	public static Where isNull(String path) {
		return new IsNull(path);
	}

	/**
	 * @param path
	 * @return
	 */
	public static Where isNotNull(String path) {
		return new IsNotNull(path);
	}

	/**
	 * Creates a raw clause without post-processing. Remember to use {@code "x"} as root prefix.
	 * 
	 * @param rawJpql
	 * @param operation
	 * @param value
	 * @return
	 */
	public static Where passthroughRawClause(String rawJpql, Operation operation, Object value) {
		return new PassthroughRawClause(rawJpql, operation, value);
	}

	/*
	 * Implementations
	 */

	/**
	 * @param query
	 */
	public abstract <E> TypedQuery<E> compileClause(TypedQuery<E> query);

	/*
	 * Getters and Setters
	 */

	/**
	 * @return
	 */
	public Object[] getValues() {
		return values;
	}

	/*
	 * To String
	 */

	@Override
	public String toString() {
		return getJpqlClause();
	}

}