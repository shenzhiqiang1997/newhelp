USE newhelp;

INSERT teacher_ VALUES ('wuzufeng','wuzufeng','吴祖峰','副书记',0),
('honglei','honglei','洪磊','科长',0),
('weijuan','weijuan','魏娟','副科长',0),
('zhangshihan','zhangshihan','张诗晗','辅导员',2014),
('bailongfei','bailongfei','白龙飞','辅导员',2014),
('chengyao','chengyao','程瑶','辅导员',2015),
('zhouxin','zhouxin','周歆','辅导员',2015),
('zhaoyiwei','zhaoyiwei','赵亦为','辅导员',2015),
('dairuiting','dairuiting','戴瑞婷','辅导员',2016),
('lizhou','lizhou','李周','辅导员',2016),
('qixiang','qixiang','齐翔','辅导员',2017),
('wangyanyan','wangyanyan','王艳艳','辅导员',2017),
('wangluyi','wangluyi','王陆一','辅导员',2017),
('yangyanxiang','yangyanxiang','杨彦祥','辅导员',2017) 
ON DUPLICATE KEY UPDATE teacher_id=VALUES(teacher_id),password=VALUES(password),
name=VALUES(name),duty=VALUES(duty),grade=VALUES(grade);