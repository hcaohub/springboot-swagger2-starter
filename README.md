# springboot-swagger2-starter
* maven依赖
```
<dependency>
    <groupId>com.github.hc621311</groupId>
    <artifactId>springboot-swagger2-starter</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```
```
springfox-swagger2:
  config: >
    {
      "api-info":{
        "default":{"title":"title","desc":"de","terms-of-service-url":"service","version":"V1.0","contact-name":"name","contact-url":"url","contact-email":"email"},
        "default2":{"title":"title","desc":"de","terms-of-service-url":"service","version":"V1.0","contact-name":"name","contact-url":"url","contact-email":"email"}
      },
      "docket-config":{
        "grou-name1":{
          "api-info-name":"xx","base-package":["11","222"],"paths-include":["11","222"],"paths-exclude":["11","222"]
        }
      }
    }
```