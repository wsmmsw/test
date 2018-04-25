package com.baicheng.fork.web.constants;

/**
 * 权限标识常量
 * 
 * @author mabaoyu
 * @date：2017年4月18日 上午9:13:49
 */
public class PermitConstants {

	/** 会员菜单 */
	public static final String CRM_MEMBER = "crm:member";
	public static final String MEMBER_ADD = "member:add";
	public static final String MEMBER_LIST = "member:list";

	/** 短信菜单 */
	public static final String CRM_SMS = "crm:sms";
	public static final String SMS_SEND = "sms:send";
	public static final String SMS_LOG = "sms:log";
	public static final String SMS_TASK = "sms:task";
	public static final String SMS_TASK_LIST = "sms:taskList";
	public static final String SMS_CHANNEL = "sms:channel";

	/** 系统菜单 */
	public static final String CRM_SYSTEM = "crm:system";
	public static final String SYSTEM_USER = "system:user";
	public static final String SYSTEM_ROLE = "system:role";
	public static final String SYSTEM_PERMIT = "system:permit";

	/** 个人中心菜单 */
	public static final String CRM_PERSIONAL = "crm:persional";
	public static final String PERSIONAL_PASSWORD = "persional:password";

	/** 黑名单菜单 */
	public static final String CRM_BLACK = "crm:black";
	public static final String BLACK_LIST = "black:list";
	public static final String BLACK_ADD = "black:add";

	/** 邮件菜单 */
	public static final String CRM_EMAIL = "crm:email";
	public static final String EMAIL_TASK = "email:task";
	public static final String EMAIL_TASK_LIST = "email:taskList";
	public static final String EMAIL_TEMPLATE = "email:template";
	public static final String EMAIL_TEMPLATE_LIST = "email:templateList";
	public static final String EMAIL_SEND = "email:send";
	public static final String EMAIL_LOG = "email:log";
	public static final String EMAIL_CHANNEL_SET = "email:channelSet";

	/** 数据推送菜单 */
	public static final String CRM_PUSH = "crm:dockAPI";
	public static final String PUSH_SCRM = "dockAPI:scrm";
	public static final String PUSH_SMY = "dockAPI:sanMaoYou";

	/** 数据统计 菜单 */
	public static final String CRM_STATISTICS = "crm:statistics";
	public static final String STATISTICS_HEADMAP = "statistics:headmap";
	public static final String STATISTICS_PROD = "statistics:prod";

	/** APP PUSH推送 菜单 */
	public static final String CRM_APP_PUSH = "crm:push";
	public static final String APP_PUSH_SEND = "apppush:send";
	public static final String APP_PUSH_TASK = "apppush:task";
	public static final String APP_PUSH_TASK_LIST = "apppush:taskList";
	public static final String APP_PUSH_LOG = "apppush:log";

}
