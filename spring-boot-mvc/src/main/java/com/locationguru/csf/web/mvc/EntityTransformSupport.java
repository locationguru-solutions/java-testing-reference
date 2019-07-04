package com.locationguru.csf.web.mvc;

import java.util.List;

import com.locationguru.csf.util.FunctionUtils;

public interface EntityTransformSupport<Type, ResponseType>
{
	public ResponseType transform(final Type type);

	public default List<ResponseType> transform(final List<Type> typeList)
	{
		return FunctionUtils.reduce(typeList, this::transform);
	}

}
