package com.noki.jtreport.shi.download.javabean;

/**
 * ��������
 * @author ����
 *
 */
public class ReportDaoFactory {
	private ReportDaoFactory(){}
	
	public static ReportDao create(String action){
		if(action.equals("1")){//���л�վ�õ�����Ϣ���ܱ�.xls����һ��
			return new DSJZYDLXXHZ_ReportDao();
		}
		else if(action.equals("2")){//��վ�õ������ܷ�����.xls�������
			return new JZYDLHZFX_ReportDao();
		}
		else if(action.equals("3")){//����רҵ�õ������ܱ�.xls��������
			return new GWZYYDLHZ_ReportDao();
		}
		else if(action.equals("4")){//IDC�õ������ܱ�.xls�����ģ�
			return new IDCYDLHZ_ReportDao();
		}
		else if(action.equals("5")){//ͨ�žַ��õ������ܱ�.xls�����壩
			return new TXJFYDLHZ_ReportDao();
		}
		else if(action.equals("6")){//ͨ�Ż��������¶ȹ������������ܱ�.xls��������
			return new TXJFHJWDGLJNQKHZ_ReportDao();
		}
		else if(action.equals("7")){//IDC�������ܼ���Ӧ��������ܱ�.xls�����ߣ�
			return new IDCJFJNJSYYQKHZ_ReportDao();
		}
		else if(action.equals("8")){//��վ���ܼ��ż���Ӧ�����ͳ�Ʊ�.xls����ˣ�
			return new JZJNJPJSYYQKTJ_ReportDao();
		}
		else if(action.equals("9")){//���ܼ����¹���������.xls����ţ�
			return new JNJPYGZJB_ReportDao();
		}
		else if(action.equals("a")){//�����������õ���̨��.xls����ʮ��
			return new JRWJFYDLTZ_ReportDao();
		}
		else if(action.equals("b")){//ͨ�Ż������ܼ���Ӧ����Ϣͳ��.xls����ʮһ��
			return new TXJFJNJSYYXXTJ_ReportDao();
		}
		else if(action.equals("c")){//�������������ܼ��ż���Ӧ�����.xls����ʮ����
			return new JRWJFJNJPJSYYQK_ReportDao();
		}
		else if(action.equals("d")){//���ܼ��Ź�����Ч����.xls����ʮ����
			return new JNJPGZCX_ReportDao();
		}
		else if(action.equals("e")){//���ܼ���������ܱ�.xls����ʮ�ģ�
			return new JNJSFLHZ_ReportDao();
		}
		else if(action.equals("f")){//���н����������õ�����Ϣ����.xls����ʮ�壩
			return new DSJRWJFYDLXXHZ_ReportDao();
		}
		else if(action.equals("g")){//ͨ�Ż��������¶ȹ����������.xls����ʮ����
			return new TXJFHJWDGLQKHZ_ReportDao();
		}
		else if(action.equals("h")){//��վ�õ���̨��.xls����ʮ����
			return new JZYDLTZ_ReportDao();
		}
		else if(action.equals("z")){//�ɼ���վ�õ�����Ϣ���ܱ�.xls����ʮ�ߣ�
			return new CJJZYDLXXHZB_ReportDao();
		}else if(action.equals("v")){//����10�������豸�����۱���.xls
			return new JNSBHPJBB_ReportDao();
		}
		return null;
	}
}
