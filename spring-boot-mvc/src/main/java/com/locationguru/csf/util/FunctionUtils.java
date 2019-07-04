package com.locationguru.csf.util;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionUtils
{
	public static <T> T filterFirst(final Collection<T> list, final Predicate<T> predicate)
	{
		if (list.isEmpty()) { return null; }

		for (final T t : list)
		{
			if (predicate.test(t)) { return t; }
		}

		return null;
	}

	public static <T> List<T> filter(final Collection<T> list, final Predicate<T> predicate)
	{
		if (list.isEmpty()) { return Collections.emptyList(); }

		final List<T> values = new ArrayList<>(list.size());

		for (final T t : list)
		{
			if (predicate.test(t)) { values.add(t); }
		}

		return values;
	}

	public static <I, T> List<T> reduce(final Collection<I> list, final Function<I, T> filter)
	{
		if (list.isEmpty())
		{
			return Collections.emptyList();
		}

		final List<T> values = new ArrayList<>(list.size());

		for (final I t : list)
		{
			values.add(filter.apply(t));
		}

		return values;
	}

	public static <I, T> List<T> reduce(final List<I> list, final Function<I, T> filter)
	{
		if (list.isEmpty())
		{
			return Collections.emptyList();
		}

		final List<T> values = new ArrayList<>(list.size());

		for (final I t : list)
		{
			values.add(filter.apply(t));
		}

		return values;
	}

	public static <K, R> Map<K, R> map(final List<R> list, final Function<R, K> keyFunction)
	{
		final Map<K, R> map = new HashMap<>(list.size());

		for (final R t : list)
		{
			map.put(keyFunction.apply(t), t);
		}

		return map;
	}

	public static <Input, K, V> Map<K, V> map(final List<Input> list, final Function<Input, K> keyFunction, final Function<Input, V> valueFunction)
	{
		final Map<K, V> map = new HashMap<>(list.size());

		for (final Input t : list)
		{
			map.put(keyFunction.apply(t), valueFunction.apply(t));
		}

		return map;
	}

	public static <K, V> Map<K, List<V>> group(final List<V> list, final Function<V, K> keyFunction)
	{
		final Map<K, List<V>> map = new HashMap<>(list.size());

		for (final V v : list)
		{
			map.computeIfAbsent(keyFunction.apply(v), k -> new ArrayList<>()).add(v);
		}

		return map;
	}

	public static <Input, K, V> Map<K, List<V>> group(final List<Input> list, final Function<Input, K> keyFunction, final Function<Input, V> valueFunction)
	{
		final Map<K, List<V>> map = new HashMap<>(list.size());

		for (final Input i : list)
		{
			map.computeIfAbsent(keyFunction.apply(i), k -> new ArrayList<>()).add(valueFunction.apply(i));
		}

		return map;
	}

	public static <Input, K> Map<K, Integer> groupCount(final List<Input> list, final Function<Input, K> keyFunction)
	{
		final Map<K, Integer> map = new HashMap<>();

		for (final Input i : list)
		{
			final K key = keyFunction.apply(i);

			map.put(key, map.getOrDefault(key, 0) + 1);
		}

		return map;

	}
}
