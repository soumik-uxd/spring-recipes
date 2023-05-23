package io.github.soumikuxd.springbatchexamples.services;

import io.github.soumikuxd.springbatchexamples.utils.Constants;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Service;

@Service
public class JobSkipPolicy implements SkipPolicy {
    private static final Logger logger = LoggerFactory.getLogger(JobSkipPolicy.class);
    @Override
    public boolean shouldSkip(@Nonnull Throwable t, long skipCount) throws SkipLimitExceededException {
        if (skipCount > Constants.MAX_ALLOWED_BAD_RECORDS) {
            logger.error("Job failed! Skip count: {} exceeded", skipCount);
            return false;
        } else {
            logger.info("Error! Skip count: {}", skipCount);
        }
        return true;
    }
}
