package com.mirea.book.notifications

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter

@SpringBootApplication
class NotificationsApplication {

	static void main(String[] args) {
		SpringApplication.run(NotificationsApplication, args)
	}

	@Bean
	public OtlpGrpcSpanExporter otlpHttpSpanExporter(@Value('${tracing.url}') String url) {
		return OtlpGrpcSpanExporter.builder().setEndpoint(url).build()
	}
}
