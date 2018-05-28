package com.ptac.app.weixin.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ptac.app.weixin.model.Filter;
import com.ptac.app.weixin.model.Text;
import com.ptac.app.weixin.model.TextMessage;
import com.ptac.app.weixin.pojo.AccessToken;
import com.ptac.app.weixin.util.MessageUtil;
import com.ptac.app.weixin.util.WeixinUtil;

/**
 * 群发送信息
 * 
 * @author 
 * @date 2013-08-08
 */
public class GroupMessage {
	private static Logger log = LoggerFactory.getLogger(GroupMessage.class);

	public static int sendWX(String s) {
		int result=1;
		// 第三方用户唯一凭证
		String appId = "wx0a84f0ccd5fbddfe";
		// 第三方用户唯一凭证密钥
		String appSecret = "cff897607c05fe9f599e2843fd2ce41e";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		
		if (null != at) {
			// 调用接口创建菜单
			result = WeixinUtil.sendGroupMessage(getTextMessage(s), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("群发信息发送成功！");
			else
				log.info("群发信息失败，错误码：" + result);
		}
		return result;
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static TextMessage getTextMessage(String s) {
		Filter filter = new Filter();
		filter.setGroup_id("100");
		Text text =  new Text();
		text.setContent(s);
		TextMessage textMessage = new TextMessage();
		
		textMessage.setFilter( filter );
		textMessage.setText(text);
		textMessage.setMsgtype(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		
		return textMessage;
	}
}