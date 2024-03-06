package com.hubs.ing.assignment.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hubs.ing.assignment.rest.api.converter.FetchProductConverter;
import com.hubs.ing.assignment.rest.api.converter.ProductConverter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ConverterConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new ProductConverter());
		registry.addConverter(new FetchProductConverter());
	}
}
