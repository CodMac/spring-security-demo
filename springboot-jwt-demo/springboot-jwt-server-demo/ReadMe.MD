两种获取token方式：
- 通过`login`页面登录后， 通过`/token POST`接口获得token
- 通过 `curl -XPOST user:password@localhost:8080/token`获取token

通过token访问资源  
`curl -H "Authorization: Bearer $TOKEN" localhost:8080/hello && echo`