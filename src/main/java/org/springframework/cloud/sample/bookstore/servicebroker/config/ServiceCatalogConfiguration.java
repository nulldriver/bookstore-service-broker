/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sample.bookstore.servicebroker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.servicebroker.model.catalog.Catalog;
import org.springframework.cloud.servicebroker.model.catalog.Plan;
import org.springframework.cloud.servicebroker.model.catalog.ServiceDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceCatalogConfiguration {

	@Value("${BOOKSTORE_SERVICE_BROKER_ASYNC:false}")
	private boolean async;

	@Bean
	public Catalog catalog() {
		Plan standard = Plan.builder()
				.id(modifyId("b973fb78-82f3-49ef-9b8b-c1876974a6cd"))
				.name("standard")
				.description("A simple book store plan")
				.free(true)
				.build();

		Plan pro = Plan.builder()
				.id(modifyId("2c39bc18-1fae-4a24-a956-99e676e02807"))
				.name("pro")
				.description("A pro book store plan")
				.free(true)
				.build();

		ServiceDefinition serviceDefinition = ServiceDefinition.builder()
				.id(modifyId("bdb1be2e-360b-495c-8115-d7697f9c6a9e"))
				.name(modifyId("bookstore"))
				.description("A book store service")
				.bindable(true)
				.tags("book-store", "books", "sample")
				.plans(standard, pro)
				.metadata("shareable", true)
				.metadata("displayName", "bookstore")
				.metadata("longDescription", "A book store service")
				.metadata("providerDisplayName", "Acme Books")
				.planUpdateable(true)
				.build();

		return Catalog.builder()
				.serviceDefinitions(serviceDefinition)
				.build();
	}

	public String modifyId(String prefix) {
		if (async) {
			return prefix + "-async";
		} else {
			return prefix;
		}
	}
}
