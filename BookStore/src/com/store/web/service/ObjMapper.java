package com.store.web.service;

import java.io.IOException;
import java.io.OutputStream;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * ����ͨ����ת����json����
 * @author ����
 */
public class ObjMapper extends ObjectMapper{
	private static final long serialVersionUID = 1L;
	@Override
	public void writeValue(OutputStream out, Object value)
			throws IOException, JsonGenerationException, JsonMappingException {
		super.writeValue(out, value);
	}
}
