package com.grupo25.hospital.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.helpers.mail.objects.Personalization;

public class DynamicTemplatePersonalization extends Personalization{

	@JsonProperty(value = "dynamic_template_data")
	private Map<String, String> dynamic_template_data;
		
	@JsonProperty("dynamic_template_data")
	public Map<String, String> getDynamic_template_data() {
		if (dynamic_template_data == null) {
			return Collections.<String, String>emptyMap();
		}
		return dynamic_template_data;
	}		

	public void addDynamicTemplateData(String key, String value) {
		if (dynamic_template_data == null) {
			dynamic_template_data = new HashMap<String, String>();
			dynamic_template_data.put(key, value);
		} else {
			dynamic_template_data.put(key, value);
		}
	}
}
