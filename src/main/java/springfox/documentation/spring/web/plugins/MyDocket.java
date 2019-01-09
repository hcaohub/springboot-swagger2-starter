
package springfox.documentation.spring.web.plugins;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.ApiSelector;

public class MyDocket extends Docket {

	public MyDocket( DocumentationType documentationType, ApiInfo apiInfo, String groupName, ApiSelector apiSelector ) {
		super( documentationType );
		this.apiInfo( apiInfo );
		this.groupName( groupName );
		this.selector( apiSelector );
	}

}
