package me.pedrocaires.fff.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ObjectMapperConfigTest {

	@InjectMocks
	ObjectMapperConfig objectMapperConfig;

	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper = objectMapperConfig.objectMapper();
	}

	@Test
	void shouldProvideObjectMapperThatDoesntFailOnUnknownPropertiesRead() throws JsonProcessingException {
		var nameProperty = "test";
		var stringWithUnknownProperties = "{\"name\": \"" + nameProperty
				+ "\", \"unknownProperty\": \"I'm unknown\", \"unknownProperty2\": \"I'm unknown too\"}";

		var rodeClass = objectMapper.readValue(stringWithUnknownProperties, ClassWithProperties.class);

		assertEquals(nameProperty, rodeClass.getName());
	}

	static class ClassWithProperties {

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
