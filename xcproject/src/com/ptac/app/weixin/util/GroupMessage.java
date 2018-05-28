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
 * Ⱥ������Ϣ
 * 
 * @author 
 * @date 2013-08-08
 */
public class GroupMessage {
	private static Logger log = LoggerFactory.getLogger(GroupMessage.class);

	public static int sendWX(String s) {
		int result=1;
		// �������û�Ψһƾ֤
		String appId = "wx0a84f0ccd5fbddfe";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "cff897607c05fe9f599e2843fd2ce41e";

		// ���ýӿڻ�ȡaccess_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		
		if (null != at) {
			// ���ýӿڴ����˵�
			result = WeixinUtil.sendGroupMessage(getTextMessage(s), at.getToken());

			// �жϲ˵��������
			if (0 == result)
				log.info("Ⱥ����Ϣ���ͳɹ���");
			else
				log.info("Ⱥ����Ϣʧ�ܣ������룺" + result);
		}
		return result;
	}

	/**
	 * ��װ�˵�����
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