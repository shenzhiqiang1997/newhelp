USE newhelp;

INSERT teacher_ VALUES ('wuzufeng','wuzufeng','�����','�����',0),
('honglei','honglei','����','�Ƴ�',0),
('weijuan','weijuan','κ��','���Ƴ�',0),
('zhangshihan','zhangshihan','��ʫ��','����Ա',2014),
('bailongfei','bailongfei','������','����Ա',2014),
('chengyao','chengyao','����','����Ա',2015),
('zhouxin','zhouxin','���','����Ա',2015),
('zhaoyiwei','zhaoyiwei','����Ϊ','����Ա',2015),
('dairuiting','dairuiting','������','����Ա',2016),
('lizhou','lizhou','����','����Ա',2016),
('qixiang','qixiang','����','����Ա',2017),
('wangyanyan','wangyanyan','������','����Ա',2017),
('wangluyi','wangluyi','��½һ','����Ա',2017),
('yangyanxiang','yangyanxiang','������','����Ա',2017) 
ON DUPLICATE KEY UPDATE teacher_id=VALUES(teacher_id),password=VALUES(password),
name=VALUES(name),duty=VALUES(duty),grade=VALUES(grade);