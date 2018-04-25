package com.baicheng.fork.web.constants;

/**
 * @author mabaoyu
 * 
 * @date：2017年4月18日 上午11:47:59
 */
public class TaskConstants {

	/** jobGroup */
	public static final String JOB_DATA_MAP_KEY_JOB_GROUP = "jobGroup";
	/** jobName */
	public static final String JOB_DATA_MAP_KEY_JOB_NAME = "jobName";

	/** 任务状态 1 就绪 */
	public static final Integer STATUS_WAITTING_TASK = 1;
	/** 任务状态 0 已停止 */
	public static final Integer STATUS_STOP_TASK = 0;
	/** 任务状态 2 已删除 */
	public static final Integer STATUS_DELETE_TASK = 2;
	/** 任务状态 3 已完成 */
	public static final Integer STATUS_FINISH_TASK = 3;
	/** 任务状态 4 运行中 */
	public static final Integer STATUS_RUNNING_TASK = 4;

	/** 任务同步 1是 */
	public static final Integer IS_SYNC = 1;
	/** 任务同步 1否 */
	public static final Integer IS_NOT_SYNC = 0;

	/** 是否包含黑名单 1包含 */
	public static final Integer HAS_BLACK_LIST = 1;
	/** 是否包含黑名单 0不包含 */
	public static final Integer NOT_HAS_BLACK_LIST = 0;

	/** 是否发送报告 0不发送 */
	public static final Integer NOT_HAS_REPORT = 0;
	/** 是否发送报告 1发送 */
	public static final Integer HAS_REPORT = 1;

	/** 任务类型 0短信 */
	public static final Integer TYPE_DEMO = 0;

}
