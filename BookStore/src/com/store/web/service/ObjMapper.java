package com.store.web.service;

import java.io.IOException;
import java.io.OutputStream;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 将普通数据转换成json对象
 * @author 老腰
 */
public class ObjMapper extends ObjectMapper{
	private static final long serialVersionUID = 1L;
	@Override
	public void writeValue(OutputStream out, Object value)
			throws IOException, JsonGenerationException, JsonMappingException {
		super.writeValue(out, value);
	}
}
