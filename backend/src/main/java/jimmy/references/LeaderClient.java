package jimmy.references;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import java.net.URI;

import jimmy.model.NewRangeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(name = "dummy-name", url = "https://placeholder.com", configuration = LeaderClient.Configuration.class)
public interface LeaderClient {
    @GetMapping("/next-range")
    NewRangeResponse nextRange(URI baseUrl);

    class Configuration {
        @Bean
        Retryer retryer() {
            return new Retryer.Default(50, 500, 3);
        }

        @Bean
        ErrorDecoder errorDecoder() {
            return new ErrorDecoder.Default();
        }
    }
}