package com.hc.springboot.swagger2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import java.util.List;
import java.util.Map;

@JsonInclude( JsonInclude.Include.NON_EMPTY )
public class Swagger2Properties {
	@JsonProperty( "api-info" )
	private Map<String, CustomApiInfo> apiInfo;
	@JsonProperty( "docket-config" )
	private Map<String, DocketConfig> docketConfig;

	public ApiInfo getApiInfoByName( String apiInfoName ) {
		if ( apiInfoName == null || apiInfoName.isEmpty() || apiInfo == null || !apiInfo.containsKey( apiInfoName ) ) {
			return null;
		}
		CustomApiInfo customApiInfo = apiInfo.get( apiInfoName );
		return new ApiInfoBuilder().title( customApiInfo.getTitle() ).description( customApiInfo.getDesc() )
				.termsOfServiceUrl( customApiInfo.getTermsOfServiceUrl() )
				.contact( new Contact( customApiInfo.getContactName(), customApiInfo.getContactUrl(), customApiInfo.getContactEmail() ) )
				.version( customApiInfo.getVersion() ).build();
	}

	public Map<String, DocketConfig> getDocketConfig() {
		return docketConfig;
	}

	public void setDocketConfig( Map<String, DocketConfig> docketConfig ) {
		this.docketConfig = docketConfig;
	}

	public Map<String, CustomApiInfo> getApiInfo() {
		return apiInfo;
	}

	public void setApiInfo( Map<String, CustomApiInfo> apiInfo ) {
		this.apiInfo = apiInfo;
	}

	@JsonInclude( JsonInclude.Include.NON_EMPTY )
	public static class DocketConfig {
		@JsonProperty( "api-info-name" )
		private String apiInfoName;
		@JsonProperty( "base-package" )
		private List<String> basePackage;
		@JsonProperty( "paths-include" )
		private List<String> pathsInclude;
		@JsonProperty( "paths-exclude" )
		private List<String> pathsExclude;

		public String getApiInfoName() {
			return apiInfoName;
		}

		public DocketConfig setApiInfoName( String apiInfoName ) {
			this.apiInfoName = apiInfoName;
			return this;
		}

		public List<String> getBasePackage() {
			return basePackage;
		}

		public DocketConfig setBasePackage( List<String> basePackage ) {
			this.basePackage = basePackage;
			return this;
		}

		public List<String> getPathsInclude() {
			return pathsInclude;
		}

		public DocketConfig setPathsInclude( List<String> pathsInclude ) {
			this.pathsInclude = pathsInclude;
			return this;
		}

		public List<String> getPathsExclude() {
			return pathsExclude;
		}

		public DocketConfig setPathsExclude( List<String> pathsExclude ) {
			this.pathsExclude = pathsExclude;
			return this;
		}
	}

	@JsonInclude( JsonInclude.Include.NON_EMPTY )
	public static class CustomApiInfo {
		private String title;
		private String desc;
		@JsonProperty( "terms-of-service-url" )
		private String termsOfServiceUrl;
		private String version;
		@JsonProperty( "contact-name" )
		private String contactName;
		@JsonProperty( "contact-url" )
		private String contactUrl;
		@JsonProperty( "contact-email" )
		private String contactEmail;

		public String getTitle() {
			return title;
		}

		public CustomApiInfo setTitle( String title ) {
			this.title = title;
			return this;
		}

		public String getDesc() {
			return desc;
		}

		public CustomApiInfo setDesc( String desc ) {
			this.desc = desc;
			return this;
		}

		public String getTermsOfServiceUrl() {
			return termsOfServiceUrl;
		}

		public CustomApiInfo setTermsOfServiceUrl( String termsOfServiceUrl ) {
			this.termsOfServiceUrl = termsOfServiceUrl;
			return this;
		}

		public String getVersion() {
			return version;
		}

		public CustomApiInfo setVersion( String version ) {
			this.version = version;
			return this;
		}

		public String getContactName() {
			return contactName;
		}

		public CustomApiInfo setContactName( String contactName ) {
			this.contactName = contactName;
			return this;
		}

		public String getContactUrl() {
			return contactUrl;
		}

		public CustomApiInfo setContactUrl( String contactUrl ) {
			this.contactUrl = contactUrl;
			return this;
		}

		public String getContactEmail() {
			return contactEmail;
		}

		public CustomApiInfo setContactEmail( String contactEmail ) {
			this.contactEmail = contactEmail;
			return this;
		}
	}
}