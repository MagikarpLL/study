package cn.magikarpll;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Hello world!
 *
 */
@EnableRedisHttpSession
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
