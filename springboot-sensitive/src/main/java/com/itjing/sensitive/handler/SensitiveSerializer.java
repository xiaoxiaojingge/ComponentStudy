package com.itjing.sensitive.handler;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.itjing.sensitive.enums.SensitiveEnum;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @Description:
 * @Author: lijing
 * @CreateTime: 2022-11-03 11:06
 */
public class SensitiveSerializer extends StdScalarSerializer<Object> {

	private SensitiveOperation operation;

	private String maskChar;

	public SensitiveSerializer() {
		super(String.class, false);
		this.operation = null;
		this.maskChar = null;
	}

	public SensitiveSerializer(SensitiveOperation operation, String maskChar) {
		super(String.class, false);
		this.operation = operation;
		this.maskChar = maskChar;
	}

	public boolean isEmpty(SerializerProvider prov, Object value) {
		String str = (String) value;
		return str.isEmpty();
	}

	public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (Objects.isNull(operation)) {
			String content = SensitiveEnum.ALL_MASK.operation().mask((String) value, maskChar);
			gen.writeString(content);
		}
		else {
			String content = operation.mask((String) value, maskChar);
			gen.writeString(content);
		}
	}

	public final void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider,
			TypeSerializer typeSer) throws IOException {
		this.serialize(value, gen, provider);
	}

	public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
		return this.createSchemaNode("string", true);
	}

	public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint)
			throws JsonMappingException {
		this.visitStringFormat(visitor, typeHint);
	}

}
