package com.mb.service.impl;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String>
{

	@Override
	public Optional<String> getCurrentAuditor()
	{
		// TODO Auto-generated method stub
		return Optional.of("AMAN");
	}

}
