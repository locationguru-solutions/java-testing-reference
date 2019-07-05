package com.locationguru.automation;

import com.locationguru.csf.base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles({ "team-pilot-dhiraagu" })
public class DhiraaguTest
		extends BaseTest
{
	private static final Logger logger = LogManager.getLogger(DhiraaguTest.class);

}
