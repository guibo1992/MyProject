package com.gb.chrom.serialize;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * <p>
 * javabean 对应属性的方法添加注解：
 * </p>
 * <code>@JsonSerialize(using = JsonDateSerializer.class)</code><br>
 * <code>public Date getDate() { ... }</code>
 * <p>
 * </p>
 * 
 * @author Summer
 */
public class JsonDateTimeSerializer extends JsonSerializer<Date> {

	static final Logger logger = LoggerFactory.getLogger(JsonDateTimeSerializer.class);

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		Optional.ofNullable(value).ifPresent(date -> {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				gen.writeString(format.format(value));
			} catch (IOException e) {
				logger.warn("Write date format string error.", e);
			}
		});
	}

}
