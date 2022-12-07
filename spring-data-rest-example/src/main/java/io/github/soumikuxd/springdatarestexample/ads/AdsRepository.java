package io.github.soumikuxd.springdatarestexample.ads;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdsRepository extends PagingAndSortingRepository<Ad, Long> {
}
