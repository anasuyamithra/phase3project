package com.fr.p3p.utils;

import org.springframework.stereotype.Component;

import com.fr.p3p.model.response.MSResponse;

@Component
public class ResponseHelper {

	public static MSResponse createResponse(Object data, String successMessage, String errorMessage) {
		MSResponse result = new MSResponse();
		if (data != null) {
			result.setSuccess(true);
			result.setMessage(successMessage);
			result.setData(data);
		} else {
			throw new MSException(ErrorCode.INTERNAL_SERVER_ERROR, errorMessage);
		}
		return result;
	}
}
