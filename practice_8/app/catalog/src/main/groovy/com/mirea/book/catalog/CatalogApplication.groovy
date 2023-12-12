package com.mirea.book.catalog

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter

@SpringBootApplication
class CatalogApplication {

	static void main(String[] args) {
		SpringApplication.run(CatalogApplication, args)
	}

	@Bean
	public OtlpGrpcSpanExporter otlpHttpSpanExporter(@Value('${tracing.url}') String url) {
		return OtlpGrpcSpanExporter.builder().setEndpoint(url).build()
	}
}
