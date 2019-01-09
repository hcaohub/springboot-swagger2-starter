package com.hc.springboot.swagger2;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import springfox.documentation.RequestHandler;
import springfox.documentation.spi.service.contexts.ApiSelector;

public class ApiSelectorBuilder {
	private Predicate<RequestHandler> requestHandlerSelector;
	private Predicate<String> pathSelector;

	public ApiSelectorBuilder() {
		this.requestHandlerSelector = ApiSelector.DEFAULT.getRequestHandlerSelector();
		this.pathSelector = ApiSelector.DEFAULT.getPathSelector();
	}

	public ApiSelectorBuilder apis( Predicate<RequestHandler> selector ) {
		this.requestHandlerSelector = Predicates.and( this.requestHandlerSelector, selector );
		return this;
	}

	public ApiSelectorBuilder paths( Predicate<String> selector ) {
		this.pathSelector = Predicates.and( this.pathSelector, selector );
		return this;
	}

	public ApiSelector build() {
		return new ApiSelector( this.combine( this.requestHandlerSelector, this.pathSelector ), this.pathSelector );
	}

	private Predicate<RequestHandler> combine( Predicate<RequestHandler> requestHandlerSelector, Predicate<String> pathSelector ) {
		return Predicates.and( requestHandlerSelector, this.transform( pathSelector ) );
	}

	private Predicate<RequestHandler> transform( final Predicate<String> pathSelector ) {
		return new Predicate<RequestHandler>() {
			public boolean apply( RequestHandler input ) {
				return Iterables.any( input.getPatternsCondition().getPatterns(), pathSelector );
			}
		};
	}
}
