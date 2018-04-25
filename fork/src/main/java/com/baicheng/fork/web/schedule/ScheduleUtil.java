package com.baicheng.fork.web.schedule;

import com.baicheng.domain.EnvConfig;

public class ScheduleUtil {

	public static boolean isOnline() {
		String env = System.getProperty("spring.profiles.active");
		return !EnvConfig.V_ENV_SPA_TEST.equals(env);
	}

}
