package com.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HolomanConfiguration {

	@Bean
	public Holoman holeman() {
		Holoman holoman = new Holoman();
		holoman.setHowLong(5);
		holoman.setName("esjo");
		return holoman;
	}

}
