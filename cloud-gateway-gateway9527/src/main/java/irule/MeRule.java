package irule;

import com.li.springcloud.rule.MyGateWayRule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStreamReader;

/**
 * @ClassName IRule
 * @Description: TODO
 * @Author: li
 * @Date: 2020/5/18 19:11
 * @Version v1.0
 **/
@Configuration
public class MeRule {

    @Bean
    public IRule iRule() {
        return new MyGateWayRule();
    }

}
