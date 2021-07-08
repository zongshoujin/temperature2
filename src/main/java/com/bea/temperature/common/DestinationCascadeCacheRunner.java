package com.bea.temperature.common;

import com.bea.temperature.cache.DestinationCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by zsj on 2021/7/8.
 */

@Component
public class DestinationCascadeCacheRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        DestinationCache.getInstance();
    }

}
